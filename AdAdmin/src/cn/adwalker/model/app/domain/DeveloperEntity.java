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

import cn.adwalker.core.repository.Entity;

/**
 * 功能概述：<br>
 * 用户-开发者实体
 * 
 * @author zhaozengbin
 */
public class DeveloperEntity implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -3540573102628800889L;

	/** 主键ID */
	private Long id;

	/** 电子邮箱 */
	private String email;

	/** 密码 */
	private String password;

	/** 公司名 */
	private String company_name;

	/** 公司地址 */
	private String company_address;

	/** 通信地址 */
	private String mailing_address;

	/** 邮编 */
	private String post_code;

	/** QQ */
	private String qq;

	/** msn */
	private String msn;

	/** 真实姓名 */
	private String real_name;

	/** 移动电话 */
	private String mobile_phone;
	
	private String	fixed_phone;

	/** 网址 */
	private String website_url;

	/** 账户类型 1、个人 2、公司 */
	private Integer type;

	/** 描述 */
	private String description;

	/** 证件类型(1:身份证 2:公司号) */
	private Integer card_type;

	/** 证件号 */
	private String card_code;

	/** 证件图片存放地址 */
	private String card_url;

	/** 可提款佣金 */
	private Double finance_income;

	/** 帐号状态 0:初始化 1：封号 2、正常 */
	private Integer status;

	/** 修改时间 */
	private Date update_time;

	/** 创建时间 */
	private Date create_time;

	/** 扣税状态 */
	private int tax_Status;

	private String resource_code;

	public int getTax_Status() {
		return tax_Status;
	}

	public void setTax_Status(int tax_Status) {
		this.tax_Status = tax_Status;
	}

	public Double getFinance_income() {
		return finance_income;
	}

	public void setFinance_income(Double finance_income) {
		this.finance_income = finance_income;
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
	
	

	public String getFixed_phone() {
		return fixed_phone;
	}

	public void setFixed_phone(String fixed_phone) {
		this.fixed_phone = fixed_phone;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getMailing_address() {
		return mailing_address;
	}

	public void setMailing_address(String mailing_address) {
		this.mailing_address = mailing_address;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
 

	public String getWebsite_url() {
		return website_url;
	}

	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
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

	public Integer getCard_type() {
		return card_type;
	}

	public void setCard_type(Integer card_type) {
		this.card_type = card_type;
	}

	public String getCard_code() {
		return card_code;
	}

	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

	public String getCard_url() {
		return card_url;
	}

	public void setCard_url(String card_url) {
		this.card_url = card_url;
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

	public String getResource_code() {
		return resource_code;
	}

	public void setResource_code(String resource_code) {
		this.resource_code = resource_code;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}