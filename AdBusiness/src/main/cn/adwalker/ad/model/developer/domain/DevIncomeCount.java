package cn.adwalker.ad.model.developer.domain;

import java.io.Serializable;

public class DevIncomeCount implements Serializable {

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