package cn.adwalker.ad.beans;

import cn.adwalker.ad.bean.Data;

public class ApiAdInfo extends Data {
	private static final long serialVersionUID = 1L;
	private Long ad_id;//广告id
	private String icon_url; //广告icon
	private String create_date; //时间
	private String ad_name; //广告名称
	private String slogan; //广告语
	private String click_url; //广告连接
	private String store_url; //广告连接
	private String task_desc;
	private int score; 
	private String score_unit; 
	
	public Long getAd_id() {
		return ad_id;
	}
	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	public String getTask_desc() {
		return task_desc;
	}
	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}
	public String getScore_unit() {
		return score_unit;
	}
	public void setScore_unit(String score_unit) {
		this.score_unit = score_unit;
	}
	public String getClick_url() {
		return click_url;
	}
	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}
	public String getStore_url() {
		return store_url;
	}
	public void setStore_url(String store_url) {
		this.store_url = store_url;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
