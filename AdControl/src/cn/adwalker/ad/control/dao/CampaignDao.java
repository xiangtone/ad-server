package cn.adwalker.ad.control.dao;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.Campaign;
import cn.adwalker.ad.control.exception.DatabaseException;

@Repository("campaignDao")
public class CampaignDao extends BaseDao<Campaign> {
	
	public CampaignDao() {
		setClazz(Campaign.class);
	}
	
	public Campaign getCampaign(Long id) throws DatabaseException {
		StringBuilder sb =new StringBuilder();
		sb.append("SELECT id,campaign_name,campaign_type,category_id,adv_id,charge_type,");
		sb.append("price,budget,salesman_id,campaign_required,create_time,create_user,area_directional,");
		sb.append("app_type,sdk_version,terminal_type,time_directional,operat_agencies,phone_brand,");
		sb.append("balance_cycle,last_balance_date,max_package_code,confirm_mode FROM t_campaign WHERE id = ?");
		return getObjByParams(sb.toString(),id);
		
	}
}
