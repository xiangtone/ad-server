package cn.adwalker.IOSChannel.picker.bean;

import java.io.Serializable;
import java.util.Date;

public class IosActionLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String adId;
	private String mac;
	private Integer stat_date;
	private String channel;
	private Integer status;
	private Integer activite_status;
	private Date createTime;
	private String openudid;
	private String idfa;
	private String idfv;
	private Integer page_type;
	private String os_version;
	private String client_ip;
	private String application_key;
	private String callback_url;
	private String ad_key;
	private String area_code;
	//add by jief 2014-06-18
	private String ssid;			//wifi name
	private String bssid;		//wifi 地址
	private String phoneName;	//电话名称
	private String latitude;		//纬度
	private String longitude;	//经度
	private Double in_price;		//接入单价
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String areaCode) {
		area_code = areaCode;
	}
	public String getAd_key() {
		return ad_key;
	}
	public void setAd_key(String adKey) {
		ad_key = adKey;
	}
	public String getCallback_url() {
		return callback_url;
	}
	public void setCallback_url(String callbackUrl) {
		callback_url = callbackUrl;
	}
	public String getApplication_key() {
		return application_key;
	}
	public void setApplication_key(String applicationKey) {
		application_key = applicationKey;
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
	public Integer getPage_type() {
		return page_type;
	}
	public void setPage_type(Integer pageType) {
		page_type = pageType;
	}
	public Integer getActivite_status() {
		return activite_status;
	}
	public void setActivite_status(Integer activiteStatus) {
		activite_status = activiteStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Integer getStat_date() {
		return stat_date;
	}
	public void setStat_date(Integer statDate) {
		stat_date = statDate;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
