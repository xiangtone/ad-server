package cn.adwalker.ad.control.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("devIncomeAuditDao")
public class OperationDevIncomeAuditDao {
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	* <p>Title: updateDevIncome</p>
	* <p>Description:TODO</p>
	* @param objects
	* @author cuidd
	* @date 2014年6月18日
	* @return void
	* @version 1.0
	 */
	
	public void updateDevIncome(Object[] objects) {
		// 1、当天内根据应用、广告（带形式的(⊙o⊙)哦）分组
		// 2、取出成本金额、效果数据
		// 3、对现有数据进行merge操作，如果存在更新判断状态，是否已经确认，如果没确认，更新数据，已经确认原值不变。
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO T_FINACE_DEV_INCOM_CONFIRM(APP_ID,ad_id,DEV_COST,EFFECT_TIME,CREATE_TIME,STATUS) ");
		sb.append("select sta.app_id,sta.ad_id,sum(sta.cost) dev_cost,?,?,? from T_STATIC_AD_BYDAY sta,");
		sb.append("(SELECT app.id,dev.status dev_status from T_APPLICATION app, T_DEVELOPER dev where  app.dev_id=dev.id and dev.status!=-1) t1");
		sb.append(" where sta.static_date=? and sta.app_id=t1.id and t1.dev_status!=-1 GROUP BY sta.app_id,sta.ad_id having sum(sta.cost)>0");
		namedParameterJdbcTemplate.getJdbcOperations().update(sb.toString(), objects);
		
	}
	
	
}
