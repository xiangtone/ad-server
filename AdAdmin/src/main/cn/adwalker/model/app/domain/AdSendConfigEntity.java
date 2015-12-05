package cn.adwalker.model.app.domain;

import cn.adwalker.core.repository.Entity;

public class AdSendConfigEntity implements Entity{
	private static final long serialVersionUID = -1492020477622665074L;
	
	private Long ad_id;//广告配置主键
	private Long scheme_id;//方案id
	private Integer ad_ipsegment_num;//ip段激活数据限制
	private Integer ad_ip_num;//ip激活数据限制
	private Integer ad_bssid_num;//bssid激活数限制
	private Integer ad_latlon_num;//同意经纬度下激活数限制
	
	public Long getAd_id() {
		return ad_id;
	}
	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
	public Long getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(Long scheme_id) {
		this.scheme_id = scheme_id;
	}
	public Integer getAd_ipsegment_num() {
		return ad_ipsegment_num;
	}
	public void setAd_ipsegment_num(Integer ad_ipsegment_num) {
		this.ad_ipsegment_num = ad_ipsegment_num;
	}
	public Integer getAd_ip_num() {
		return ad_ip_num;
	}
	public void setAd_ip_num(Integer ad_ip_num) {
		this.ad_ip_num = ad_ip_num;
	}
	public Integer getAd_bssid_num() {
		return ad_bssid_num;
	}
	public void setAd_bssid_num(Integer ad_bssid_num) {
		this.ad_bssid_num = ad_bssid_num;
	}
	public Integer getAd_latlon_num() {
		return ad_latlon_num;
	}
	public void setAd_latlon_num(Integer ad_latlon_num) {
		this.ad_latlon_num = ad_latlon_num;
	}
	
	
}
