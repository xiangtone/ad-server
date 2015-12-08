package cn.adwalker.ad.param;

import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.vo.ParamVo;

public class AcParam extends ParamVo {
	private Long appId;
	private String uuid;
	private String mac;
	private String channel;//渠道
	private String page_type;//广告的分类(区分 墙 插屏  banner)
	private String terminalType;
	private String openudid;
	private String idfa;
	private String yjf_idfa;
	private String idfv;
	private String id;
	private String ids;
	private String ac;//行为编码
	private Integer bannerTag;
	private String imsi;
	private String devUserId;
	private String ssid;
	private String bssid;
	private String os;
	private String phoneName;
	private String latitude;
	private String longitude;
	
	public String getIds() {
		return StringUtil.dealNull(ids,id);//参数处理
	}
	
	public String getSsid() {
		return ssid;
	}
	
	public String getBssid() {
		return bssid;
	}


	public String getYjf_idfa() {
		return yjf_idfa;
	}
	
	public String getImsi() {
		return imsi;
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
	
	public String getIdfv() {
		return idfv;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDevUserId() {
		return devUserId;
	}
	
	public String getAc() {
		return ac;
	}
	public Integer getBannerTag() {
		return bannerTag;
	}
	
	
	public Long getAppId() {
		return appId;
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
	
	public String getTerminalType() {
		return terminalType;
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
	
	public String getOs() {
		return os;
	}
	
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
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
	
	public void setOs(String os) {
		this.os = os;
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

	public void setYjf_idfa(String yjfIdfa) {
		yjf_idfa = yjfIdfa;
	}
	
	public void setidfv(String yjfIdfv) {
		idfv = yjfIdfv;
	}
	
	public void setImsi(String imsi) {
		this.imsi = imsi;
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
	
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	
	public void setDevUserId(String devUserId) {
		this.devUserId = devUserId;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	public void setAc(String ac) {
		this.ac = ac;
	}
	
	public void setBannerTag(Integer bannerTag) {
		this.bannerTag = bannerTag;
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
}
