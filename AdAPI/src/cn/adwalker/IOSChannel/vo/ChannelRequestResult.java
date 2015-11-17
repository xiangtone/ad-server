/**
 * <p>Title: ChannelRequestResult.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2012-12-21
 * @version 1.0
 */
package cn.adwalker.IOSChannel.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: ChannelRequestResult
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2012-12-21
 */
public class ChannelRequestResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3963381776388603720L;
	private long id;
	private Integer page_type;
	private String ad_key;
	private String application_key;
	private String adId;
	private String deviceId;
	private String source;
	private Long statDate;
	private String mac;
	private String activiteDate;
	private Integer status;
	private Date createTime;
	private Integer activiteStatus;
	private String sdkversion;
	private String channel;
	private String adSendType;	
	private String udid;
	private String client_ip;

	
	//add by jief 2013-09-03
	private String openUDID; //ios openUDID
	private String idfa;	 //ios idfa
	private String idfv;	 //ios idfv
	private String os_version;		 //os_version
	private String callback_url;//回调接口
	private String app_key;		//应用id
	private String redirect;	//是否跳转1、是，0、否
	
	private String ssid;		//wifi name
	private String bssid;		//wifi 地址
	private String phoneName;	//电话名称
	private String latitude;	//纬度
	private String longitude;	//经度
	private Double price;		//广告投放单价
	private Double in_price;	//广告接入单价
	private String area_code;	//地理区域
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public String getApplication_key() {
		return application_key;
	}

	public void setApplication_key(String application_key) {
		this.application_key = application_key;
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

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSource() {
		return source;
	}

	public Long getStatDate() {
		return statDate;
	}

	public void setStatDate(Long statDate) {
		this.statDate = statDate;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getActiviteDate() {
		return activiteDate;
	}

	public void setActiviteDate(String activiteDate) {
		this.activiteDate = activiteDate;
	}

	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getActiviteStatus() {
		return activiteStatus;
	}

	public void setActiviteStatus(Integer activiteStatus) {
		this.activiteStatus = activiteStatus;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAdSendType() {
		return adSendType;
	}

	public void setAdSendType(String adSendType) {
		this.adSendType = adSendType;
	}

	public Integer getPage_type() {
		return page_type;
	}

	public void setPage_type(Integer page_type) {
		this.page_type = page_type;
	}

	public String getAd_key() {
		return ad_key;
	}

	public void setAd_key(String ad_key) {
		this.ad_key = ad_key;
	}

	public String getSdkversion() {
		return sdkversion;
	}

	public void setSdkversion(String sdkversion) {
		this.sdkversion = sdkversion;
	}

	public String getOpenUDID() {
		return openUDID;
	}

	public void setOpenUDID(String openUDID) {
		this.openUDID = openUDID;
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


	public String getCallback_url() {
		return callback_url;
	}

	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getIn_price() {
		return in_price;
	}

	public void setIn_price(Double in_price) {
		this.in_price = in_price;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
}
