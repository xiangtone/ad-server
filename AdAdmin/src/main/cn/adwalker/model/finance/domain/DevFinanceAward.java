package cn.adwalker.model.finance.domain;

import java.util.Date;
public class DevFinanceAward {
	private Long id;
	private Double award_money;
	private Date begin_day;
	private Date end_day;
	private String activityStatu;
	
	public String getActivityStatu() {
		return activityStatu;
	}
	public void setActivityStatu(String activityStatu) {
		this.activityStatu = activityStatu;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAward_money() {
		return award_money;
	}
	public void setAward_money(Double award_money) {
		this.award_money = award_money;
	}
	public Date getBegin_day() {
		return begin_day;
	}
	public void setBegin_day(Date begin_day) {
		this.begin_day = begin_day;
	}
	public Date getEnd_day() {
		return end_day;
	}
	public void setEnd_day(Date end_day) {
		this.end_day = end_day;
	}
	
	
}
