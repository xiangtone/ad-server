/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.bean;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

/**
 * 功能概述：<br>
 * 注册实体Vo
 * 
 * @author zhaozengbin
 */
public class CampaignQueryBean {

	private String campaign;
	// 活动类型
	private Integer status;

	private String adv;
	
	private String os;
	
	private String charge_type;

	// 操作人id
	private Long manager_id;
	// 操作人
	private String create_user;

	public Long getCampaign_id() {
		long l = 0l;
		if (!StringUtil.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign);
		}
		return l;
	}

	public String getCampaign_name() {
		String s = null;
		if (!StringUtil.isEmpty(campaign)) {
			s = campaign;
		}
		return s;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getAdv_id() {
		long adv_id = 0L;
		if (!StringUtil.isEmpty(adv) && NumberUtils.isNumber(adv)) {
			adv_id = Long.valueOf(adv);
		}
		return adv_id;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getAdv() {
		return adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public String getCompany_name() {
		String s = null;
		if (!StringUtil.isEmpty(adv)) {
			s = adv;
		}
		return s;
	}
}
