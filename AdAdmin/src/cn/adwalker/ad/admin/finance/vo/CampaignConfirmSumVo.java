package cn.adwalker.ad.admin.finance.vo;
/**
* <p>Title: CampaignConfirmSumVo</p>
* <p>Description:求和Vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-10-11
 */
public class CampaignConfirmSumVo {
	private Double sum_income_money;//总金额 统计
	private Integer sum_income_amount;//总确认数统计
	private Integer sum_forecast_amount;//总确认数 统计
	
	public CampaignConfirmSumVo() {
		super();
	}

	public CampaignConfirmSumVo(Double sum_income_money,Integer sum_income_amount,Integer sum_forecast_amount) {
		super();
		this.sum_income_money = sum_income_money;
		this.sum_income_amount = sum_income_amount;
		this.sum_forecast_amount = sum_forecast_amount;
	}

	public Double getSum_income_money() {
		return sum_income_money;
	}

	public void setSum_income_money(Double sum_income_money) {
		this.sum_income_money = sum_income_money;
	}

	public Integer getSum_income_amount() {
		return sum_income_amount;
	}

	public void setSum_income_amount(Integer sum_income_amount) {
		this.sum_income_amount = sum_income_amount;
	}

	public Integer getSum_forecast_amount() {
		return sum_forecast_amount;
	}

	public void setSum_forecast_amount(Integer sum_forecast_amount) {
		this.sum_forecast_amount = sum_forecast_amount;
	}	
	
		
	
}
