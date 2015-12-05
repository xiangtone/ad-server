/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import cn.adwalker.ad.config.StatusConstant;

/**
 * 活动主持VO
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
public class CampaignVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -2237320771076063905L;

	// 主键
	private Long id;

	// 广告主id
	private Integer adv_id;

	// 广告主
	private String company_name;

	// 活动名称
	private String campaign_name;

	// 活动类型
	private Integer campaign_type;

	// 活动类别(生活类，工具类。。。。)
	private Integer category_id;
	// 状态
	private Integer status;

	/**
	 * 投放id
	 */
	private Long placement_id;

	// 计费方式
	private String charge_type;

	// 接入单价
	private Double price;
	// 限制每日预算
	private String budget;

	// 平台类型
	private String os;
	// 排期开始时间
	private Date plan_start;
	// 排期结束时间
	private Date plan_end;
	// 数据入库时间
	private Timestamp create_time;
	// 操作人id
	private Long create_user;
	
	private String salesman_name;
	

	private String create_user_name;
	
	/**
	 * 
	* <p>Title: getStatusName</p>
	* <p>Description:TODO</p>
	* @return
	* @author cuidd
	* @date 2013-6-20
	* @return String
	* @version 1.0
	 */
	public String getStatus_name(){
		return StatusConstant.getCampaignStatusName(status);
	}
	
	
	

	/********************** getter\setter方法 ****************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCampaign_type() {
		return campaign_type;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public void setCampaign_type(Integer campaign_type) {
		this.campaign_type = campaign_type;
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

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
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

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
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
	
	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
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

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}


	/**
	 * @return salesman_name
	 */
	public String getSalesman_name() {
		return salesman_name;
	}


	/**
	 * @param salesman_name the salesman_name to set
	 */
	
	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}
	
	
}
