package cn.adwalker.ad.control.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.util.ConfigUtil;

@Repository("reDao")
public class IOSIncomNumberDao {
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	* <p>Title: copyActionLog</p>
	* <p>Description:TODO</p>
	* @param beginTime
	* @param endTime
	* @author cuidd
	* @date 2014年6月18日
	* @return void
	* @version 1.0
	 */
	public void copyActionLog(String beginTime, String endTime) {
		String ids = ConfigUtil.getString("t_ios_income_number_ad_id");
		if(ids == null || "".equals(ids)) {
			return;
		}
		String[] adIds = ids.split(",");
		for(String adid : adIds) {
			StringBuilder sql = new StringBuilder("insert into T_IOS_INCOME_NUMBER (INCOME_MAC,STAT_DATE,AD_ID,STATUS,MANAGER_ID,CREATE_TIME,OPENUDID,IDFA)");
			sql.append(" select mac,stat_date,ad_id,0,0,create_time,openudid,idfa from (");
			sql.append("select mac,max(stat_date) stat_date,ad_id,max(create_time) create_time,max(openudid) openudid,idfa from t_ios_action_log where ad_id='");
			sql.append(adid);
			sql.append("' and activite_status=0 and date_format(create_time,'%Y-%m-%d %T')>=? and date_format(create_time,'%Y-%m-%d %T')<? group by ad_id,mac,idfa) t1");
			namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), beginTime, endTime);
		}
	}
	
	
}
