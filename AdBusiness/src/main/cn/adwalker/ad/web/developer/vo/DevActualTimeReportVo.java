package cn.adwalker.ad.web.developer.vo;

import java.io.Serializable;

import cn.adwalker.ad.util.ObjectUtils;

public class DevActualTimeReportVo implements Serializable {

	private static final long serialVersionUID = 8465237714859413876L;

	private String static_hour;

	private String static_date;
	
	private Integer pv;

	private Integer click;

	private Integer activate;

	private Double click_rate;

	private Double cost;
	
	public String getStatic_hour() {
		return static_hour;
	}

	public void setStatic_hour(String static_hour) {
		this.static_hour = static_hour;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

	public Double getClick_rate() {
		if (ObjectUtils.isNotEmpty(click) && ObjectUtils.isNotEmpty(pv)) {
			click_rate = Double.valueOf(click) / Double.valueOf(pv) * 100;
		}
		return click_rate;
	}

	public void setClick_rate(Double click_rate) {
		this.click_rate = click_rate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}