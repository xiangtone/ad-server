package cn.adwalker.ad.control.domain;

import java.util.Date;


public class User {
	private String uuid;
	private String telModel;
	private String operator;
	private String os;
	private String appId;
	private String openudid;
	private String idfv;
	private Date createTime;
	private String imei;
	private String netEnv;
	private String areaCode;
	private String brand;
	private String imsi;
	private String areaProvince;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTelModel() {
		return telModel;
	}
	public void setTelModel(String telModel) {
		this.telModel = telModel;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOpenudid() {
		return openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public String getIdfv() {
		return idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getNetEnv() {
		return netEnv;
	}
	public void setNetEnv(String netEnv) {
		this.netEnv = netEnv;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getAreaProvince() {
		return areaProvince;
	}
	public void setAreaProvince(String areaProvince) {
		this.areaProvince = areaProvince;
	}
	
	
	
}
