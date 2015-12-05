package cn.adwalker.ad.admin.finance.vo;

import cn.adwalker.core.util.lang.MathUtil;
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
public class FinanceRevenueCostVo {
	// 主键
	private Long id;
	// 广告主ID
	private Long adv_Id;
	// 广告主名称
	private String company_name;
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String month_stat_date;
	// 效果发生时间
	private String month_end_date;
	// 平台类型
	private String os;
	// 确认收入
	private Double income_money;
	//单价
	private Double price;
	// 预确认收入
	private Double forecast_money;
	// 确认支出
	private Double exprice_money;
	// 毛利率
	private Double gross_profit;
	// 收入差额
	private Double balance;
	// 收入差额
	private Integer income_amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdv_Id() {
		return adv_Id;
	}

	public void setAdv_Id(Long adv_Id) {
		this.adv_Id = adv_Id;
	}

	

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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

	public String getMonth_stat_date() {
		return month_stat_date;
	}

	public void setMonth_stat_date(String month_stat_date) {
		this.month_stat_date = month_stat_date;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Double getIncome_money() {
		if (income_amount != null && price != null) {
			income_money = MathUtil.multiply(income_amount, price);
		}
		return income_money;
	}

	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}

	public Double getExprice_money() {
		return exprice_money;
	}

	public void setExprice_money(Double exprice_money) {
		this.exprice_money = exprice_money;
	}

	public Double getGross_profit() {
		if(ObjectUtils.isNotEmpty(income_money)&& ObjectUtils.isNotEmpty(exprice_money)){
			gross_profit = ((income_money - exprice_money)/income_money)*100;
		}
		return gross_profit;
	}

	public void setGross_profit(Double gross_profit) {
		this.gross_profit = gross_profit;
	}

	public Double getBalance() {
		if (ObjectUtils.isNotEmpty(income_money)&& ObjectUtils.isNotEmpty(forecast_money)) {
			balance = income_money - forecast_money;
		}
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getForecast_money() {
		return forecast_money;
	}

	public void setForecast_money(Double forecast_money) {
		this.forecast_money = forecast_money;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getIncome_amount() {
		return income_amount;
	}

	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
	}

	public String getMonth_end_date() {
		return month_end_date;
	}

	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
	}

}
