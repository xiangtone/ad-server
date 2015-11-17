package cn.adwalker.ad.admin.finance.form;

import java.util.Date;

/**
* <p>Title: CampaignConfirmForm</p>
* <p>Description:确认收入form</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-8
 */
public class CampaignConfirmFrom {
	
	//id
	private Long id;
	//活动id
	private Long campaign_id;
	//录入人id
	private Long manager_id;
	//效果发生开始时间
	private String month_stat_date;
	//效果发生结束时间
	private String month_end_date;
	//广告主确认数
	private Integer income_amount;
	//确认钱
	private double income_money;
	//单价
	private double price;
	//状态0:未发布，1：已发布
	private Integer status;
	//预确认的钱
	private Double forecast_money;
	//预确认的数
	private Integer forecast_amount;
	
	//备注
	private String income_remark;
	
	private Date create_time;
	
	public Long getCampaign_id() {
		
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getMonth_stat_date() {
		return month_stat_date;
	}
	public void setMonth_stat_date(String month_stat_date) {
		this.month_stat_date = month_stat_date;
	}
	
	public Integer getIncome_amount() {
		return income_amount;
	}
	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
	}
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public double getIncome_money() {
		return income_money;
	}
	public void setIncome_money(double income_money) {
		this.income_money = income_money;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMonth_end_date() {
		return month_end_date;
	}
	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
	}
	
	public Double getForecast_money() {
		return forecast_money;
	}
	public void setForecast_money(Double forecast_money) {
		this.forecast_money = forecast_money;
	}
	public Integer getForecast_amount() {
		return forecast_amount;
	}
	public void setForecast_amount(Integer forecast_amount) {
		this.forecast_amount = forecast_amount;
	}
	public String getIncome_remark() {
		return income_remark;
	}
	public void setIncome_remark(String income_remark) {
		this.income_remark = income_remark;
	}
	
}
