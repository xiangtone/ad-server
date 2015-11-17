package cn.adwalker.ad.control.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.AdActualDay;
import cn.adwalker.ad.control.exception.DatabaseException;
import cn.adwalker.ad.control.util.ConfigUtil;

@Repository("adActualDayDao")
public class AdActualDayDao extends BaseDao<AdActualDay> {
	
	public AdActualDayDao() {
		setClazz(AdActualDay.class);
	} 

	public AdActualDay getById(Long id) throws DatabaseException {
		String sql = "select * from T_AD_ACTUAL_DAY where id=?";
		return getObjByParams(sql, id);
	}

	public AdActualDay getByAdIdAndDate(Long adId, String staticDate) throws DatabaseException {
		String sql = "select * from T_AD_ACTUAL_DAY where AD_ID=? AND STATIC_DATE=?";
		return getObjByParams(sql, adId,staticDate);
	}
	
	public Long save(AdActualDay adActualDay) throws DatabaseException {
		StringBuilder sql = new StringBuilder("insert into T_AD_ACTUAL_DAY (AD_ID,IMPRESSIONS_AMOUNT,CLICK_AMOUNT,DOWNLOAD_AMOUNT,ACTION_AMOUNT,UPDATE_TIME,STATIC_DATE,STATUS)");
		sql.append(" values (:adId,:impressionsAmount,:clickAmount,:downloadAmount,:actionAmount,:updateTime,:staticDate,:status)");
		return save(sql.toString(), adActualDay);
	}
	
	public int update(AdActualDay adActualDay) throws DatabaseException {
		StringBuilder sql = new StringBuilder("update T_AD_ACTUAL_DAY set IMPRESSIONS_AMOUNT=:impressionsAmount,CLICK_AMOUNT=:clickAmount,");
		sql.append("DOWNLOAD_AMOUNT=:downloadAmount,ACTION_AMOUNT=:actionAmount,UPDATE_TIME=:updateTime");
		sql.append(" where id=:id");
		return update(sql.toString(), adActualDay);
	}

	public int invalid(String staticDate) throws NumberFormatException, DatabaseException {
		String sql = "update T_AD_ACTUAL_DAY set STATUS=? where STATIC_DATE=?";
		return update(sql.toString(),Integer.parseInt(ConfigUtil.getString("ad.actual.status.invalid")),staticDate);
		
	}

	public List<AdActualDay> getReachActualAdIdList() throws DatabaseException {
		String sql = "SELECT D.* FROM T_AD_ACTUAL_DAY D LEFT JOIN T_AD A ON D.AD_ID=A.ID WHERE A.STATUS=? AND D.STATUS=? AND A.BUDGET_DAY>0 AND A.BUDGET_DAY<=(CASE WHEN A.BUDGET_TYPE='M' THEN FLOOR(D.IMPRESSIONS_AMOUNT/1000) WHEN A.BUDGET_TYPE='C' THEN D.CLICK_AMOUNT WHEN A.BUDGET_TYPE='D' THEN D.DOWNLOAD_AMOUNT WHEN A.BUDGET_TYPE='A' THEN D.ACTION_AMOUNT END)";
		return super.namedParameterJdbcTemplate.getJdbcOperations().query(sql, new BeanPropertyRowMapper<AdActualDay>(AdActualDay.class), Integer.parseInt(ConfigUtil.getString("ad.status.online")), Integer.parseInt(ConfigUtil.getString("ad.actual.status.valid")));
	}

	public int updateRealData(String staticDate) throws DatabaseException {
		StringBuilder sql = new StringBuilder("UPDATE T_AD_ACTUAL_DAY t1,");
		sql.append("(SELECT STATIC_DATE,AD_ID,IFNULL(SUM(PV), 0) IMPRESSIONS_AMOUNT,IFNULL(SUM(CLICK), 0) CLICK_AMOUNT,IFNULL(SUM(DOWNLOAD), 0) DOWNLOAD_AMOUNT,");
		sql.append("IFNULL(SUM(ACTIVATE), 0) ACTION_AMOUNT,NOW() UPDATE_TIME FROM T_STATIC_AD_BYHOUR WHERE STATIC_DATE=? GROUP BY STATIC_DATE,AD_ID) t2 ");
		sql.append("SET t1.IMPRESSIONS_AMOUNT=t2.IMPRESSIONS_AMOUNT,t1.CLICK_AMOUNT=t2.CLICK_AMOUNT,t1.DOWNLOAD_AMOUNT=t2.DOWNLOAD_AMOUNT,t1.ACTION_AMOUNT=t2.ACTION_AMOUNT,");
		sql.append("t1.UPDATE_TIME=NOW() WHERE t1.AD_ID=t2.AD_ID AND t1.static_date=?");
		return update(sql.toString(), staticDate, staticDate);
	}

	public int updateIOSData(String staticDate) throws DatabaseException {
		StringBuilder sql = new StringBuilder("UPDATE T_AD_ACTUAL_DAY t1,");
		sql.append("(SELECT l.ad_key ad_id,COUNT(1) action_amount,DATE_FORMAT(l.create_time, '%Y-%m-%d') static_date FROM t_ios_activite_log l,t_ad a,t_placement p ");
		sql.append("WHERE a.id=l.ad_key AND p.id=a.placement_id AND a.budget_type='A' AND p.os='ios' AND DATE_FORMAT(l.create_time, '%Y-%m-%d')=? AND ad_key IS NOT NULL GROUP BY ad_key,static_date) t2 ");
		sql.append("SET t1.ACTION_AMOUNT=t2.ACTION_AMOUNT,t1.UPDATE_TIME=NOW() WHERE t1.AD_ID=t2.AD_ID AND t1.static_date=?");
		return update(sql.toString(), staticDate, staticDate);
	}
	
}
