package cn.adwalker.ad.web.developer.vo;

import java.io.Serializable;

public class DevIncomeCountVo implements Serializable {

	private static final long serialVersionUID = 8465237714859413876L;

	private String static_date;

	private String cost;
	
	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
}