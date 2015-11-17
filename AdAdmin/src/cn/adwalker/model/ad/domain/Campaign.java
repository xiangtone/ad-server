/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.ad.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 活动主持VO
 * <p>
 * Title: PlacementVo
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
public class Campaign implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -7998318442200388752L;

	// 主键
	private Long id;

	// 活动名称
	private String campaign_name;

	// 活动类型
	private Integer campaign_type;

	// 活动类别(对应app的应用分类)
	private Integer category_id;

	// 广告主id
	private Long adv_id;

	// 计费方式
	private String charge_type;

	// 接入单价
	private Double price;

	// 限制每日预算
	private Double budget;
	
	private Long salesman_id;

	/**
	 * 限量要求
	 */
	private String campaign_required;

	// 数据入库时间
	private Date create_time;

	// 操作人id
	private Long create_user;
	
	//结算周期
	private Integer balance_cycle;
	
	
	//上次结算日期
	private Date last_balance_date;
	
	
	private Date online_time;

	/**
	 * 
	 */
	private Integer max_package_code;

	// 确认方式（0线下、1线上）
	private Integer confirm_mode;
	
	//活动来源，1、直客；2、二道贩子
	private Integer res_type;
	

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

	public void setCampaign_type(Integer campaign_type) {
		this.campaign_type = campaign_type;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public String getCampaign_required() {
		return campaign_required;
	}

	public void setCampaign_required(String campaign_required) {
		this.campaign_required = campaign_required;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getMax_package_code() {
		return max_package_code;
	}

	public void setMax_package_code(Integer max_package_code) {
		this.max_package_code = max_package_code;
	}

	public Integer getConfirm_mode() {
		return confirm_mode;
	}

	public void setConfirm_mode(Integer confirm_mode) {
		this.confirm_mode = confirm_mode;
	}

	/**
	 * @return salesman_id
	 */
	public Long getSalesman_id() {
		return salesman_id;
	}

	/**
	 * @param salesman_id the salesman_id to set
	 */
	
	public void setSalesman_id(Long salesman_id) {
		this.salesman_id = salesman_id;
	}

	/**
	 * @return balance_cycle
	 */
	public Integer getBalance_cycle() {
		return balance_cycle;
	}

	/**
	 * @param balance_cycle the balance_cycle to set
	 */
	
	public void setBalance_cycle(Integer balance_cycle) {
		this.balance_cycle = balance_cycle;
	}

	/**
	 * @return last_balance_date
	 */
	public Date getLast_balance_date() {
		return last_balance_date;
	}

	/**
	 * @param last_balance_date the last_balance_date to set
	 */
	
	public void setLast_balance_date(Date last_balance_date) {
		this.last_balance_date = last_balance_date;
	}

	public Date getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Date online_time) {
		this.online_time = online_time;
	}

	public Integer getRes_type() {
		return res_type;
	}

	public void setRes_type(Integer res_type) {
		this.res_type = res_type;
	}
}
