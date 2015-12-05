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
* <p>Title: Price</p>
* <p>Description:</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年6月20日
 */
public class Price implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -7998318442200388752L;
	// 主键
	private Long id;
	// 主键
	private Long cam_ad_id;
	// 接入单价
	private Double price;
	//操作人id
	private Long operater_id;
	// 数据入库时间
	private Date create_time;
	// 类型(1：活动；2：广告)
	private Integer cam_ad_type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCam_ad_id() {
		return cam_ad_id;
	}
	public void setCam_ad_id(Long cam_ad_id) {
		this.cam_ad_id = cam_ad_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getOperater_id() {
		return operater_id;
	}
	public void setOperater_id(Long operater_id) {
		this.operater_id = operater_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getCam_ad_type() {
		return cam_ad_type;
	}
	public void setCam_ad_type(Integer cam_ad_type) {
		this.cam_ad_type = cam_ad_type;
	}
	
	
	
}
