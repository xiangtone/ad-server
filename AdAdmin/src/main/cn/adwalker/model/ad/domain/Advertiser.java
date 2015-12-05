/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.ad.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 注册广告主vo
* <p>Title: AdvForm</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-3-29
 */
public class Advertiser implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -811944891391415843L;
	/** 主键ID */
	private Long id;
	/** 电子邮箱 */
	private String email;

	/** 密码 */
	private String password;

	/** 帐号状态 0:初始化 1：封号 2、正常 */
	private Integer status;

	/** 公司名 */
	private String company_name;

	/** 公司地址 */
	private String company_address;

	/** 1个人 2 公司 */
	private Integer type;

	/** 邮编 */
	private String postcode;

	/** QQ */
	private String qq;

	/** 真实姓名 */
	private String real_name;
	
	/** 区域类型(4:华南、1:华东、2:华北、0:平台) */
	private Integer area_type;

	/** 移动电话 */
	private String mobile_phone;

	/** 固定电话 */
	private String fixed_phone;
	/** 创建时间 */
	private Date create_time;
	
	/** 创建时间 */
	private Date update_time;
	/** 信用额度 */
	private Double credit_quota;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getFixed_phone() {
		return fixed_phone;
	}

	public void setFixed_phone(String fixed_phone) {
		this.fixed_phone = fixed_phone;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Double getCredit_quota() {
		return credit_quota;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public void setCredit_quota(Double credit_quota) {
		this.credit_quota = credit_quota;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	/**
	 * @return area_type
	 */
	public Integer getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type the area_type to set
	 */
	
	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}
}
