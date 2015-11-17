/*
 * SearchVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 09-Dec-2011
 */
package cn.adwalker.ad.web.common.bean;


/**
 * 功能概述：<br>
 * 搜索vo
 * 
 * @author zhaozengbin
 */
public class SearchBean {
	
	/** 搜索类型 */
	private String searchType;

	/** 搜索内容 */
	private String searchText;

	/** 状态 */
	private String statusName;

	/** 状态 */
	private Integer status;

	/** 当前登录用户id */
	private Long currentUserId;
	
	/** 根据ID查询 */
	private Long id;
	
	/** type(是否根据ID搜索，all不跟根据ID查，manage为管理员查找指定用户，其他则根据当前登录用户的ID查找) */
	private String type;
	
	/** 是否加入黑名单***/
	private Integer shitList;
	
	/** 墙类型 */
	private String wallTypeName;

	/** 墙类型*/
	private Integer wallType;
	
	/** 开始时间 */
	private String startTime;
	/** 结束时间*/
	private String endTime;
	
	public Integer getShitList() {
		return shitList;
	}

	public void setShitList(Integer shitList) {
		this.shitList = shitList;
	}

	/**
	 * @return Returns the searchType.
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType
	 *            The searchType to set.
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
	 * @param searchText
	 *            The searchText to set.
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
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

	/**
	 * @return Returns the currentUserId.
	 */
	public Long getCurrentUserId() {
		return currentUserId;
	}

	/**
	 * @param currentUserId
	 *            The currentUserId to set.
	 */
	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}

	/**
	 * @return Returns the statusName.
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName
	 *            The statusName to set.
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getWallTypeName() {
		return wallTypeName;
	}

	public void setWallTypeName(String wallTypeName) {
		this.wallTypeName = wallTypeName;
	}

	public Integer getWallType() {
		return wallType;
	}

	public void setWallType(Integer wallType) {
		this.wallType = wallType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
