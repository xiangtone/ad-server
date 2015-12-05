/*
 * ReportDevAdDayStatVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 20-Dec-2011
 */
package cn.adwalker.ad.admin.operation.bean;




/**
 * 
* <p>Title: OperDevPresentbean</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-3-11
 */
public class OperDevPresentbean {
	private Long dev_Id; // 开发者id
	private String dev_Name; // 开发者名称	
	private String stat_date_begin; // 日期-开始
	private String stat_date_end; // 日期-结束
	public Long getDev_Id() {
		return dev_Id;
	}
	public void setDev_Id(Long dev_Id) {
		this.dev_Id = dev_Id;
	}
	public String getDev_Name() {
		return dev_Name;
	}
	public void setDev_Name(String dev_Name) {
		this.dev_Name = dev_Name;
	}
	public String getStat_date_begin() {
		return stat_date_begin;
	}
	public void setStat_date_begin(String stat_date_begin) {
		this.stat_date_begin = stat_date_begin;
	}
	public String getStat_date_end() {
		return stat_date_end;
	}
	public void setStat_date_end(String stat_date_end) {
		this.stat_date_end = stat_date_end;
	}
	
}
