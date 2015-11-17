/*
 * DevAppMoneyVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 29-Dec-2011
 */
package cn.adwalker.ad.web.developer.vo;

import java.util.Date;

import cn.adwalker.ad.util.ObjectUtils;

/**
 * 功能概述：<br>
 * 
 * @author zhaozengbin
 */
public class DevApplyMoneyVo {

	/** ID */
	private Long id;

	/** 银行信息 */
	private Long bankId;

	/** 账号余额 */
	private Double finance_income;
	/** 申请提款的金额 */
	private Double applyMoney;

	/** 审核状态（0:未审批 1:审批通过 -1:审批失败 2:财务审批成功 -2：财务审批失败） */
	private Integer status;

	/** 运营审批人 */
	private Long managerId;

	/** 运营确认金额 */
	private Double managerMoney;

	/** 运营审批时间 */
	private Date managerTime;

	/** 运营说明 */
	private String managerDesc;

	/** 财务审批人 */
	private Long financeId;

	/** 财务确认金额 */
	private Double financeMoney;

	/** 财务审批时间 */
	private Date financeTime;

	/** 财务说明 */
	private String financeDesc;

	/** 账户原来的钱 */
	private Double formerMoney;

	/** 创建时间 */
	private Date createTime;

	/** 联系人 */
	private String realName;

	/** 状态名称 */
	private String statusName;

	/** 扣税 */
	private Double finance_tax;

	/** 手续费 */
	private Double finance_dues;
	/** 是否开具发票 */
	private Integer invoice;
	/** 是否开具发票 */
	private String invoice_name;

	public Double getFinance_tax() {
		return finance_tax;
	}

	public void setFinance_tax(Double finance_tax) {
		this.finance_tax = finance_tax;
	}

	public Double getFinance_dues() {
		return finance_dues;
	}

	public void setFinance_dues(Double finance_dues) {
		this.finance_dues = finance_dues;
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
	 * @return Returns the bankId.
	 */
	public Long getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 *            The bankId to set.
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return Returns the applyMoney.
	 */
	public Double getApplyMoney() {
		return applyMoney;
	}

	/**
	 * @param applyMoney
	 *            The applyMoney to set.
	 */
	public void setApplyMoney(Double applyMoney) {
		this.applyMoney = applyMoney;
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

	/**
	 * @return Returns the managerMoney.
	 */
	public Double getManagerMoney() {
		return managerMoney;
	}

	/**
	 * @param managerMoney
	 *            The managerMoney to set.
	 */
	public void setManagerMoney(Double managerMoney) {
		this.managerMoney = managerMoney;
	}

	/**
	 * @return Returns the managerTime.
	 */
	public Date getManagerTime() {
		return managerTime;
	}

	/**
	 * @param managerTime
	 *            The managerTime to set.
	 */
	public void setManagerTime(Date managerTime) {
		this.managerTime = managerTime;
	}

	/**
	 * @return Returns the managerDesc.
	 */
	public String getManagerDesc() {
		return managerDesc;
	}

	/**
	 * @param managerDesc
	 *            The managerDesc to set.
	 */
	public void setManagerDesc(String managerDesc) {
		this.managerDesc = managerDesc;
	}

	/**
	 * @return Returns the financeMoney.
	 */
	public Double getFinanceMoney() {
		return financeMoney;
	}

	/**
	 * @param financeMoney
	 *            The financeMoney to set.
	 */
	public void setFinanceMoney(Double financeMoney) {
		this.financeMoney = financeMoney;
	}

	/**
	 * @return Returns the financeTime.
	 */
	public Date getFinanceTime() {
		return financeTime;
	}

	/**
	 * @param financeTime
	 *            The financeTime to set.
	 */
	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}

	/**
	 * @return Returns the financeDesc.
	 */
	public String getFinanceDesc() {
		return financeDesc;
	}

	/**
	 * @param financeDesc
	 *            The financeDesc to set.
	 */
	public void setFinanceDesc(String financeDesc) {
		this.financeDesc = financeDesc;
	}

	/**
	 * @return Returns the formerMoney.
	 */
	public Double getFormerMoney() {
		return formerMoney;
	}

	/**
	 * @param formerMoney
	 *            The formerMoney to set.
	 */
	public void setFormerMoney(Double formerMoney) {
		this.formerMoney = formerMoney;
	}

	/**
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId
	 *            the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the financeId
	 */
	public Long getFinanceId() {
		return financeId;
	}

	/**
	 * @param financeId
	 *            the financeId to set
	 */
	public void setFinanceId(Long financeId) {
		this.financeId = financeId;
	}

	/**
	 * @return Returns the statusName.
	 */
	public String getStatusName() {
		if (ObjectUtils.isNotEmpty(status)) {
			if (status == 0) {
				statusName = "待审核";
			} else if (status == 1) {
				statusName = "通过";
			} else if (status == -1) {
				statusName = "不通过";
			} else if (status == 2) {
				statusName = "付款成功";
			} else if (status == -2) {
				statusName = "付款失败";
			} else if (status == -3) {
				statusName = "已撤销申请";
			}
		}
		return statusName;
	}

	/**
	 * @param statusName
	 *            The statusName to set.
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getInvoice() {
		return invoice;
	}

	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}

	public String getInvoice_name() {
		if (ObjectUtils.isNotEmpty(invoice)) {
			if (invoice == 0) {
				invoice_name = "已开发票";
			} else if (invoice == 1) {
				invoice_name = "未开发票";
			} 
		}
		return invoice_name;
	}

	public void setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
	}

	public Double getFinance_income() {
		return finance_income;
	}

	public void setFinance_income(Double finance_income) {
		this.finance_income = finance_income;
	}

	

}
