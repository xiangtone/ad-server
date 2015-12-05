/*
 * ReportDevAdDayStatVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 20-Dec-2011
 */
package cn.adwalker.ad.admin.report.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 功能概述：<br>
 * 开发者应用报表搜索vo
 * 
 * @author guoyongxiang
 */
public class ReportDevAppDayStatManageVo {

	/** 开始日期 */
	private String startTime;

	/** 结束日期 */
	private String endTime;

	/** 统计类型 1、综合 2、日数据 */
	private Integer statType;

	/** 广告类型 */
	private Integer adType;

	/** 统计类型 */
	private Integer statisticalType;

	/** 时间段 1、今天2、昨天3、过去一周4、过去一月5、本周到今天6、本月到今天7、上一月 */
	private Integer timeSlot;

	/** 开发者邮箱 */
	private String devEmail;

	/** 应用名称 */
	private String appName;
	
	/** 开发者搜索类型 */
	private String devType;
	
	/** 应用搜索类型 */
	private String appType;
	
	/** 开发者搜索内容 */
	private String devText;
	
	/** 应用搜索内容 */
	private String appText;
	
	/** 查询的时间区间 */
	private String timeInterval;

	/** 排序字段 */
	private String orderColumn;

	/** 排序条件 */
	private String orderCondition;
	

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return Returns the statType.
	 */
	public Integer getStatType() {
		return statType;
	}

	/**
	 * @param statType
	 *            The statType to set.
	 */
	public void setStatType(Integer statType) {
		this.statType = statType;
	}

	/**
	 * @return Returns the adType.
	 */
	public Integer getAdType() {
		return adType;
	}

	/**
	 * @param adType
	 *            The adType to set.
	 */
	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	/**
	 * @return Returns the statisticalType.
	 */
	public Integer getStatisticalType() {
		return statisticalType;
	}

	/**
	 * @param statisticalType
	 *            The statisticalType to set.
	 */
	public void setStatisticalType(Integer statisticalType) {
		this.statisticalType = statisticalType;
	}

	/**
	 * @return Returns the timeSlot.
	 */
	public Integer getTimeSlot() {
		return timeSlot;
	}

	/**
	 * @param timeSlot
	 *            The timeSlot to set.
	 */
	public void setTimeSlot(Integer timeSlot) {
		this.timeSlot = timeSlot;
	}

	/**
	 * @return the devEmail
	 */
	public String getDevEmail() {
		return devEmail;
	}

	/**
	 * @param devEmail the devEmail to set
	 */
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the devType
	 */
	public String getDevType() {
		return devType;
	}

	/**
	 * @param devType the devType to set
	 */
	public void setDevType(String devType) {
		this.devType = devType;
	}

	/**
	 * @return the appType
	 */
	public String getAppType() {
		return appType;
	}

	/**
	 * @param appType the appType to set
	 */
	public void setAppType(String appType) {
		this.appType = appType;
	}

	/**
	 * @return the devText
	 */
	public String getDevText() {
		return devText;
	}

	/**
	 * @param devText the devText to set
	 */
	public void setDevText(String devText) {
		this.devText = devText;
	}

	/**
	 * @return the appText
	 */
	public String getAppText() {
		return appText;
	}

	/**
	 * @param appText the appText to set
	 */
	public void setAppText(String appText) {
		this.appText = appText;
	}
	
	/**
	 * @return Returns the timeInterval.
	 */
	public String getTimeInterval() {
		StringBuffer sbf = new StringBuffer();
		try {
			if (ObjectUtils.isNotEmpty(startTime)) {
				sbf.append(startTime);
			}
			if (ObjectUtils.isNotEmpty(startTime)
					|| ObjectUtils.isNotEmpty(endTime)) {
				sbf.append("至");
			}
			if (ObjectUtils.isNotEmpty(endTime)) {
				sbf.append(endTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		timeInterval = sbf.toString();
		return timeInterval;
	}

	/**
	 * @param timeInterval
	 *            The timeInterval to set.
	 */
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	/**
	 * @return the orderColumn
	 */
	public String getOrderColumn() {
		return orderColumn;
	}

	/**
	 * @param orderColumn the orderColumn to set
	 */
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	/**
	 * @return the orderCondition
	 */
	public String getOrderCondition() {
		return orderCondition;
	}

	/**
	 * @param orderCondition the orderCondition to set
	 */
	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}
}
