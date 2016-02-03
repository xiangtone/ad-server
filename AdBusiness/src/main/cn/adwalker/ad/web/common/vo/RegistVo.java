/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.web.common.vo;

import java.util.Date;

/**
 * 功能概述：<br>
 * 注册实体Vo
 * 
 * @author zhaozengbin
 */
public class RegistVo {
	/** 主键ID */
	private Long id;

	/** 电子邮箱 */
	private String email;

	/** 密码 */
	private String password;

	/** 帐号状态 0:初始化 1：封号 2、正常 */
	private Integer status;

	/** 公司名 */
	private String companyName;

	/** 公司地址 */
	private String companyAddress;

	/** 1个人 2 公司 */
	private Integer type;

	/** 邮编 */
	private String postCode;

	/** QQ */
	private String qq;

	/** msn */
	private String msn;

	/** 真实姓名 */
	private String realName;

	/** 移动电话 */
	private String mobilePhone;

	/** 固定电话 */
	private String fixedPhone;

	/** 网址 */
	private String websiteUrl;

	/** 描述 */
	private String description;

	/** 证件类型(1:身份证 2:公司号) */
	private Integer cardType;

	/** 累计积分 */
	private Integer totalScore;

	/** 累计提现 */
	private Double totalMoney;

	/** 创建时间 */
	private Date createTime;

	/** 公司logo */
	private String logo;

	/** 账面余额 */
	private Double blance;

	/** 账户累积消费 */
	private Double cost;

	/** 账户累计投资 */
	private Double payment;

	/** 账户预算 */
	private Double budget;

	/** 信用额度 */
	private Double creditQuota;

	/** 用户类型 1、广告主 2、开发者 */
	private Integer userType;

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
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return Returns the companyName.
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            The companyName to set.
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return Returns the companyAddress.
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress
	 *            The companyAddress to set.
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return Returns the type.
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return Returns the postCode.
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            The postCode to set.
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return Returns the qq.
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq
	 *            The qq to set.
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return Returns the msn.
	 */
	public String getMsn() {
		return msn;
	}

	/**
	 * @param msn
	 *            The msn to set.
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}

	/**
	 * @return Returns the realName.
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            The realName to set.
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return Returns the mobilePhone.
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone
	 *            The mobilePhone to set.
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return Returns the fixedPhone.
	 */
	public String getFixedPhone() {
		return fixedPhone;
	}

	/**
	 * @param fixedPhone
	 *            The fixedPhone to set.
	 */
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	/**
	 * @return Returns the websiteUrl.
	 */
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	/**
	 * @param websiteUrl
	 *            The websiteUrl to set.
	 */
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the cardType.
	 */
	public Integer getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            The cardType to set.
	 */
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return Returns the totalScore.
	 */
	public Integer getTotalScore() {
		return totalScore;
	}

	/**
	 * @param totalScore
	 *            The totalScore to set.
	 */
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
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
	 * @return Returns the logo.
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            The logo to set.
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @return Returns the blance.
	 */
	public Double getBlance() {
		return blance;
	}

	/**
	 * @param blance
	 *            The blance to set.
	 */
	public void setBlance(Double blance) {
		this.blance = blance;
	}

	/**
	 * @return Returns the cost.
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            The cost to set.
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

	/**
	 * @return Returns the payment.
	 */
	public Double getPayment() {
		return payment;
	}

	/**
	 * @param payment
	 *            The payment to set.
	 */
	public void setPayment(Double payment) {
		this.payment = payment;
	}

	/**
	 * @return Returns the budget.
	 */
	public Double getBudget() {
		return budget;
	}

	/**
	 * @param budget
	 *            The budget to set.
	 */
	public void setBudget(Double budget) {
		this.budget = budget;
	}

	/**
	 * @return Returns the creditQuota.
	 */
	public Double getCreditQuota() {
		return creditQuota;
	}

	/**
	 * @param creditQuota
	 *            The creditQuota to set.
	 */
	public void setCreditQuota(Double creditQuota) {
		this.creditQuota = creditQuota;
	}

	/**
	 * @return Returns the userType.
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            The userType to set.
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
