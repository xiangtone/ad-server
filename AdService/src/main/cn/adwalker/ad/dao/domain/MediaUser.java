package cn.adwalker.ad.dao.domain;

import java.io.Serializable;

public class MediaUser implements Serializable {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1147009552643630806L;
	
	private String uuid;
	private String string;
	private String app_id;
	private Integer status;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
