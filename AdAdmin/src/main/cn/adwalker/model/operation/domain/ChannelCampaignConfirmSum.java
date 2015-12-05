package cn.adwalker.model.operation.domain;
/**
* <p>Title: CampaignConfirmSum</p>
* <p>Description:求和domin</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-10-11
 */
public class ChannelCampaignConfirmSum {
	private Double sum_income_money;//总金额 统计
	private Double sum_forecast_money;//预计总金额统计
	private Integer sum_forecast_amount;//总确认数 统计
	
	public Double getSum_income_money() {
		return sum_income_money;
	}

	public void setSum_income_money(Double sum_income_money) {
		this.sum_income_money = sum_income_money;
	}

	

	public Double getSum_forecast_money() {
		return sum_forecast_money;
	}

	public void setSum_forecast_money(Double sum_forecast_money) {
		this.sum_forecast_money = sum_forecast_money;
	}

	public Integer getSum_forecast_amount() {
		return sum_forecast_amount;
	}

	public void setSum_forecast_amount(Integer sum_forecast_amount) {
		this.sum_forecast_amount = sum_forecast_amount;
	}	
	
		
	
}
