/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.ad.domain;

import java.math.BigDecimal;
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
public class CampaignPayments implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 8188873042949439119L;

	// 主键
	private Long id;

	// 活动id
	private Long balance_account_id;

	// 状态（-50活动草稿、-40活动待审核、-30待投放，-20投放草稿，-10投放待审核 ,1上线，0下线）
	private Integer status;

	private Integer payments_type;

	private Date create_time;

	private Long create_user;
	
	private BigDecimal	amount;
	
	private Date pay_date;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return balance_account_id
	 */
	public Long getBalance_account_id() {
		return balance_account_id;
	}

	/**
	 * @param balance_account_id the balance_account_id to set
	 */
	
	public void setBalance_account_id(Long balance_account_id) {
		this.balance_account_id = balance_account_id;
	}

	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return payments_type
	 */
	public Integer getPayments_type() {
		return payments_type;
	}

	/**
	 * @param payments_type the payments_type to set
	 */
	
	public void setPayments_type(Integer payments_type) {
		this.payments_type = payments_type;
	}

	/**
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return create_user
	 */
	public Long getCreate_user() {
		return create_user;
	}

	/**
	 * @param create_user the create_user to set
	 */
	
	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	/**
	 * @return amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return pay_date
	 */
	public Date getPay_date() {
		return pay_date;
	}

	/**
	 * @param pay_date the pay_date to set
	 */
	
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	
}
