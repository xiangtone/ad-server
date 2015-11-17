package cn.adwalker.IOSChannel.param;

import java.io.Serializable;

public class ClickParam implements Serializable {

	/** @Fields serialVersionUID : 序列化*/
	private static final long serialVersionUID = 4838740877943218856L;
	private String res_code;//来源标识：有米、触控
	private String s;//签名---根据签名及附加码计算
	private String uuid;//用户标识
	private String mac;//设备号
	private String udid;//udit
	private String idfa;//idfa
	private Long ad_id;//广告id
	private Long app_id;//应用id
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getRes_code() {
		return res_code;
	}
	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}
	public Long getAd_id() {
		return ad_id;
	}
	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
}
