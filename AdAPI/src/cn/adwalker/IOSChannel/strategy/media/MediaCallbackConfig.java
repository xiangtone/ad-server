package cn.adwalker.IOSChannel.strategy.media;

import java.io.Serializable;
import java.util.List;

import cn.adwalker.IOSChannel.strategy.AreaSchemeInfo;
/**
 * 
 * @author adwalkerji
 *
 */
public class MediaCallbackConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4226251119048470955L;
	private long appid; 			//应用id
	private Double detain_rate; 	//扣量比例
	private Long scheme_id;			//区域方案配置主键
	private Integer ipsegment_times;	//某ip段下广告数数值=N*广告数
	private Integer ip_times;			//某ip下广告数数值=N*广告数
	private Integer ca_time_ratio;		//点击和激活时间间隔计算系数 (app size(M)/N +10)
	private List<AreaSchemeInfo> schemeInfo;//区域配置详情
	public long getAppid() {
		return appid;
	}
	public void setAppid(long appid) {
		this.appid = appid;
	}
	public Double getDetain_rate() {
		return detain_rate;
	}
	public void setDetain_rate(Double detain_rate) {
		this.detain_rate = detain_rate;
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
	public Long getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(Long scheme_id) {
		this.scheme_id = scheme_id;
	}
	public List<AreaSchemeInfo> getSchemeInfo() {
		return schemeInfo;
	}
	public void setSchemeInfo(List<AreaSchemeInfo> schemeInfo) {
		this.schemeInfo = schemeInfo;
	}

}
