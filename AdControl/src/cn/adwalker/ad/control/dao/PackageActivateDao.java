package cn.adwalker.ad.control.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.PackageActivate;
import cn.adwalker.ad.control.domain.PackageActivateInprice;
import cn.adwalker.ad.control.domain.PackageActivateTmp;
import cn.adwalker.ad.control.exception.DatabaseException;

@Repository("packageActivateDao")
public class PackageActivateDao extends BaseDao<PackageActivate> {

	public PackageActivateDao() {
		setClazz(PackageActivate.class);
	}

	public Long savePackageActivate(PackageActivate packageActivate)
			throws DatabaseException {
		StringBuilder sql = new StringBuilder(
				"insert into T_PACKAGE_ACTIVATE(CREATE_TIME,PACKAGE_ID,STATIC_DATE,STATUS)");
		sql.append(" VALUES(:create_time,:package_id,:static_date,:status)");
		return save(sql.toString(), packageActivate);
	}

	public List<PackageActivateTmp> getPackageFlatformList(String date)
			throws DatabaseException {
		String sql = "select PACKAGE_ID,MEDIA_ID,TYPE_ID,sum(stat.activate) activate,sum(cost) cost,null as out_price, 0 as media_type,null as BLANCE_MODE from T_LOG_AD_PLACEMENT p INNER JOIN (SELECT ad_id,sum(ACTIVATE) ACTIVATE,sum(cost) cost FROM T_STATIC_AD_BYDAY where static_date ='"
				+ date
				+ "' group by ad_id having(sum(cost) > 0 OR sum(ACTIVATE) > 0)) stat on p.ad_id = stat.ad_id where static_date = '"
				+ date
				+ "' and p.os='android' group by PACKAGE_ID, MEDIA_ID, TYPE_ID";
		return super.namedParameterJdbcTemplate.getJdbcOperations().query(
				sql,
				new BeanPropertyRowMapper<PackageActivateTmp>(
						PackageActivateTmp.class));
	}

	public List<PackageActivateTmp> getPackageChannelList(String date)
			throws DatabaseException {
		String sql = "select PACKAGE_ID,MEDIA_ID,TYPE_ID,0 as activate,0 as cost,ad_price as out_price,1 as media_type,BLANCE_MODE as BLANCE_MODE from T_LOG_AD_PLACEMENT where ad_type = 1 and static_date ='"
				+ date + "' and os='android' and status!=-5";
		return super.namedParameterJdbcTemplate.getJdbcOperations().query(
				sql,
				new BeanPropertyRowMapper<PackageActivateTmp>(
						PackageActivateTmp.class));
	}

	public int update(String date) throws DatabaseException {
		String sql = "UPDATE t_package_activate p SET SYS_ACTIVATE=(SELECT SUM(SYS_ACTIVATE) FROM `t_package_activate_detail` WHERE parent_id=p.id) WHERE static_date=?";
		return update(sql, date);
	}

	public List<PackageActivateInprice> getPackagePriceList()
			throws DatabaseException {
		String sql = "SELECT  t.id as id, cmp.price as inprice,cmp.id as campaign_id,cmp.charge_type   from (SELECT id,  package_id   from  t_package_activate where in_price is null ) t LEFT  JOIN  t_placement_package p on t.package_id=p.id left join  t_campaign_placement_rel rel on  rel.placement_id=p.placement_id left join  t_campaign cmp  on rel.campaign_id=cmp.id";
		return super.namedParameterJdbcTemplate.getJdbcOperations().query(
				sql,
				new BeanPropertyRowMapper<PackageActivateInprice>(
						PackageActivateInprice.class));
	}

	public int[] batchUpdate(final List<PackageActivateInprice> listPrice)
			throws DatabaseException {
		String sql = "update t_package_activate set in_price=?,campaign_id=?,charge_type=? where id=?";
		return super.namedParameterJdbcTemplate.getJdbcOperations()
				.batchUpdate(sql, new BatchPreparedStatementSetter() {
					public int getBatchSize() {
						return listPrice.size();
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						PackageActivateInprice pa = listPrice.get(i);
						ps.setBigDecimal(1, pa.getInprice());
						ps.setLong(2, pa.getCampaign_id());
						ps.setString(3, pa.getCharge_type());
						ps.setLong(4, pa.getId());
					}
				});
	}
}
