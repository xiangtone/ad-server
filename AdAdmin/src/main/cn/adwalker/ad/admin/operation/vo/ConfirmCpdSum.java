package cn.adwalker.ad.admin.operation.vo;
/**
* <p>Title: ConfirmCpdSum</p>
* <p>Description:求和domin</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-10-12
 */
public class ConfirmCpdSum {
	
	private Double sum_forecast_money;//总预计金额 统计
	private Double sum_income_money;//总金额 统计
	private Integer sum_channel_num;//总数渠道
	
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
	public Integer getSum_channel_num() {
		return sum_channel_num;
	}
	public void setSum_channel_num(Integer sum_channel_num) {
		this.sum_channel_num = sum_channel_num;
	}
	
	
	
	
}
