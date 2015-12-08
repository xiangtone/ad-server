/**
* <p>Title: SystemConfig.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-6-9
* @version 1.0
*/
package cn.adwalker.ad.cache.element;

import java.io.Serializable;

/**
 * <p>Title: SystemConfig</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-6-9
 */
public class ServiceConfig implements Serializable {
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1565948948939753749L;
	
	private Integer score_ac_limit;//激活限制
	private Integer score_delay_time;//延时时间
	private String score_explain;//积分延时提示
	private Integer banner_interval;//banner轮转时间
	private Integer android_banner_probability;
	private Integer android_plaque_probability;
	private Integer ios_banner_probability;
	private Integer ios_plaque_probability;
	private Double lomark_price;
	private String lomark_pay_type;
	
	//add by jief 2013-10-26
	private String lomark_url;
	private String lomark_key;
	private String lomark_safe_key;
	
	
	private String	ad_res_switch_kuaiyou;
	private String	ad_res_switch_chukong;
	private String	ad_res_switch_youmi;
	private String	ad_res_switch_platform;
	
	private Integer quickly_task;
	
	public Integer getScore_ac_limit() {
		return score_ac_limit;
	}
	public void setScore_ac_limit(Integer score_ac_limit) {
		this.score_ac_limit = score_ac_limit;
	}
	public Integer getScore_delay_time() {
		return score_delay_time;
	}
	public void setScore_delay_time(Integer score_delay_time) {
		this.score_delay_time = score_delay_time;
	}
	public String getScore_explain() {
		return score_explain;
	}
	public void setScore_explain(String score_explain) {
		this.score_explain = score_explain;
	}
	public Integer getBanner_interval() {
		return banner_interval;
	}
	public void setBanner_interval(Integer banner_interval) {
		this.banner_interval = banner_interval;
	}
	public Integer getAndroid_banner_probability() {
		return android_banner_probability;
	}
	public void setAndroid_banner_probability(Integer android_banner_probability) {
		this.android_banner_probability = android_banner_probability;
	}
	public Integer getAndroid_plaque_probability() {
		return android_plaque_probability;
	}
	public void setAndroid_plaque_probability(Integer android_plaque_probability) {
		this.android_plaque_probability = android_plaque_probability;
	}
	public Integer getIos_banner_probability() {
		return ios_banner_probability;
	}
	public void setIos_banner_probability(Integer ios_banner_probability) {
		this.ios_banner_probability = ios_banner_probability;
	}
	public Integer getIos_plaque_probability() {
		return ios_plaque_probability;
	}
	public void setIos_plaque_probability(Integer ios_plaque_probability) {
		this.ios_plaque_probability = ios_plaque_probability;
	}
	public Double getLomark_price() {
		return lomark_price;
	}
	public void setLomark_price(Double lomark_price) {
		this.lomark_price = lomark_price;
	}
	public String getLomark_pay_type() {
		return lomark_pay_type;
	}
	public void setLomark_pay_type(String lomark_pay_type) {
		this.lomark_pay_type = lomark_pay_type;
	}
	public String getLomark_url() {
		return lomark_url;
	}
	public void setLomark_url(String lomark_url) {
		this.lomark_url = lomark_url;
	}
	public String getLomark_key() {
		return lomark_key;
	}
	public void setLomark_key(String lomark_key) {
		this.lomark_key = lomark_key;
	}
	public String getLomark_safe_key() {
		return lomark_safe_key;
	}
	public void setLomark_safe_key(String lomark_safe_key) {
		this.lomark_safe_key = lomark_safe_key;
	}
	public String getAd_res_switch_kuaiyou() {
		return ad_res_switch_kuaiyou;
	}
	public void setAd_res_switch_kuaiyou(String ad_res_switch_kuaiyou) {
		this.ad_res_switch_kuaiyou = ad_res_switch_kuaiyou;
	}
	public String getAd_res_switch_chukong() {
		return ad_res_switch_chukong;
	}
	public void setAd_res_switch_chukong(String ad_res_switch_chukong) {
		this.ad_res_switch_chukong = ad_res_switch_chukong;
	}
	public String getAd_res_switch_youmi() {
		return ad_res_switch_youmi;
	}
	public void setAd_res_switch_youmi(String ad_res_switch_youmi) {
		this.ad_res_switch_youmi = ad_res_switch_youmi;
	}
	public String getAd_res_switch_platform() {
		return ad_res_switch_platform;
	}
	public void setAd_res_switch_platform(String ad_res_switch_platform) {
		this.ad_res_switch_platform = ad_res_switch_platform;
	}
	public Integer getQuickly_task() {
		return quickly_task;
	}
	public void setQuickly_task(Integer quickly_task) {
		this.quickly_task = quickly_task;
	}
}
