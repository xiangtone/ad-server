package cn.adwalker.ad.admin.finance.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
* <p>Title: FinanceRevenueCostYJFbean</p>
* <p>Description:确认收入成本(平台)bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-7-3
 */
public class FinanceRevenueCostYJFbean {
	//广告主ID
	//广告主名称
	private String adv;
	//活动id
	//活动名称
	private String campaign;
	//
	private String media_type;
	//计费类型
	private Long type_id;
	//平台类型
	private String os;
	//效果发生开始时间
	private String statDateStartTime;
	//效果发生结束时间
	private String statDateEndTime;
	
	public Long getAdv_Id() {
		Long media_id = 0l;
		if (!StringUtils.isEmpty(adv) && NumberUtils.isNumber(adv)) {
			media_id = Long.valueOf(adv);
		}
		return media_id;
	}
	 
	public String getAdv_email() {
		String media_name = null;
		if (!StringUtils.isEmpty(adv)) {
			media_name = adv.trim();
		}
		return media_name;
	}
	public Long getCampaign_id() {
		Long media_id = 0l;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			media_id = Long.valueOf(campaign);
		}
		return media_id;
	}
	 
	public String getCampaign_name() {
		String media_name = null;
		if (!StringUtils.isEmpty(campaign)) {
			media_name = campaign.trim();
		}
		return media_name;
	}
	 
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getStatDateStartTime() {
		return statDateStartTime;
	}
	public void setStatDateStartTime(String statDateStartTime) {
		this.statDateStartTime = statDateStartTime;
	}
	public String getStatDateEndTime() {
		return statDateEndTime;
	}
	public void setStatDateEndTime(String statDateEndTime) {
		this.statDateEndTime = statDateEndTime;
	}
	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getAdv() {
		return adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
	}

	public String getMedia_type() {
		return media_type;
	}

	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}
	
}
