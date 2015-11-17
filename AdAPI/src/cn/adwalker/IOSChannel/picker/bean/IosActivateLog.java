package cn.adwalker.IOSChannel.picker.bean;

import java.util.Date;

public class IosActivateLog {
	private Integer id;
	private String ad_id;
	private String mac;
	private Integer activite_date;
	private String channel;
	private Integer status;
	private Date create_time;
	private Date click_time;
	private Integer activite_status;
	private String application_key;
	private Integer page_type;
	private String ad_key;
	private String sdkversion;
	private String client_ip;
	private String openudid;
	private String idfa;
	private String idfv;
	private String os_version;
	private Integer action_id;
	private String area_code;
	private Double price;
	private String ssid;			//wifi name
	private String bssid;		//wifi 地址
	private String phoneName;	//电话名称
	private String latitude;		//纬度
	private String longitude;	//经度
	private Double in_price;	//接入单价
	
	public Date getClick_time() {
		return click_time;
	}
	public void setClick_time(Date clickTime) {
		click_time = clickTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String areaCode) {
		area_code = areaCode;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String adId) {
		ad_id = adId;
	}
	public Integer getActivite_date() {
		return activite_date;
	}
	public void setActivite_date(Integer activiteDate) {
		activite_date = activiteDate;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
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
	public String getOpenudid() {
		return openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
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
