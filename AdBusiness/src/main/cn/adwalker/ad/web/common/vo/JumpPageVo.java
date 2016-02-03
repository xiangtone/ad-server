/*
 * JumpPageInfo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 10-Dec-2011
 */
package cn.adwalker.ad.web.common.vo;

/**
 * 功能概述：<br>
 * 中间页跳转信息
 * 
 * @author zhaozengbin
 */
public class JumpPageVo {
	/** 跳转信息 */
	private String jumpInfo;

	/** 跳转链接 */
	private String jumpUrl;

	/** 消息类型 */
	private String infoType;
	/** 注册用户名 */
	private String email;

	/**
	 * 
	 */
	public JumpPageVo() {
		super();
	}

	/**
	 * @param jumpInfo
	 * @param jumpUrl
	 * @param infoType
	 */
	public JumpPageVo(String jumpInfo, String jumpUrl, String infoType) {
		super();
		this.jumpInfo = jumpInfo;
		this.jumpUrl = jumpUrl;
		this.infoType = infoType;
	}

	public JumpPageVo(String jumpInfo, String jumpUrl, String infoType,
			String email) {
		this.jumpInfo = jumpInfo;
		this.jumpUrl = jumpUrl;
		this.infoType = infoType;
		this.email = email;
	}

	/**
	 * @return Returns the jumpInfo.
	 */
	public String getJumpInfo() {
		return jumpInfo;
	}

	/**
	 * @param jumpInfo
	 *            The jumpInfo to set.
	 */
	public void setJumpInfo(String jumpInfo) {
		this.jumpInfo = jumpInfo;
	}

	/**
	 * @return Returns the jumpUrl.
	 */
	public String getJumpUrl() {
		return jumpUrl;
	}

	/**
	 * @param jumpUrl
	 *            The jumpUrl to set.
	 */
	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	/**
	 * @return Returns the infoType.
	 */
	public String getInfoType() {
		return infoType;
	}

	/**
	 * @param infoType
	 *            The infoType to set.
	 */
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
