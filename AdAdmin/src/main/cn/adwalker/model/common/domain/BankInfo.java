/*
 * BankInfoChannel.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 14-Dec-2011
 */
package cn.adwalker.model.common.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 功能概述：<br>
 * 银行信息实体
 * 
 * @author zhaozengbin
 */
public class BankInfo implements Entity {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 7220833565805591804L;

	/** 主键编号 */
	private Long id;

	/** 用户编号 */
	private Long userId;

	/** 角色 1：广告主，2：开发者 */
	private Integer role;

	/** 开户人姓名 */
	private String account_hoder;

	/** 银行名称 */
	private String bankName;

	/** 银行名称 */
	private String bank_city;

	/** 开户支行 */
	private String bankSubbranch;

	/** 银行账户 */
	private String bankAccount;

	/** 创建时间 */
	private Date createTime;

	/** 证件类型(1:身份证 2:公司号) */
	private Integer cardType;

	/** 证件号 */
	private String cardCode;

	/** 证件存放路径 */
	private String cardUrl;

	/** 分类id */
	private Integer city_id;

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the role
	 */
	public Integer getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Integer role) {
		this.role = role;
	}

	public String getAccount_hoder() {
		return account_hoder;
	}

	public void setAccount_hoder(String account_hoder) {
		this.account_hoder = account_hoder;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankSubbranch
	 */
	public String getBankSubbranch() {
		return bankSubbranch;
	}

	/**
	 * @param bankSubbranch
	 *            the bankSubbranch to set
	 */
	public void setBankSubbranch(String bankSubbranch) {
		this.bankSubbranch = bankSubbranch;
	}

	/**
	 * @return the bankAccount
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount
	 *            the bankAccount to set
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the cardType
	 */
	public Integer getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the cardCode
	 */
	public String getCardCode() {
		return cardCode;
	}

	/**
	 * @param cardCode
	 *            the cardCode to set
	 */
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	/**
	 * @return the cardUrl
	 */
	public String getCardUrl() {
		return cardUrl;
	}

	/**
	 * @param cardUrl
	 *            the cardUrl to set
	 */
	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}

	public String getBank_city() {
		return bank_city;
	}

	public void setBank_city(String bank_city) {
		this.bank_city = bank_city;
	}

}
