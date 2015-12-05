/*
 * UserDeveloper.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011 
 */      
package cn.adwalker.model.finance.domain;


/**
 * 功能概述：<br>
 * 用户-开发者实体
 * 
 * @author zhaozengbin
 */
public class UserDeveloper {
	/** 主键ID */
	private Long id;

	/** 累计提现 */
	private Double totalMoney;

	/** 未确认佣金 */
	private Double unconfirmedMoney;

	/** 已确认佣金 */
	private Double confirmedMoney;

	/** 帐号状态 0:初始化 1：封号 2、正常 */
	private Integer status;
	
	/** 运营已确认的佣金 */
	private Double operateConfirmedMoney;
	
	/** 扣税状态 */
	private int tax_Status;
	
	
	public int getTax_Status() {
		return tax_Status;
	}
	public void setTax_Status(int tax_Status) {
		this.tax_Status = tax_Status;
	}
	
	public Double getOperateConfirmedMoney() {
		return operateConfirmedMoney;
	}
	public void setOperateConfirmedMoney(Double operateConfirmedMoney) {
		this.operateConfirmedMoney = operateConfirmedMoney;
	}

	

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the totalMoney.
	 */
	public Double getTotalMoney() {
		return totalMoney;
	}

	/**
	 * @param totalMoney
	 *            The totalMoney to set.
	 */
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
	/**
	 * @return Returns the unconfirmedMoney.
	 */
	public Double getUnconfirmedMoney() {
		return unconfirmedMoney;
	}

	/**
	 * @param unconfirmedMoney
	 *            The unconfirmedMoney to set.
	 */
	public void setUnconfirmedMoney(Double unconfirmedMoney) {
		this.unconfirmedMoney = unconfirmedMoney;
	}

	/**
	 * @return Returns the confirmedMoney.
	 */
	public Double getConfirmedMoney() {
		return confirmedMoney;
	}

	/**
	 * @param confirmedMoney
	 *            The confirmedMoney to set.
	 */
	public void setConfirmedMoney(Double confirmedMoney) {
		this.confirmedMoney = confirmedMoney;
	}

	/**
	 * @return Returns the status.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
