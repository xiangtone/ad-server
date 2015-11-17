package cn.adwalker.ad.admin.finance.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
* <p>Title: RevenueCostVo</p>
* <p>Description:收支结算求和</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-8-27
 */
public class RevenueCostYJFVo {
				
	private Double sum_sys_cost;//预计成本
	private Double sum_forecast_income;//预计收入
	private Double sum_forecast_confirm_cost;//预确认成本
	private Double sum_forecast_confirm_income;//预确认收入
	private Double sum_gross_profit;	// 毛利
	private Double sum_gross_profit_rate;// 毛利率
	
	public RevenueCostYJFVo(Double sum_sys_cost,Double sum_forecast_income, Double sum_forecast_confirm_cost,Double sum_forecast_confirm_income) {
		super();
		this.sum_sys_cost = sum_sys_cost;
		this.sum_forecast_income = sum_forecast_income;
		this.sum_forecast_confirm_cost=sum_forecast_confirm_cost;
		this.sum_forecast_confirm_income=sum_forecast_confirm_income;
	}

	public Double getSum_sys_cost() {
		return sum_sys_cost;
	}

	public void setSum_sys_cost(Double sum_sys_cost) {
		this.sum_sys_cost = sum_sys_cost;
	}

	public Double getSum_forecast_income() {
		return sum_forecast_income;
	}

	public void setSum_forecast_income(Double sum_forecast_income) {
		this.sum_forecast_income = sum_forecast_income;
	}

	public Double getSum_forecast_confirm_cost() {
		return sum_forecast_confirm_cost;
	}

	public void setSum_forecast_confirm_cost(Double sum_forecast_confirm_cost) {
		this.sum_forecast_confirm_cost = sum_forecast_confirm_cost;
	}

	public Double getSum_forecast_confirm_income() {
		return sum_forecast_confirm_income;
	}

	public void setSum_forecast_confirm_income(Double sum_forecast_confirm_income) {
		this.sum_forecast_confirm_income = sum_forecast_confirm_income;
	}

	public Double getSum_gross_profit() {
		if(ObjectUtils.isNotEmpty(sum_forecast_confirm_income)&& ObjectUtils.isNotEmpty(sum_forecast_confirm_cost)){
			sum_gross_profit=(sum_forecast_confirm_income)-sum_forecast_confirm_cost;
		}
		return sum_gross_profit;
	}

	public void setSum_gross_profit(Double sum_gross_profit) {
		this.sum_gross_profit = sum_gross_profit;
	}

	public Double getSum_gross_profit_rate() {
		if(ObjectUtils.isNotEmpty(sum_forecast_confirm_income)&& ObjectUtils.isNotEmpty(sum_gross_profit)){
			sum_gross_profit_rate=(sum_gross_profit/sum_forecast_confirm_income)*100;
		}else{
			sum_gross_profit_rate=0d;
		}
		return sum_gross_profit_rate;
	}

	public void setSum_gross_profit_rate(Double sum_gross_profit_rate) {
		this.sum_gross_profit_rate = sum_gross_profit_rate;
	}	
	
}
