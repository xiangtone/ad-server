package cn.adwalker.ad.admin.app.vo;



public class AdSendConfigVo {
	private Long id;
	private String campaignName;
	private String os;
	private Integer status;
	private Integer pcStatus;
	
	private Long schemeId;//方案
	private Integer adIpsegmentNum;//ip段激活数据限制
	private Integer adIpNum;//ip激活数据限制
	private Integer adBssidNum;//bssid激活数限制
	private Integer adLatlonNum;//同意经纬度下激活数限制
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPcStatus() {
		return pcStatus;
	}
	public void setPcStatus(Integer pcStatus) {
		this.pcStatus = pcStatus;
	}
	public Long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}
	public Integer getAdIpsegmentNum() {
		return adIpsegmentNum;
	}
	public void setAdIpsegmentNum(Integer adIpsegmentNum) {
		this.adIpsegmentNum = adIpsegmentNum;
	}
	public Integer getAdIpNum() {
		return adIpNum;
	}
	public void setAdIpNum(Integer adIpNum) {
		this.adIpNum = adIpNum;
	}
	public Integer getAdBssidNum() {
		return adBssidNum;
	}
	public void setAdBssidNum(Integer adBssidNum) {
		this.adBssidNum = adBssidNum;
	}
	public Integer getAdLatlonNum() {
		return adLatlonNum;
	}
	public void setAdLatlonNum(Integer adLatlonNum) {
		this.adLatlonNum = adLatlonNum;
	}
	
	
	
	
}
