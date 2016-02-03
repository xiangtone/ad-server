/*
 * ESearchVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 09-Dec-2011
 */
package cn.adwalker.ad.web.common.bean;

import cn.adwalker.ad.config.AppConstant;

/**
 * 功能概述：<br>
 * 搜索Vo 数据库对应枚举
 * 
 * @author zhaozengbin
 */
public enum ESearchBean {
	/** ID字段 */
	id("ID"),
	/** NAME字段 */
	name("NAME"),
	/** EMAIL字段 */
	email("EMAIL"),
	/** REAL_NAME字段 */
	realName("REAL_NAME"),
	/** DEV_EMAIL字段 */
	devEmail("DEV_EMAIL"),
	/** 帐号状态名-正常 */
	use(AppConstant.BLOCK_USE+""),
	/** 帐号状态名-封号 */
	closed(AppConstant.BLOCK_CLOSED+""),
	/** 帐号状态名-未激活 */
	notUsed(AppConstant.BLOCK_NOTUSED+"");
	
	/** 数据库对应列名 */
	private String columnName;

	private ESearchBean(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return Returns the columnName.
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            The columnName to set.
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
