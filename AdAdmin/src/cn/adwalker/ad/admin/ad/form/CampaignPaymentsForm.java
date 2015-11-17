/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.form;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动主持VO
 * <p>
 * Title: PlacementVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: emar
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-7
 */
public class CampaignPaymentsForm {

	private Date pay_date;

	// 活动id
	private Long balance_account_id;

	/**
	 * 金额
	 */
	private BigDecimal amount;

	private Integer balance_status;

	// 状态（1、正常，-1删除）
	private Integer status;

	private Integer payments_type;

	private Long create_user;

	/**
	 * @return balance_account_id
	 */
	public Long getBalance_account_id() {
		return balance_account_id;
	}

	/**
	 * @param balance_account_id
	 *            the balance_account_id to set
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
	 * @param status
	 *            the status to set
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
	 * @param payments_type
	 *            the payments_type to set
	 */

	public void setPayments_type(Integer payments_type) {
		this.payments_type = payments_type;
	}

	/**
	 * @return create_user
	 */
	public Long getCreate_user() {
		return create_user;
	}

	/**
	 * @param create_user
	 *            the create_user to set
	 */

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	/**
	 * @return pay_date
	 */
	public Date getPay_date() {
		return pay_date;
	}

	/**
	 * @param pay_date
	 *            the pay_date to set
	 */

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	/**
	 * @return amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return balance_status
	 */
	public Integer getBalance_status() {
		return balance_status;
	}

	/**
	 * @param balance_status the balance_status to set
	 */
	
	public void setBalance_status(Integer balance_status) {
		this.balance_status = balance_status;
	}
	
}
