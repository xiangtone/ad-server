package cn.adwalker.ad.rules.bean;

import java.util.List;

import cn.adwalker.ad.cache.element.UserAdRel;

public class ScoreConfigBean {
	private	double price;
	private int exchange_rate_rmb;
	private float scale;
	private List<UserAdRel> userAdRels;
	private Long ad_id;
	private String quickly_task;//是否是快速任务
	private Integer quickly_task_rate;
	
	
	public ScoreConfigBean(double price, int exchange_rate_rmb, float scale,List<UserAdRel> userAdRels, Long ad_id, String quickly_task, Integer quickly_task_rate) {
		super();
		this.price = price;
		this.exchange_rate_rmb = exchange_rate_rmb;
		this.scale = scale;
		this.userAdRels = userAdRels;
		this.ad_id = ad_id;
		this.quickly_task=quickly_task;
		this.quickly_task_rate=quickly_task_rate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getExchange_rate_rmb() {
		return exchange_rate_rmb;
	}
	public void setExchange_rate_rmb(int exchange_rate_rmb) {
		this.exchange_rate_rmb = exchange_rate_rmb;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public List<UserAdRel> getUserAdRels() {
		return userAdRels;
	}
	public void setUserAdRels(List<UserAdRel> userAdRels) {
		this.userAdRels = userAdRels;
	}
	public Long getAd_id() {
		return ad_id;
	}
	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
	public String getQuickly_task() {
		return quickly_task;
	}
	public void setQuickly_task(String quickly_task) {
		this.quickly_task = quickly_task;
	}
	public Integer getQuickly_task_rate() {
		return quickly_task_rate;
	}
	public void setQuickly_task_rate(Integer quickly_task_rate) {
		this.quickly_task_rate = quickly_task_rate;
	}
}
