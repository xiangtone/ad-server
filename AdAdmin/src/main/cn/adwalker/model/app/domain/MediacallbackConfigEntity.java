package cn.adwalker.model.app.domain;

import cn.adwalker.core.repository.Entity;


public class MediacallbackConfigEntity implements Entity {

	private static final long serialVersionUID = 1221354956344042409L;
	private Long appid;//媒体的主键
	private Long scheme_id;//方案id
	private Double detain_rate;//媒介的扣量比例
	private Integer ipsegment_times;//某ip段下广告数数值=N*广告数
	private Integer ip_times;//某ip下广告激活数数值=N*广告数 此值小于ip段设置
	private Integer  ca_time_ratio;//点击和激活时间间隔计算系数 (app size(M)/N +10)
	
	public Long getAppid() {
		return appid;
	}
	public void setAppid(Long appid) {
		this.appid = appid;
	}
	public Double getDetain_rate() {
		return detain_rate;
	}
	public void setDetain_rate(Double detain_rate) {
		this.detain_rate = detain_rate;
	}
	public Long getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(Long scheme_id) {
		this.scheme_id = scheme_id;
	}
	public Integer getIpsegment_times() {
		return ipsegment_times;
	}
	public void setIpsegment_times(Integer ipsegment_times) {
		this.ipsegment_times = ipsegment_times;
	}
	public Integer getIp_times() {
		return ip_times;
	}
	public void setIp_times(Integer ip_times) {
		this.ip_times = ip_times;
	}
	public Integer getCa_time_ratio() {
		return ca_time_ratio;
	}
	public void setCa_time_ratio(Integer ca_time_ratio) {
		this.ca_time_ratio = ca_time_ratio;
	}
	
	
}
