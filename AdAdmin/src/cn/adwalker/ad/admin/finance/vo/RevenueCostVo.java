package cn.adwalker.ad.admin.finance.vo;
/**
* <p>Title: RevenueCostVo</p>
* <p>Description:收支结算求和</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-8-27
 */
public class RevenueCostVo {
		
	private Double sum_forecast_money;//确认成本
	private Double sum_income_money;//总确认收入
	private Double sum_gross_profit;//毛利
	
	
	public RevenueCostVo(Double sum_gross_profit, Double sum_income_money,Double sum_forecast_money) {
		super();
		this.sum_gross_profit = sum_gross_profit;
		this.sum_income_money = sum_income_money;
		this.sum_forecast_money=sum_forecast_money;
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


	
	
	
	
}
