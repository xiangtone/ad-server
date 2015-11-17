package cn.adwalker.ad.admin.finance.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: FinanceConsume
 * </p>
 * <p>
 * Description:广告消费明细From 
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-14
 */
public class FinanceConsumeVo {
	private Long id;
	// 广告主ID
	private Long adv_id;
	// 广告主名称
	private String adv_email;
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String static_date;
	// 媒体类型
	private String type;
	// 业绩提交人
	private String sys_user_name;
	// 平台类型
	private String os;
	// 单价
	private Double in_price;
	// 加价率
	private Double profit_rate;
	// 成本
	private Double sys_cost;
	// 收入
	private Double income_money;
	// 预计收入
	private Double forecast_income;
	// 差值
	private Double balance;
	// 毛利率
	private Double gross_profit;
	// 广告主确认数
	private Integer confirm_num;
	// 收入类型
	private Integer media_type;
	//媒体名称
	private String meia_name;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public String getAdv_email() {
		return adv_email;
	}

	public void setAdv_email(String adv_email) {
		this.adv_email = adv_email;
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

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}
	public String getSys_user_name() {
		return sys_user_name;
	}

	public void setSys_user_name(String sys_user_name) {
		this.sys_user_name = sys_user_name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	
	public Double getIn_price() {
		return in_price;
	}

	public void setIn_price(Double in_price) {
		this.in_price = in_price;
	}

	public Double getIncome_money() {
		if(ObjectUtils.isNotEmpty(confirm_num)&&ObjectUtils.isNotEmpty(in_price)){
			income_money=this.confirm_num*this.in_price;
		}
		return income_money;
	}

	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	
	public Double getProfit_rate() {
		return profit_rate;
	}

	public void setProfit_rate(Double profit_rate) {
		this.profit_rate = profit_rate;
	}

	public Double getForecast_income() {
		if(ObjectUtils.isNotEmpty(sys_cost)&&ObjectUtils.isNotEmpty(profit_rate)){
			forecast_income=sys_cost*(1+profit_rate);
		}
		return forecast_income;
	}

	public void setForecast_income(Double forecast_income) {
		this.forecast_income = forecast_income;
	}

	public Integer getMedia_type() {
		return media_type;
	}

	public void setMedia_type(Integer media_type) {
		this.media_type = media_type;
	}

	public Double getBalance() {
		balance = 0d;
		if (income_money != null) {
			if (forecast_income != null) {
				balance = this.income_money - this.forecast_income;
			} else {
				balance = this.income_money - 0.0;
			}
		}

		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getGross_profit() {
		if (ObjectUtils.isNotEmpty(sys_cost)&&ObjectUtils.isNotEmpty(income_money)&&income_money!=0) {
			gross_profit = (income_money-sys_cost)/income_money;
		}
		return gross_profit;
	}

	public void setGross_profit(Double gross_profit) {
		this.gross_profit = gross_profit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getSys_cost() {
		return sys_cost;
	}

	public void setSys_cost(Double sys_cost) {
		this.sys_cost = sys_cost;
	}

	public String getMeia_name() {
		return meia_name;
	}

	public void setMeia_name(String meia_name) {
		this.meia_name = meia_name;
	}

}
