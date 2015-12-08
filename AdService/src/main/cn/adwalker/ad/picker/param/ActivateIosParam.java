package cn.adwalker.ad.picker.param;

import cn.adwalker.ad.picker.vo.ParamVo;

public class ActivateIosParam extends ParamVo {
	public Long appId;
	public String uuid;
	public Long adId;
	public String deveiceId;
	public String devUserId;
	public String page_type;
	public String channel;
	public String idfa;
	public String version;
	public String ip;
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public String getDeveiceId() {
		return deveiceId;
	}
	public void setDeveiceId(String deveiceId) {
		this.deveiceId = deveiceId;
	}
	public String getDevUserId() {
		return devUserId;
	}
	public void setDevUserId(String devUserId) {
		this.devUserId = devUserId;
	}
	public String getPage_type() {
		return page_type;
	}
	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}