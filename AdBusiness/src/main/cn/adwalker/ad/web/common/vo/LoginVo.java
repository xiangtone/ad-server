/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.web.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能概述：<br>
 * 注册实体Vo
 * 
 * @author zhaozengbin
 */
// @Scope("session")
// @Repository("loginVo")
public class LoginVo implements Serializable {

	/**   */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private Long id;

	/** 电子邮箱 */
	private String email;

	/** 密码 */
	private String password;

	/** 公司名称 */
	private String companyName;

	/** 公司地址 */
	private String companyAddress;

	/** 通信地址 */
	private String mailingAddress;

	/** 1：个人 2：公司 */
	private String type;

	/** 用户类型 1、广告主 2、开发者 */
	private Integer userType;

	/** 公司logo */
	private String logo;

	/** 邮编 */
	private String postCode;

	/** qq */
	private Integer qq;

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
	private String cardType;

	/** 证件号 */
	private String cardCode;

	/** 证件附件的图片url */
	private String cardUrl;

	/** 充值额度余额 */
	private Double actualBalance;

	/** 账户累计消费 */
	private Double cost;

	/** 状态(0:初始化 1:激活) */
	private Integer status;

	/** 最后更新人ID(0:为自己) */
	private Long lastUpdateMan;

	/** 信用額度余额 */
	private Double creditBalance;

	/** 账户累计投资 */
	private Double payment;

	/** 账户预算 */
	private Double budget;

	/** 信用额度 */
	private Double creditQuota;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date updateTime;

	/** 累计积分 */
	private Double totalScore;

	/** 累计提现 */
	private Double totalMoney;


	/** 未确认佣金 */
	private Double unconfirmedMoney;

	/** 已确认佣金 */
	private Double confirmedMoney;

	private Integer appCount;

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
	 * @return Returns the mailingAddress.
	 */
	public String getMailingAddress() {
		return mailingAddress;
	}

	/**
	 * @param mailingAddress
	 *            The mailingAddress to set.
	 */
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
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
	public Integer getQq() {
		return qq;
	}

	/**
	 * @param qq
	 *            The qq to set.
	 */
	public void setQq(Integer qq) {
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
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            The cardType to set.
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return Returns the cardCode.
	 */
	public String getCardCode() {
		return cardCode;
	}

	/**
	 * @param cardCode
	 *            The cardCode to set.
	 */
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	/**
	 * @return Returns the cardUrl.
	 */
	public String getCardUrl() {
		return cardUrl;
	}

	/**
	 * @param cardUrl
	 *            The cardUrl to set.
	 */
	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}

	/**
	 * @return Returns the actualBalance.
	 */
	public Double getActualBalance() {
		return actualBalance;
	}

	/**
	 * @param actualBalance
	 *            The actualBalance to set.
	 */
	public void setActualBalance(Double actualBalance) {
		this.actualBalance = actualBalance;
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
	 * @return Returns the lastUpdateMan.
	 */
	public Long getLastUpdateMan() {
		return lastUpdateMan;
	}

	/**
	 * @param lastUpdateMan
	 *            The lastUpdateMan to set.
	 */
	public void setLastUpdateMan(Long lastUpdateMan) {
		this.lastUpdateMan = lastUpdateMan;
	}

	/**
	 * @return Returns the creditBalance.
	 */
	public Double getCreditBalance() {
		return creditBalance;
	}

	/**
	 * @param creditBalance
	 *            The creditBalance to set.
	 */
	public void setCreditBalance(Double creditBalance) {
		this.creditBalance = creditBalance;
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
	 * @return Returns the updateTime.
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            The updateTime to set.
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return Returns the totalScore.
	 */
	public Double getTotalScore() {
		return totalScore;
	}

	/**
	 * @param totalScore
	 *            The totalScore to set.
	 */
	public void setTotalScore(Double totalScore) {
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
	 * @return Returns the appCount.
	 */
	public Integer getAppCount() {
		return appCount;
	}

	/**
	 * @param appCount
	 *            The appCount to set.
	 */
	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}

	/**
	 * @return Returns the serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return Returns the userType.
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * @param userType The userType to set.
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
