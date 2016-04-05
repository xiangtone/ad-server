/*
 * UserDeveloper.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.ad.model.developer.domain;

import java.util.Date;

/**
 * 功能概述：<br>
 * 用户-开发者实体
 * 
 * @author zhaozengbin
 */
public class Developer {
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
	/** 累计提现 */
	private Double totalMoney;

	/** 未确认佣金 */
	private Double finance_income;

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

	private Integer flow_status;// 应用状态

	/** 运营已确认的佣金 */
	private Double operateConfirmedMoney;

	/** 扣税状态 */
	private Integer tax_Status;

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

	public Integer getFlow_status() {
		return flow_status;
	}

	public void setFlow_status(Integer flow_status) {
		this.flow_status = flow_status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardUrl() {
		return cardUrl;
	}

	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getFinance_income() {
		return finance_income;
	}

	public void setFinance_income(Double finance_income) {
		this.finance_income = finance_income;
	}

	public Double getConfirmedMoney() {
		return confirmedMoney;
	}

	public void setConfirmedMoney(Double confirmedMoney) {
		this.confirmedMoney = confirmedMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLastUpdateMan() {
		return lastUpdateMan;
	}

	public void setLastUpdateMan(Long lastUpdateMan) {
		this.lastUpdateMan = lastUpdateMan;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
