package cn.adwalker.model.finance.domain;


public class DevFinanceAwardVoLog {
	private Long ID;
	private Long dev_id;
	private String dev_name;
	private Long app_id;
	private String app_name;
	private Double award_money;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public Double getAward_money() {
		return award_money;
	}
	public void setAward_money(Double award_money) {
		this.award_money = award_money;
	}
	public Long getDev_id() {
		return dev_id;
	}
	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	
}
