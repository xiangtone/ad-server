/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.operation.vo;

import java.io.Serializable;

/**
* <p>Title: InterfaceIosVo</p>
* <p>Description:ios活动</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-5
 */
public class InterfaceIosVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -2237320771076063905L;

	// 主键
	private Long id;
	
	private Long placement_id;

	// 广告主id
	private Integer adv_id;

	// 广告主
	private String adv_email;

	// 活动名称
	private String campaign_name;
	// 活动类别(生活类，工具类。。。。)
	private Integer category_id;
	// 计费方式
	private String charge_type;
	// 接入单价
	private Double price;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(Integer adv_id) {
		this.adv_id = adv_id;
	}
	public String getAdv_email() {
		return adv_email;
	}
	public void setAdv_email(String adv_email) {
		this.adv_email = adv_email;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
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
	public Long getPlacement_id() {
		return placement_id;
	}
	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
	
}
