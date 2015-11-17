package cn.adwalker.ad.admin.finance.vo;

public class DevmonDetailVo {
	
	private Long ad_Id;//广告ID
	private String ad_Name;//广告名称
	private Long dev_Id;//开发者Id
	private String dev_Name;//开发者账号
	private Long app_Id;//应用ID
	private String app_Name;//应用名称
	private String statDateStartTime;//统计开始时间：效果发生时间
	private String statDateEndTime;//统计结束时间
	private String settleStartTime;//结算  开始时间
	private String settleEndTime;//结算 结束时间
	private Integer status;//结算类型
	
	
	
	public Long getAd_Id() {
		return ad_Id;
	}
	public void setAd_Id(Long ad_Id) {
		this.ad_Id = ad_Id;
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
	public String getDev_Name() {
		return dev_Name;
	}
	public void setDev_Name(String dev_Name) {
		this.dev_Name = dev_Name;
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
	public String getStatDateStartTime() {
		return statDateStartTime;
	}
	public void setStatDateStartTime(String statDateStartTime) {
		this.statDateStartTime = statDateStartTime;
	}
	public String getStatDateEndTime() {
		return statDateEndTime;
	}
	public void setStatDateEndTime(String statDateEndTime) {
		this.statDateEndTime = statDateEndTime;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public DevmonDetailVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
