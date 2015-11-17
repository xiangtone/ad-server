package cn.adwalker.IOSChannel.picker.vo;

import java.util.Date;

public class IosActivate extends ParamVo {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -7599117452554058878L;
	public String appid;
	public String deviceid;
	public String device;
	public String mac;
	public String source;
	public String OPENUDID;
	public String IDFA;
	public String idfa;
	public String IDFV;
	public String DATA;
	public Integer activite_date;
	public Integer status;
	public Date create_time;
	public Integer activite_status;
	public String application_key;
	public Integer page_type;
	public String ad_key;
	public String sdkversion;
	public String client_ip;
	public String os_version;
	public Integer action_id;
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getOPENUDID() {
		return OPENUDID;
	}
	public void setOPENUDID(String oPENUDID) {
		OPENUDID = oPENUDID;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public Integer getActivite_date() {
		return activite_date;
	}
	public void setActivite_date(Integer activiteDate) {
		activite_date = activiteDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public Integer getActivite_status() {
		return activite_status;
	}
	public void setActivite_status(Integer activiteStatus) {
		activite_status = activiteStatus;
	}
	public String getApplication_key() {
		return application_key;
	}
	public void setApplication_key(String applicationKey) {
		application_key = applicationKey;
	}
	public Integer getPage_type() {
		return page_type;
	}
	public void setPage_type(Integer pageType) {
		page_type = pageType;
	}
	public String getAd_key() {
		return ad_key;
	}
	public void setAd_key(String adKey) {
		ad_key = adKey;
	}
	public String getSdkversion() {
		return sdkversion;
	}
	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String clientIp) {
		client_ip = clientIp;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String osVersion) {
		os_version = osVersion;
	}
	public Integer getAction_id() {
		return action_id;
	}
	public void setAction_id(Integer actionId) {
		action_id = actionId;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIDFA() {
		return IDFA;
	}
	public void setIDFA(String iDFA) {
		IDFA = iDFA;
	}
	public String getIDFV() {
		return IDFV;
	}
	public void setIDFV(String iDFV) {
		IDFV = iDFV;
	}
	public String getDATA() {
		return DATA;
	}
	public void setDATA(String dATA) {
		DATA = dATA;
	}

}
