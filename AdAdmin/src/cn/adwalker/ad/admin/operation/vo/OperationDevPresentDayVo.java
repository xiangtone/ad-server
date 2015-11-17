/*
 * ReportDevAdDayStatVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 20-Dec-2011
 */
package cn.adwalker.ad.admin.operation.vo;

import java.util.Date;

/**
 * 功能概述：<br>
 * 开发者广告统计实体
 * 
 * @author guoyongxiang
 */
public class OperationDevPresentDayVo {

	/** ID主键 */
	private Long id;
	/** 开发者ID */
	private Long dev_id; // 开发者id
	/** 开发者邮箱 */
	private String dev_Email; // 开发者名称	
	/** 赠送金额 */
	private double money;
	/** 结算时间 */
	private Date create_time;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDev_id() {
		return dev_id;
	}
	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}
	public String getDev_Email() {
		return dev_Email;
	}
	public void setDev_Email(String dev_Email) {
		this.dev_Email = dev_Email;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
