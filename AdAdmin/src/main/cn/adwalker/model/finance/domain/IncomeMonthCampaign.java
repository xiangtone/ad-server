package cn.adwalker.model.finance.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
* <p>Title: CampaignConfirm</p>
* <p>Description:确认收入</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年11月14日
 */
public class IncomeMonthCampaign implements Entity{
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1L;
	//id
	private Long id;
	//活动id
	private Long campaign_id;
	//操作人id
	private Long operater_id;
	//录入人id
	private Long manager_id;
	//效果发生开始时间
	private String month_stat_date;
	//效果发生结束时间
	private String month_end_date;
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
	
	private Date create_time;
	
	private Date update_time;
	
	//结算方式
	private String balance_model;
	//结算备注
	private String income_remark;
	
	//发票状态，为开发票
	private Integer invoice_status;
	//回款状态0未回款
	private Integer payment_status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public Long getOperater_id() {
		return operater_id;
	}
	public void setOperater_id(Long operater_id) {
		this.operater_id = operater_id;
	}
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	public String getMonth_stat_date() {
		return month_stat_date;
	}
	public void setMonth_stat_date(String month_stat_date) {
		this.month_stat_date = month_stat_date;
	}
	public String getMonth_end_date() {
		return month_end_date;
	}
	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getBalance_model() {
		return balance_model;
	}
	public void setBalance_model(String balance_model) {
		this.balance_model = balance_model;
	}
	public String getIncome_remark() {
		return income_remark;
	}
	public void setIncome_remark(String income_remark) {
		this.income_remark = income_remark;
	}
	public Integer getInvoice_status() {
		return invoice_status;
	}
	public void setInvoice_status(Integer invoice_status) {
		this.invoice_status = invoice_status;
	}
	public Integer getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(Integer payment_status) {
		this.payment_status = payment_status;
	}
	
	
	
}
