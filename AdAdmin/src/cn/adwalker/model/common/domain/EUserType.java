/*
 * EUserType.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.model.common.domain;

/**
 * 功能概述：<br>
 * 用户类型
 * 
 * @author zhaozengbin
 */
public enum EUserType {

	ADVERTISER(1, "advertiser"), DEVELOPER(2, "developer");

	/** type */
	private Integer type;
	/** value */
	private String name;

	private EUserType(Integer type, String name) {
		this.type = type;
		this.name = name;
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
	 * @return Returns the value.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

}
