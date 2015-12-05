package cn.adwalker.model.operation.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: CampaignConfirm
 * </p>
 * <p>
 * Description:确认数domin
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-11
 */
public class CampaignConfirm implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 3736041807444850338L;
	// id
	private Long id;
	// 活动id
	private Long campaign_id;
	// 效果发生开始时间
	private String month_stat_date;
	// 效果发生结束时间
	private String month_end_date;
	// 广告主确认数
	private Integer income_amount;
	// 确认钱
	private double income_money;
	// 单价
	private double price;
	// 操作人
	private Long operater_id;
	
	private String balance_model;
	//预确认的钱
	private Double forecast_money;
	//预确认的数
	private Integer forecast_amount;

	private Date update_time;

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

	public Integer getIncome_amount() {
		return income_amount;
	}

	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
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

	public Long getOperater_id() {
		return operater_id;
	}

	public void setOperater_id(Long operater_id) {
		this.operater_id = operater_id;
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

}
