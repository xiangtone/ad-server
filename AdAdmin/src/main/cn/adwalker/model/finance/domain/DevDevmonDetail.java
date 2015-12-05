package cn.adwalker.model.finance.domain;

import java.util.Date;


public class DevDevmonDetail {

	private Long ad_id;//广告ID
	private String ad_Name;//广告名称
	private Date effect_Time;//效果发生日期
	private Long dev_Id;//开发者ID
	private String dev_Email;//开发者名称
	private Long app_Id;//应用ID
	private String app_Name;//应用名称
	private Integer status;//结算类型
	private double confirm_Money;//网站主下载确认佣金：
	private double dev_Cost;//网站主下载确认佣金：
	private String manage_Name;//业绩提交人：执行确认操作的运营人员
	private String finance_Name;//业绩提交人：执行确认操作的运营人员
	private Date create_Time;//业绩提交时间：运营人员 审核通过时间
	
	private String status_String;//结算类型 中文名称
	public String getStatus_String() {
		
		if(this.status == 2){
			this.status_String = "已结算";
		}else{
			this.status_String = "未结算";
		}
		return status_String;
	}
	
	
	public String getDev_Email() {
		return dev_Email;
	}


	public void setDev_Email(String dev_Email) {
		this.dev_Email = dev_Email;
	}


	public Date getEffect_Time() {
		return effect_Time;
	}


	public void setEffect_Time(Date effect_Time) {
		this.effect_Time = effect_Time;
	}


	public void setStatus_String(String status_String) {
		this.status_String = status_String;
	}
	public Long getAd_id() {
		return ad_id;
	}
	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
	public String getAd_Name() {
		return ad_Name;
	}
	public void setAd_Name(String ad_Name) {
		this.ad_Name = ad_Name;
	}
	
	public Long getDev_Id() {
		return dev_Id;
	}
	public void setDev_Id(Long dev_Id) {
		this.dev_Id = dev_Id;
	}
	
	public Long getApp_Id() {
		return app_Id;
	}
	public void setApp_Id(Long app_Id) {
		this.app_Id = app_Id;
	}
	public String getApp_Name() {
		return app_Name;
	}
	public void setApp_Name(String app_Name) {
		this.app_Name = app_Name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}	
	public double getConfirm_Money() {
		return confirm_Money;
	}
	public void setConfirm_Money(double confirm_Money) {
		this.confirm_Money = confirm_Money;
	}
	
	public double getDev_Cost() {
		return dev_Cost;
	}

	public void setDev_Cost(double dev_Cost) {
		this.dev_Cost = dev_Cost;
	}

	public String getManage_Name() {
		return manage_Name;
	}
	public void setManage_Name(String manage_Name) {
		this.manage_Name = manage_Name;
	}
	
	
	public Date getCreate_Time() {
		return create_Time;
	}

	public void setCreate_Time(Date create_Time) {
		this.create_Time = create_Time;
	}

	public DevDevmonDetail() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getFinance_Name() {
		return finance_Name;
	}


	public void setFinance_Name(String finance_Name) {
		this.finance_Name = finance_Name;
	}
	
}
