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
public class CampaignAuditMailVo {
	public CampaignAuditMailVo() {

		super();

	}
	// 主键
	private Long id;

	// 活动名称
	private String campaign_name;
	
	
	private String sales_name;
	
	private String sales_email;
	
	
	private Long salesman_id;
	
	private Integer res_type;

	/**
	 * 活动要求
	 */
	private String campaign_required;

	// 广告主
	private String company_name;
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

	// 操作人id
	private String manager_name;

	
	// 活动状态
	private Integer status;
	
	public String getSales_email() {
		return sales_email;
	}

	public void setSales_email(String sales_email) {
		this.sales_email = sales_email;
	}

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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getCampaign_required() {
		return campaign_required;
	}

	public void setCampaign_required(String campaign_required) {
		this.campaign_required = campaign_required;
	}

	public Long getSalesman_id() {
		return salesman_id;
	}

	public void setSalesman_id(Long salesman_id) {
		this.salesman_id = salesman_id;
	}

	public String getSales_name() {
		return sales_name;
	}

	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}

	public Integer getRes_type() {
		return res_type;
	}

	public void setRes_type(Integer res_type) {
		this.res_type = res_type;
	}
}
