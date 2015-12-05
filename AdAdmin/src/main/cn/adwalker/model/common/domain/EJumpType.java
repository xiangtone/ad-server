/*
 * EJumpType.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 10-Dec-2011
 */
package cn.adwalker.model.common.domain;

/**
 * 功能概述：<br>
 * 中间页跳转类型
 * 
 * @author zhaozengbin
 */
public enum EJumpType {
	SUCCESS("success"), FAIL("fail");

	private String value;

	private EJumpType(String value) {
		this.value = value;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
