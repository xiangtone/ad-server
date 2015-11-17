package cn.adwalker.ad.param;

import cn.adwalker.ad.picker.vo.ParamVo;

public class AdDetailParam extends ParamVo {
	private Long appId;
	private Long adId;
	private String uuid;
	private String channel;//渠道
	private String page_type;//广告的分类(区分 墙 插屏  banner)
	private String terminalType;
	private String imsi;
	
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setPage_type(String pageType) {
		page_type = pageType;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getAdId() {
		return adId;
	}
	
	public String getImsi() {
		return imsi;
	}
	
	public String getVersion() {
		return version;
	}

	

	public Long getAppId() {
		return appId;
	}

	
	public String getUuid() {
		return uuid;
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
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	
	public void setAppId(Long appId) {
		this.appId = appId;
	}
}
