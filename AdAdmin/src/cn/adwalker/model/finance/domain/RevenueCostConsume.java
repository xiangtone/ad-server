package cn.adwalker.model.finance.domain;
/**
 * 
* <p>Title: AdvConsumeDetailVo</p>
* <p>Description:求和</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-16
 */
public class RevenueCostConsume {
	
	private Double sum_forecast_money;//确认成本
	private Double sum_income_money;//总确认收入
	private Double sum_gross_profit;//毛利
	
	private Double sum_sys_cost;//预计成本
	private Double sum_forecast_income;//预计收入
	private Double sum_forecast_confirm_cost;//预确认成本
	private Double sum_forecast_confirm_income;//预确认收入
	
	public RevenueCostConsume() {
		super();
	}


	public Double getSum_forecast_money() {
		return sum_forecast_money;
	}


	public void setSum_forecast_money(Double sum_forecast_money) {
		this.sum_forecast_money = sum_forecast_money;
	}


	public Double getSum_income_money() {
		return sum_income_money;
	}


	public void setSum_income_money(Double sum_income_money) {
		this.sum_income_money = sum_income_money;
	}


	public Double getSum_gross_profit() {
		return sum_gross_profit;
	}


	public void setSum_gross_profit(Double sum_gross_profit) {
		this.sum_gross_profit = sum_gross_profit;
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
	
	
}
