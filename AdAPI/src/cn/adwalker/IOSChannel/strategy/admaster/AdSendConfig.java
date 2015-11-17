package cn.adwalker.IOSChannel.strategy.admaster;

import java.io.Serializable;
import java.util.List;

import cn.adwalker.IOSChannel.strategy.AreaSchemeInfo;
/**
 * 
 * @author adwalkerji
 *
 */
public class AdSendConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4566711854680252595L;
	private long ad_id; 			 //广告主键
	private Long scheme_id;			 //方案配置表主键
	private Integer ad_ipsegment_num;//ip段激活数限制
	private Integer ad_ip_num;		 //ip激活数限制
	private Integer ad_bssid_num;	 //同一个路由标示激活数
	private Integer ad_latlon_num;	 //同一经纬度下激活数
	private List<AreaSchemeInfo> schemeInfo;//区域配置详情
	public long getAd_id() {
		return ad_id;
	}
	public void setAd_id(long ad_id) {
		this.ad_id = ad_id;
	}
	public Integer getAd_ipsegment_num() {
		return ad_ipsegment_num;
	}
	public void setAd_ipsegment_num(Integer ad_ipsegment_num) {
		this.ad_ipsegment_num = ad_ipsegment_num;
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
	public Integer getAd_ip_num() {
		return ad_ip_num;
	}
	public void setAd_ip_num(Integer ad_ip_num) {
		this.ad_ip_num = ad_ip_num;
	}
	public List<AreaSchemeInfo> getSchemeInfo() {
		return schemeInfo;
	}
	public void setSchemeInfo(List<AreaSchemeInfo> schemeInfo) {
		this.schemeInfo = schemeInfo;
	}
	public Long getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(Long scheme_id) {
		this.scheme_id = scheme_id;
	}
	
	

}
