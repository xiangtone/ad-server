/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.form;

import java.util.Date;

/**
 * 注册广告主vo
 * <p>
 * Title: AdvForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-29
 */
public class AdvAddForm {
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

	/** 真实姓名 */
	private String realName;

	/** 移动电话 */
	private String mobilePhone;

	/** 固定电话 */
	private String fixedPhone;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	/** 信用额度 */
	private Double creditQuota;

	/** 区域类型(4:华南、1:华东、2:华北、0:平台) */
	private Integer salesman_area_type;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getCreditQuota() {
		return creditQuota;
	}

	public void setCreditQuota(Double creditQuota) {
		this.creditQuota = creditQuota;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return salesman_area_type
	 */
	public Integer getSalesman_area_type() {
		return salesman_area_type;
	}

	/**
	 * @param salesman_area_type the salesman_area_type to set
	 */
	
	public void setSalesman_area_type(Integer salesman_area_type) {
		this.salesman_area_type = salesman_area_type;
	}
}
