package cn.adwalker.ad.param;

import cn.adwalker.ad.picker.vo.ParamVo;
public class PageParam extends ParamVo {
	private Long appId;
	private Integer pageNo;//页码
	private String uuid;
	private String mac;
	private String channel;//渠道
	private String page_type;//广告的分类(区分 墙 插屏  banner)
	private Integer pageSize;
	private String image_type;
	private String terminalType;
	private Integer isSign;//是否签到
	private String openudid;
	private String idfa;
	private String noticeType;
	private String appkey;
	private String ssid;
	private String bssid;
	private String telModel;
	private String os;
	private String phoneName;
	private String latitude;
	private String longitude;
	private String quickly_task;
	
	
	public String getSsid() {
		return ssid;
	}
	
	public String getBssid() {
		return bssid;
	}
	
	public String getTelModel() {
		return telModel;
	}
	
	public String getAppkey() {
		return appkey;
	}
	
	public String getNoticeType() {
		return noticeType;
	}
	
	public String getVersion() {
		return version;
	}

	public String getOpenudid() {
		return openudid;
	}
	
	public String getIdfa() {
		return idfa;
	}
	
	public Long getAppId() {
		return appId;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public String getMac() {
		return mac;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public String getPage_type() {
		return page_type;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	
	public String getImage_type() {
		return image_type;
	}
	
	public String getTerminalType() {
		return terminalType;
	}
	
	
	public Integer getIsSign() {
		return isSign;
	}
	
	public String getPhoneName() {
		return phoneName;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public String getQuickly_task() {
		return quickly_task;
	}
	

	
	public void setopenudid(String yjfOpenudid) {
		openudid = yjfOpenudid;
	}
	
	public void setidfa(String yjfIdfa) {
		idfa = yjfIdfa;
	}
	
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	
	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	
	public void setTelModel(String telModel) {
		this.telModel = telModel;
	}
	
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public void setPage_type(String pageType) {
		page_type = pageType;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public void setImage_type(String imageType) {
		image_type = imageType;
	}
	
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	
	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}
	
	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setQuickly_task(String quickly_task) {
		this.quickly_task = quickly_task;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
}
