package cn.adwalker.ad.control.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.PackageActivateDetail;
import cn.adwalker.ad.control.exception.DatabaseException;

@Repository("packageActivateDetailDao")
public class PackageActivateDetailDao  extends BaseDao<PackageActivateDetail>{
	
	public PackageActivateDetailDao() {
		setClazz(PackageActivateDetail.class);
	}

	public int[] batchInsert(final List<PackageActivateDetail> packageActivateList) throws DatabaseException {
		String sql = "insert into T_PACKAGE_ACTIVATE_DETAIL (PACKAGE_ID, STATIC_DATE, MEDIA_ID, TYPE_ID, SYS_ACTIVATE,parent_id, sys_cost,in_price, out_price, media_type, BLANCE_MODE,status) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		return super.namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public int getBatchSize() {
                return packageActivateList.size();
            }
            public void setValues(PreparedStatement ps, int i)throws SQLException {
            	PackageActivateDetail entity = packageActivateList.get(i);
            	ps.setLong(1, entity.getPackage_id());
            	ps.setString(2, entity.getStatic_date());
            	ps.setLong(3, entity.getMedia_id());
            	ps.setLong(4, entity.getType_id());
            	ps.setInt(5, entity.getSys_activate()!=null?entity.getSys_activate():0);           	
            	ps.setLong(6, entity.getParent_id());
            	ps.setDouble(7, entity.getSys_cost());
            	ps.setString(8, entity.getIn_price() !=null ?  String.valueOf(entity.getIn_price()):null);
            	ps.setString(9, entity.getOut_price() !=null ? String.valueOf(entity.getOut_price()):null);
            	ps.setInt(10, entity.getMedia_type());
            	ps.setString(11, entity.getBlance_mode());
            	ps.setString(12, entity.getStatus() !=null ? String.valueOf(entity.getStatus()):null);              	   
            }
        });
	}
}
