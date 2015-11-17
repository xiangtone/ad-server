/*
 * AdEffectSecondVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-28
 */
package cn.adwalker.ad.admin.finance.vo;

/**
 * 功能概述：<br>
 *
 *		广告主结算确认搜索vo
 *
 * @author jiaozhichao
 */
public class AdEffectSecondVo {
	
	/**  开始时间 */
	private String startTime;
	
	/**  结束时间 */
	private String endTime;
	
	/** 搜索类型 name:联系人  mail：广告主邮箱  */
	private String searchType;
	
	/**  搜索内容 */
	private String searchText;

	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime The endTime to set.
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return Returns the searchType.
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType The searchType to set.
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return Returns the searchText.
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText The searchText to set.
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	
	
}
