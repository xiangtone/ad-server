package cn.adwalker.ad.control.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("reportAdByDayDao")
public class ReportAdByDayDao {
	
	@Resource(name="namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public int delColligate(String date) {
		String sql = "delete  from  t_report_ad_byday where static_date=?";
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql, date);
	}
	
	public int update(String date) {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into t_report_ad_byday(STATIC_DATE,ADV_ID,AD_ID,");
		sb.append("AD_NAME,TYPE_ID,TYPE_NAME,OS,PV,CLICK,DOWNLOAD,ACTIVATE,COST,");
		sb.append("EFFECT,EFFECT_TYPE,CHARGE_TYPE,adv_name,salesman_name,");
		sb.append("campaign_id,syspv,sysclick,sysdownload,sysactivate,sysclickd,clickd) ");
		sb.append("select DATE_FORMAT(a.STATIC_DATE,'%Y-%m-%d') static_date,t.adv_id adv_id,");
		sb.append("t.id ad_id,t.placement_name ad_name,t.type_id,f.name as type_name,t.os os,");
		sb.append("IFNULL(a.adpv, 0) as pv,");
		sb.append("IFNULL(a.click, 0) as click,");
		sb.append("IFNULL(a.download, 0) as download,");
		sb.append("IFNULL(a.ad_activate, 0) as activate,IFNULL(round(a.cost, 4), 0) as cost,");
		sb.append("IFNULL((case when os = 'ios' then (case a.type_id when 0 then ad_activate when 1 then click when 2 then click when 4 then click when 5 then adpv end) when os = 'android' then (case a.type_id when 0 then ad_activate when 1 then download when 2 then download  when 4 then click when 5 then adpv end) end),0) effect,");
		sb.append("IFNULL((case when os = 'ios' then (case a.type_id when 0 then 'CPA'  when 1 then 'CPC' when 2 then 'CPC' when 4 then 'CPC'  when 5 then 'CPM' end) when os = 'android' then (case a.type_id when 0 then 'CPA' when 1 then 'CPD' when 2 then 'CPD' when 4 then 'CPC' when 5 then 'CPM' end) end),0) effect_type,");
		sb.append("cmp.charge_type,adv.company_name,");
		sb.append("'',t.campaign_id,syspv,sysclick,sysdownload,sysactivate,sysclickd,clickd ");
		sb.append("from (select static_date,ad_id,type_id,sum(pv) as adpv,sum(click) as click,sum(download) as download,sum(activate) as ad_activate,sum(cost) as cost,sum(syspv) as syspv,sum(sysclick) as sysclick,sum(sysdownload) as sysdownload,sum(sysactivate) as sysactivate,sum(sysclickd) as sysclickd,sum(clickd) as clickd  from ");
		sb.append("t_static_ad_byday where static_date=? group by ad_id, type_id, static_date) a ");
		sb.append("left join v_ad_campaign t on t.id = a.ad_id ");
		sb.append("left join t_type f on f.id = a.type_id ");
		sb.append("left join  T_CAMPAIGN cmp on cmp.id=t.CAMPAIGN_id ");
		sb.append("left join t_advertiser adv on  t.adv_id=adv.id where t.id IS NOT NULL");
		return namedParameterJdbcTemplate.getJdbcOperations().update(sb.toString(), new Object[] {date});
	}
	


}
