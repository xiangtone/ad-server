package cn.adwalker.ad.web.common.bean;

import java.util.Date;

public class DevIncomeBean {

	private Date static_date;
	private Integer activate;
	private Double sumCost;
	private Double cost0;
	private Double cost1;
	private Double cost5;
	public Date getStatic_date() {
		return static_date;
	}
	public void setStatic_date(Date staticDate) {
		static_date = staticDate;
	}
	public Integer getActivate() {
		return activate;
	}
	public void setActivate(Integer activate) {
		this.activate = activate;
	}
	public Double getSumCost() {
		return sumCost;
	}
	public void setSumCost(Double sumCost) {
		this.sumCost = sumCost;
	}
	public Double getCost0() {
		return cost0;
	}
	public void setCost0(Double cost0) {
		this.cost0 = cost0;
	}
	public Double getCost1() {
		return cost1;
	}
	public void setCost1(Double cost1) {
		this.cost1 = cost1;
	}
	public Double getCost5() {
		return cost5;
	}
	public void setCost5(Double cost5) {
		this.cost5 = cost5;
	}
	
	
}
