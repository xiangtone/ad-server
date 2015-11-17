package cn.adwalker.IOSChannel.picker.vo;

public class IosClick extends ParamVo implements  java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String appid;//表示某个广告
	public String mac;
	public String appId;
	public String deviceid;
	public String source;
	public String udid;
	public String client_ip;
	public String callback;
	public String app_key;
	public String ad_key;
	public Integer page_type;
	public String sdkVersion;
	public String OPENUDID;
	public String IDFA;
	public String IDFV;
	public String os;
	public String eventtime;
	public String areaCode;
	public String ssid;			//wifi name
	public String bssid;		//wifi 地址
	public String phoneName;	//电话名称
	public String latitude;		//纬度
	public String longitude;	//经度
	public Double in_price;		//接入单价
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String clientIp) {
		client_ip = clientIp;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String appKey) {
		app_key = appKey;
	}
	public String getAd_key() {
		return ad_key;
	}
	public void setAd_key(String adKey) {
		ad_key = adKey;
	}
	public Integer getPage_type() {
		return page_type;
	}
	public void setPage_type(Integer pageType) {
		page_type = pageType;
	}
	public String getSdkVersion() {
		return sdkVersion;
	}
	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}
	public String getOPENUDID() {
		return OPENUDID;
	}
	public void setOPENUDID(String oPENUDID) {
		OPENUDID = oPENUDID;
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
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getEventtime() {
		return eventtime;
	}
	public void setEventtime(String eventtime) {
		this.eventtime = eventtime;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getBssid() {
		return bssid;
	}
	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	public String getPhoneName() {
		return phoneName;
	}
	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Double getIn_price() {
		return in_price;
	}
	public void setIn_price(Double in_price) {
		this.in_price = in_price;
	}
}
