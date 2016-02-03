/*
 * ReportDevAdDayStatVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 20-Dec-2011
 */
package cn.adwalker.ad.web.developer.vo;

import cn.adwalker.ad.util.ObjectUtils;

/**
 * 功能概述：<br>
 * 开发者广告报表搜索vo
 * 
 * 
 * @author zhaozengbin
 */
public class ReportDevAdDayStatVo {

	/** 开始日期 */
	private String startTime;

	/** 结束日期 */
	private String endTime;

	/** 统计类型 1、综合 2、日数据 */
	private Integer statType;

	/** 统计范围 1、全部 2、单个软件 */
	private Integer statRange;

	/** 广告类型 */
	private Integer adType;

	/** 开发者id */
	private Long developerId;

	/** 统计类型 */
	private Integer statisticalType;

	/** 开发者应用Id */
	private Long developerAppId;

	/** 时间段 1、今天2、昨天3、过去一周4、过去一月5、本周到今天6、本月到今天7、上一月 */
	private Integer timeSlot;

	/** 查询的时间区间 */
	private String timeInterval;

	/** 排序字段 */
	private String orderColumn;

	/** 排序条件 */
	private String orderCondition;

	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            The startTime to set.
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
	 * @param endTime
	 *            The endTime to set.
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
	 * @return Returns the developerId.
	 */
	public Long getDeveloperId() {
		return developerId;
	}

	/**
	 * @param developerId
	 *            The developerId to set.
	 */
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
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
	 * @return Returns the developerAppId.
	 */
	public Long getDeveloperAppId() {
		return developerAppId;
	}

	/**
	 * @param developerAppId
	 *            The developerAppId to set.
	 */
	public void setDeveloperAppId(Long developerAppId) {
		this.developerAppId = developerAppId;
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
	 * @return Returns the statRange.
	 */
	public Integer getStatRange() {
		return statRange;
	}

	/**
	 * @param statRange
	 *            The statRange to set.
	 */
	public void setStatRange(Integer statRange) {
		this.statRange = statRange;
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
	 * @return Returns the orderColumn.
	 */
	public String getOrderColumn() {
		return orderColumn;
	}

	/**
	 * @param orderColumn
	 *            The orderColumn to set.
	 */
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	/**
	 * @return Returns the orderCondition.
	 */
	public String getOrderCondition() {
		return orderCondition;
	}

	/**
	 * @param orderCondition
	 *            The orderCondition to set.
	 */
	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}

}
