/**
* <p>Title: Balance.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author hadoop
* @date 2013-11-4
* @version 1.0
*/
package cn.adwalker.model.finance.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>Title: Balance</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    cuidd
 * @date       2013-11-4
 */
public class Balance implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -5129010686861335307L;
	// 主键
	private Long id;
	// 活动id
	private Long campaign_id;
	
	// 效果发生开始时间
	private String month_stat_date;
	
	//录入结束时间
	private String month_end_date;
	// 广告主确认数
	private Integer income_amount;
	
	
	// 广告主确认数
	private Integer status;
	
	// 结算金额--确认的收入
	private Double income_money;
	// 单价
	private Double price;
	
	private Date create_time;
	
	/**
	 * 确认支出
	 */
	private Double exprice_money;
	
	
	//预计收入
	private Double forecast_money;
	
	//预确认数
	private Integer forecast_amount;
			
	private String data_photo_url;
	
	
	private Date update_time;
	
	
	//平台成本
	private Double platform_cost_money;
	
	//渠道成本
	private Double channel_cost_money;
	
	
	//渠道确认数
	private Double channel_amount;


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


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Double getIncome_money() {
		return income_money;
	}


	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Date getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


	public Double getExprice_money() {
		return exprice_money;
	}


	public void setExprice_money(Double exprice_money) {
		this.exprice_money = exprice_money;
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


	public String getData_photo_url() {
		return data_photo_url;
	}


	public void setData_photo_url(String data_photo_url) {
		this.data_photo_url = data_photo_url;
	}


	public Date getUpdate_time() {
		return update_time;
	}


	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}


	public Double getPlatform_cost_money() {
		return platform_cost_money;
	}


	public void setPlatform_cost_money(Double platform_cost_money) {
		this.platform_cost_money = platform_cost_money;
	}


	public Double getChannel_cost_money() {
		return channel_cost_money;
	}


	public void setChannel_cost_money(Double channel_cost_money) {
		this.channel_cost_money = channel_cost_money;
	}


	public Double getChannel_amount() {
		return channel_amount;
	}


	public void setChannel_amount(Double channel_amount) {
		this.channel_amount = channel_amount;
	}
}
