package cn.adwalker.ad.admin.ad.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

/**
 * 功能概述：<br>
 * 注册实体Vo
 * 
 * @author zhaozengbin
 */
public class PlacementQueryBean {

	/**
	 * 活动id或名称
	 */
	private String campaign;

	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;

	/** 支付方式 */
	private String type_id[];

	// 活动类型
	private Integer status;

	/**
	 * 广告主id或名称
	 */
	private String adv;
	
	private String placement_name;

	/**
	 * 系统类型
	 */
	private String os;

	public Long getCampaign_id() {
		return campaign_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompany_name() {
		String s = null;
		if (!StringUtil.isEmpty(adv)) {
			s = adv;
		}
		return s;
	}
	public Long getAdv_id() {
		long adv_id = 0L;
		if (!StringUtil.isEmpty(adv) && NumberUtils.isNumber(adv)) {
			adv_id = Long.valueOf(adv);
		}
		return adv_id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getPlacement_name() {
		return placement_name;
	}

	public void setPlacement_name(String placement_name) {
		this.placement_name = placement_name;
	}

	public String getAdv() {
		return adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String[] getType_id() {
		return type_id;
	}

	public void setType_id(String[] type_id) {
		this.type_id = type_id;
	}

	public String getType_id_str() {
		String str = "";
		if (type_id != null && type_id.length > 0) {
			for (int i = 0; i < type_id.length; i++) {
				if (!StringUtils.isEmpty(str)) {
					str = str + "," + type_id[i];
				} else {
					str = "" + type_id[i];
				}

			}
		}
		return str;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		if (!StringUtil.isEmpty(campaign)) {
			if (NumberUtils.isNumber(campaign)) {
				this.campaign_id = Long.valueOf(campaign);
			} else {
				this.campaign_id = 0L;
			}
			this.campaign_name = campaign;
		}
		this.campaign = campaign;
	}
}
