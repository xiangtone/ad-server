package cn.adwalker.ad.admin.app.bean;


public class Blackbean {
	//主键
	private Long app_id;
	//活动名称
	private String app_name;
	//平台类型
	private String os;
	private Integer app_res;
	private Long app_manager_id;
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
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Integer getApp_res() {
		return app_res;
	}
	public void setApp_res(Integer app_res) {
		this.app_res = app_res;
	}
	public Long getApp_manager_id() {
		return app_manager_id;
	}
	public void setApp_manager_id(Long app_manager_id) {
		this.app_manager_id = app_manager_id;
	}
	
}
