package cn.adwalker.ad.admin.app.bean;


public class AppMediaBean {
	//主键
	private Long id;
	private String name;
	private String os;
	private Integer status;
	
	private Double detainRate;//媒介的扣量比例
	private Long schemeId;
	private Integer ipsegmentTimes;//某ip段下广告数数值=N*广告数
	private Integer ipTimes;//某ip下广告激活数数值=N*广告数 此值小于ip段设置
	private Integer  caTimeRatio;//点击和激活时间间隔计算系数 (app size(M)/N +10) 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Double getDetainRate() {
		return detainRate;
	}
	public void setDetainRate(Double detainRate) {
		this.detainRate = detainRate/100;
	}
	public Integer getIpsegmentTimes() {
		return ipsegmentTimes;
	}
	public void setIpsegmentTimes(Integer ipsegmentTimes) {
		this.ipsegmentTimes = ipsegmentTimes;
	}
	public Integer getIpTimes() {
		return ipTimes;
	}
	public void setIpTimes(Integer ipTimes) {
		this.ipTimes = ipTimes;
	}
	
	public Long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}
	public Integer getCaTimeRatio() {
		return caTimeRatio;
	}
	public void setCaTimeRatio(Integer caTimeRatio) {
		this.caTimeRatio = caTimeRatio;
	}
}
