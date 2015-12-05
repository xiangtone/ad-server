/*
 * UserDeveloper.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.model.app.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.core.repository.Entity;

/**
 * 功能概述：<br>
 * 用户-开发者实体
 * 
 * @author zhaozengbin
 */
public class Developer implements Entity {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 3812329217845171997L;

	/** 主键ID */
	private Long id;

	/** 电子邮箱 */
	private String email;

	/** 密码 */
	private String password;

	/** 公司名 */
	private String companyName;

	/** 公司地址 */
	private String companyAddress;

	/** 通信地址 */
	private String mailingAddress;

	/** 邮编 */
	private String postCode;

	/** QQ */
	private String qq;

	/**
	 * 
	 */
	private String resource_code;

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

	/** 账户类型 1、个人 2、公司 */
	private Integer type;

	/** 描述 */
	private String description;

	/** 证件类型(1:身份证 2:公司号) */
	private Integer cardType;

	/** 证件号 */
	private String cardCode;

	/** 证件图片存放地址 */
	private String cardUrl;

	/** 收益 */
	private Double cost;

	/** 累计提现 */
	private Double totalMoney;

	/** 可提款佣金 */
	private Double finance_income;

	/** 未确认佣金 */
	private Double unconfirmedMoney;

	/** 已确认佣金 */
	private Double confirmedMoney;

	/** 帐号状态 0:初始化 1：封号 2、正常 */
	private Integer status;

	/** 最后更新者 */
	private Long lastUpdateMan;

	/** 修改时间 */
	private Date updateTime;

	/** 创建时间 */
	private Date createTime;

	private Integer appCount;

	/** 运营已确认的佣金 */
	private Double operateConfirmedMoney;

	/** 扣税状态 */
	private Integer tax_Status;

	public Integer getResource() {
		int i = 0;
		if (!StringUtils.isEmpty(resource_code)) {
			i = 1;
		}
		return i;
	}

	public Integer getTax_Status() {
		return tax_Status;
	}

	public void setTax_Status(Integer tax_Status) {
		this.tax_Status = tax_Status;
	}

	public Double getOperateConfirmedMoney() {
		return operateConfirmedMoney;
	}

	public void setOperateConfirmedMoney(Double operateConfirmedMoney) {
		this.operateConfirmedMoney = operateConfirmedMoney;
	}

	public Double getFinance_income() {
		return finance_income;
	}

	public void setFinance_income(Double finance_income) {
		this.finance_income = finance_income;
	}

	/**
	 * @return the appCount
	 */
	public Integer getAppCount() {
		return appCount;
	}

	/**
	 * @param appCount
	 *            the appCount to set
	 */
	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	public String getResource_code() {
		return resource_code;
	}

	public void setResource_code(String resource_code) {
		this.resource_code = resource_code;
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

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
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

	/**
	 * @return the lastUpdateMan
	 */
	public Long getLastUpdateMan() {
		return lastUpdateMan;
	}

	/**
	 * @param lastUpdateMan
	 *            the lastUpdateMan to set
	 */
	public void setLastUpdateMan(Long lastUpdateMan) {
		this.lastUpdateMan = lastUpdateMan;
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
}