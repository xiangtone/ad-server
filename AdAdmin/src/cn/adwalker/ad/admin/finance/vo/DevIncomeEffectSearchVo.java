/*
 * DevConsumeEffectConfirmVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 2011-12-29
 */
package cn.adwalker.ad.admin.finance.vo;


/**
 * 功能概述：<br>
 *
 *	开发者收入搜索vo
 *
 * @author jiaozhichao
 */
public class DevIncomeEffectSearchVo {
	
	/**广告id*/
	private Integer adId;
	/**广告名称*/
	private String adName;
	/**开发者id (网站主id)*/
	private Long developerId;
	/**开发者名称*/
	private String devEmail;
	/**开发者状态*/
	private Integer devStatus;
	/**应用id(网站id)*/
	private Long appId;
	/**应用名称(网站名称)*/
	private String appName;

	/**效果发生开始时间*/
	private String statStartTime;
	/**效果发生结束时间*/
	private String statEndTime;
	
	/**下载提交开始时间*/
	private String submitStartTime;
	/**下载提交结束时间*/
	private String submitEndTime;
	
	/**结算状态 0是未结算，1是已结算*/
	private Integer status;
	
	/**结算开始时间*/
	private String settleStartTime;
	/**结算结束时间*/
	private String settleEndTime;
	public Long getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}
	public String getDevEmail() {
		return devEmail;
	}
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	public String getStatStartTime() {
		return statStartTime;
	}
	public void setStatStartTime(String statStartTime) {
		this.statStartTime = statStartTime;
	}
	public String getStatEndTime() {
		return statEndTime;
	}
	public void setStatEndTime(String statEndTime) {
		this.statEndTime = statEndTime;
	}
	public Integer getDevStatus() {
		return devStatus;
	}
	public void setDevStatus(Integer devStatus) {
		this.devStatus = devStatus;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getSubmitStartTime() {
		return submitStartTime;
	}
	public void setSubmitStartTime(String submitStartTime) {
		this.submitStartTime = submitStartTime;
	}
	public String getSubmitEndTime() {
		return submitEndTime;
	}
	public void setSubmitEndTime(String submitEndTime) {
		this.submitEndTime = submitEndTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSettleStartTime() {
		return settleStartTime;
	}
	public void setSettleStartTime(String settleStartTime) {
		this.settleStartTime = settleStartTime;
	}
	public String getSettleEndTime() {
		return settleEndTime;
	}
	public void setSettleEndTime(String settleEndTime) {
		this.settleEndTime = settleEndTime;
	}
}
