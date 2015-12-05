package cn.adwalker.ad.admin.app.bean;

import java.io.Serializable;

public class SelCampaignQueryBean implements Serializable {
	
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 2477446097765449770L;
	
	private Long app_id;
	
	private String name;
	
	private String	code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
}
