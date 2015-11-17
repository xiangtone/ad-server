package cn.adwalker.ad.control.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository("iosEffectByDayDao")
public class IosEffectByDayDao {
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void deleteByDay( String staticDate) {
		String sql = "delete from T_IOS_EFFECT_BYDAY where STATIC_DATE=?";	
		namedParameterJdbcTemplate.getJdbcOperations().update(sql, staticDate);
	}


	public void updateByDay(Object[] objects) {		
		// 计算数据
		StringBuilder sql = new StringBuilder();
		sql.append("insert into T_IOS_EFFECT_BYDAY(STATIC_DATE,CREATE_TIME,MEDIA_TYPE,AD_ID,APP,CHANNEL,PAGE_TYPE,SYS_NUM,CONFIRM_NUM,OS_VERSION,IN_PRICE) ");
		sql.append(" SELECT tlog.static_date,NOW(),tlog.media_type,tlog.ad_id,tlog.application_key,tlog.channel,tlog.page_type,tlog.c,act.c,tlog.os_version,act.in_price FROM ");
		sql.append(" (SELECT DATE_FORMAT(ios.create_time,'%Y-%m-%d') static_date,(CASE WHEN ios.channel ='adwalker' THEN 0  WHEN ios.channel!='adwalker' THEN 1 END) media_type,ios.ad_id,");
		sql.append(" IFNULL(ios.application_key,'') application_key,ios.channel,IFNULL(ios.page_type,'') page_type,IFNULL(ios.os_version,'') os_version,COUNT(1) c,IFNULL(in_price,'0.0') in_price");
		sql.append(" FROM T_IOS_ACTION_LOG ios LEFT JOIN t_campaign_config cof ON ios.ad_id=cof.ad_key WHERE ios.create_time>=? AND ios.create_time<=? AND cof.url IS NOT NULL");
		sql.append(" GROUP BY static_date,ios.ad_id,ios.channel,ios.in_price,ios.page_type) tlog,");
		sql.append(" (SELECT DATE_FORMAT(ios.create_time,'%Y-%m-%d') static_date,");
		sql.append(" (CASE WHEN ios.channel ='adwalker' THEN 0  WHEN ios.channel!='adwalker' THEN 1 END) MEDIA_TYPE,ios.ad_id,");
		sql.append(" IFNULL(ios.application_key,'') application_key,ios.channel,IFNULL(ios.page_type,'') page_type,IFNULL(ios.os_version,'') os_version,COUNT(1) c,IFNULL(in_price,'0.0') in_price");
		sql.append(" FROM t_ios_activite_log ios LEFT JOIN t_campaign_config cof ON ios.ad_id=cof.ad_key WHERE ios.CREATE_TIME>=? AND ios.CREATE_TIME<=? AND cof.url IS NOT NULL");
		sql.append(" GROUP BY static_date,ios.ad_id,ios.channel,ios.in_price,ios.page_type)");
		sql.append(" act where tlog.ad_id=act.ad_id AND tlog.static_date=act.static_date AND tlog.channel=act.channel AND tlog.in_price=act.in_price AND tlog.page_type=act.page_type");
		namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), objects);
	}
	
	
	


	
	
}
