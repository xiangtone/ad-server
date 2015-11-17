package cn.adwalker.ad.service.vo;

import java.io.Serializable;

public class CInfoVo implements Serializable{
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -1550865768563707787L;
	private String app_key;
	private String dev_user_id;
	private String client_ip;
	private String idfa;
	private String idfv;
	private String ad_id;
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getDev_user_id() {
		return dev_user_id;
	}
	public void setDev_user_id(String dev_user_id) {
		this.dev_user_id = dev_user_id;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getIdfv() {
		return idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
}
