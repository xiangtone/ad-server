/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.vo;

import java.util.Date;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 查看活动VO
 * <p>
 * Title: CampaignVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-7
 */
public class CampaignInfoVo {
	// 主键
	private Long id;

	// 活动名称
	private String campaign_name;
	
	/**
	 * 活动类型
	 */
	private Integer	campaign_type;

	private String campaign_required;

	private Double budget;

	private Long category_id;

	// 广告主id
	private Integer adv_id;
	// 广告主
	private String adv_email;
	// 计费方式
	private String charge_type;
	// 接入单价
	private Double price;

	// 平台类型
	private String os;
	// 排期开始时间
	private Date plan_start;
	// 排期结束时间
	private Date plan_end;
	// 数据入库时间
	private Date create_time;
	// 操作人id
	private Long manager_id;
	// 操作人id
	private String manager_name;
	
	// 活动状态
	private Integer status;

	// 定向类别id
	private Integer type;
	// 确认方式（0线下、1线上）
	private Integer confirm_mode;
	// 确认方式（0线下、1线上）
	private String confirm_mode_name;
	
	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getAdv_email() {
		return adv_email;
	}

	public void setAdv_email(String adv_email) {
		this.adv_email = adv_email;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Date getPlan_start() {
		return plan_start;
	}

	public void setPlan_start(Date plan_start) {
		this.plan_start = plan_start;
	}

	public Date getPlan_end() {
		return plan_end;
	}

	public void setPlan_end(Date plan_end) {
		this.plan_end = plan_end;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Integer adv_id) {
		this.adv_id = adv_id;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Integer getCampaign_type() {
		return campaign_type;
	}

	public void setCampaign_type(Integer campaign_type) {
		this.campaign_type = campaign_type;
	}

	public CampaignInfoVo() {

		super();

	}
	
	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getCampaign_required() {
		return campaign_required;
	}

	public void setCampaign_required(String campaign_required) {
		this.campaign_required = campaign_required;
	}

	public Integer getConfirm_mode() {
		return confirm_mode;
	}

	public void setConfirm_mode(Integer confirm_mode) {
		this.confirm_mode = confirm_mode;
	}

	public String getConfirm_mode_name() {
		if(ObjectUtils.isNotEmpty(confirm_mode)){
			if(confirm_mode==0){
				confirm_mode_name="线下";
			}else{
				confirm_mode_name="线上";
			}
		}
		return confirm_mode_name;
	}

	public void setConfirm_mode_name(String confirm_mode_name) {
		this.confirm_mode_name = confirm_mode_name;
	}
}
