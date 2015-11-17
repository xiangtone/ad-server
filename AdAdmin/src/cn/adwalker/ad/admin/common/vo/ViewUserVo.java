/*
 * UserLoginVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-30
 */
package cn.adwalker.ad.admin.common.vo;

/**
 * 功能概述：<br>
 * 
 * 	登录用
 *
 * @author jiaozhichao
 */
public class ViewUserVo {
	private String password;
	/** 1：广告主 2:开发者  */
	private Integer type;
	/**  正常(激活) 1的时候才能登录 */
	private Integer status;
	private String email;
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the type.
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return Returns the status.
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
