package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

public class CostVo {
	private Long id;
	private Integer invoice_status; // 开票状态
	private Integer payment_status; // 付款状态
	private Long channel_id;// 渠道id
	private String channel_name;// 渠道名称
	private Long campaign_id;// 活动id
	private String campaign_name;// 活动名称
	private String os;// 平台类型
	private String month_stat_date; //录入开始时间
	private String month_end_date; //录入结束时间
	private String balance_model;// 结算形式
	private Double price;// 单价
	private Long forecast_amount; // 预确认数
	private Double forecast_money; // 预确认金额
	private Double income_money; // 结算金额
	private String name;// 销售人员
	private String channe_manager;// 渠道人员
	private String payment_remark; // 付款备注
	private String income_remark; // 结算备注
	private String invoice_remark; // 发票备注
	private Integer status; // 0:未发布，1：已发布
	private Date income_time; // 结算时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
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

	public String getBalance_model() {
		return balance_model;
	}

	public void setBalance_model(String balance_model) {
		this.balance_model = balance_model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getForecast_amount() {
		return forecast_amount;
	}

	public void setForecast_amount(Long forecast_amount) {
		this.forecast_amount = forecast_amount;
	}

	public Double getForecast_money() {
		return forecast_money;
	}

	public void setForecast_money(Double forecast_money) {
		this.forecast_money = forecast_money;
	}

	public Double getIncome_money() {
		return income_money;
	}

	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayment_remark() {
		return payment_remark;
	}

	public void setPayment_remark(String payment_remark) {
		this.payment_remark = payment_remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getIncome_time() {
		return income_time;
	}

	public void setIncome_time(Date income_time) {
		this.income_time = income_time;
	}

	public String getChanne_manager() {
		return channe_manager;
	}

	public void setChanne_manager(String channe_manager) {
		this.channe_manager = channe_manager;
	}

	public String getIncome_remark() {
		return income_remark;
	}

	public void setIncome_remark(String income_remark) {
		this.income_remark = income_remark;
	}

	public String getInvoice_remark() {
		return invoice_remark;
	}

	public void setInvoice_remark(String invoice_remark) {
		this.invoice_remark = invoice_remark;
	}

}
