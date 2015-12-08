package cn.adwalker.ad.param;

import cn.adwalker.ad.picker.vo.ParamVo;

public class PayScoreParam extends ParamVo {
	private Long appId;
	private String uuid;
	private String mac;
	private String channel;//渠道
	private String openudid;
	private Integer consumeScore;//消耗积分
	private String os;
	
	public void setopenudid(String yjfOpenudid) {
		openudid = yjfOpenudid;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	
	
	public void setConsumeScore(Integer consumeScore) {
		this.consumeScore = consumeScore;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOpenudid() {
		return openudid;
	}
	
	public Integer getConsumeScore() {
		return consumeScore;
	}
	
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
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
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
}
