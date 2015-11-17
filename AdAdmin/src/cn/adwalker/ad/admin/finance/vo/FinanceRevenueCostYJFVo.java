package cn.adwalker.ad.admin.finance.vo;

import cn.adwalker.core.util.Function;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 
 * <p>
 * Title: FinanceRevenueCostYJFVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-3
 */
public class FinanceRevenueCostYJFVo {
	// 主键
	private Long id;

	// 主键
	private String type;
	// 广告主ID
	private Long adv_id;
	// 广告主名称
	private String company_name;

	private String type_name;

	// 媒体名称
	private String meia_name;

	private Integer confirm_num;

	private Double in_price;
	// 活动id
	private Long campaign_id;

	private Double sys_cost;
	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String static_date;
	// 平台类型
	private String os;
	// 计费类型
	private String charge_type;
	// 预计支出
	private Double forecast_cost;
	// 预计收入=预计成本*（1+加价率）
	private Double forecast_income;
	// 预确认成本
	private Double forecast_confirm_cost;
	// 确认单价
	private Double price;
	// 加价率
	private Double profit_rate;
	// 毛利
	private Double gross_profit;
	// 毛利率
	private Double gross_profit_rate;

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

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Double getProfit_rate() {
		return profit_rate;
	}

	public void setProfit_rate(Double profit_rate) {
		this.profit_rate = profit_rate;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public Double getForecast_cost() {
		return forecast_cost;
	}

	public void setForecast_cost(Double forecast_cost) {
		this.forecast_cost = forecast_cost;
	}

	public Double getForecast_income() {
		if (sys_cost != null && profit_rate != null) {
			forecast_income = this.sys_cost * (1 + this.profit_rate);
		}
		return forecast_income;
	}

	public void setForecast_income(Double forecast_income) {
		this.forecast_income = forecast_income;
	}

	public Double getForecast_confirm_cost() {
		if (ObjectUtils.isEmpty(sys_cost)) {
			sys_cost = 0d;
		}
		forecast_confirm_cost = this.sys_cost;
		return forecast_confirm_cost;
	}

	public void setForecast_confirm_cost(Double forecast_confirm_cost) {
		this.forecast_confirm_cost = forecast_confirm_cost;
	}

	public Double getForecast_confirm_income() {
		return Function.multiply(in_price != null ? in_price : 0d,
				confirm_num != null ? confirm_num : 0d);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	public Double getIn_price() {
		return in_price;
	}

	public void setIn_price(Double in_price) {
		this.in_price = in_price;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getMeia_name() {
		return meia_name;
	}

	public void setMeia_name(String meia_name) {
		this.meia_name = meia_name;
	}

	public Double getGross_profit() {
		if (ObjectUtils.isNotEmpty(in_price)
				&& ObjectUtils.isNotEmpty(confirm_num)) {
			gross_profit = (in_price * confirm_num) - forecast_confirm_cost;
		}
		return gross_profit;
	}

	public void setGross_profit(Double gross_profit) {
		this.gross_profit = gross_profit;
	}

	public Double getGross_profit_rate() {
		if (ObjectUtils.isNotEmpty(in_price)
				&& ObjectUtils.isNotEmpty(confirm_num)
				&& ObjectUtils.isNotEmpty(gross_profit)) {
			if ((in_price * confirm_num) != 0) {
				gross_profit_rate = (gross_profit / (in_price * confirm_num)) * 100;
			} else {
				gross_profit_rate = 0d;
			}
		} else {
			gross_profit_rate = 0d;
		}
		return gross_profit_rate;
	}

	public void setGross_profit_rate(Double gross_profit_rate) {
		this.gross_profit_rate = gross_profit_rate;
	}

}
