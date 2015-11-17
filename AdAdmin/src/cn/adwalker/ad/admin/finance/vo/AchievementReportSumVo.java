package cn.adwalker.ad.admin.finance.vo;
/**
* <p>Title: AchievementReportSumVo</p>
* <p>Description:求和Vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-10-30
 */
public class AchievementReportSumVo {
	private Double sum_income_money;//总收入 统计
	private Double sum_income_plm_money;//平台 统计
	private Double sum_income_cha_money;//渠道收入 统计
	private Double sum_cost_money;//总成本 统计
	private Double sum_cos_plm_money;//平台成本 统计
	private Double sum_cost_cha_money;//渠道成本 统计
	private Double sum_gross_profit;//总毛利率 统计
	private Double sum_plm_gross_profit;//平台毛利率 统计
	private Double sum_cha_gross_profit;//渠道毛利率 统计
	private Integer sum_income_amount;//总确认数统计
	private Integer sum_forecast_amount;//总预确认数 统计
	
	public AchievementReportSumVo() {
		super();
	}
	public AchievementReportSumVo(Double sum_income_money,
			Double sum_income_plm_money, Double sum_income_cha_money,
			Double sum_cost_money, Double sum_cos_plm_money,
			Double sum_cost_cha_money, Double sum_gross_profit,
			Double sum_plm_gross_profit, Double sum_cha_gross_profit,
			Integer sum_income_amount, Integer sum_forecast_amount) {
		super();
		this.sum_income_money = sum_income_money;
		this.sum_income_plm_money= sum_income_plm_money;
		this.sum_income_cha_money= sum_income_cha_money;
		this.sum_cost_money= sum_cost_money;
		this.sum_cos_plm_money= sum_cos_plm_money;
		this.sum_cost_cha_money= sum_cost_cha_money;
		this.sum_gross_profit= sum_gross_profit;
		this.sum_plm_gross_profit= sum_plm_gross_profit;
		this.sum_cha_gross_profit= sum_cha_gross_profit;
		
		this.sum_income_amount = sum_income_amount;
		this.sum_forecast_amount = sum_forecast_amount;
	}
	public AchievementReportSumVo(Double sum_income_money,
			Double sum_income_plm_money) {
		super();
		this.sum_income_money = sum_income_money;
		this.sum_income_plm_money= sum_income_plm_money;
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

	public Double getSum_income_plm_money() {
		return sum_income_plm_money;
	}

	public void setSum_income_plm_money(Double sum_income_plm_money) {
		this.sum_income_plm_money = sum_income_plm_money;
	}

	public Double getSum_income_cha_money() {
		return sum_income_cha_money;
	}

	public void setSum_income_cha_money(Double sum_income_cha_money) {
		this.sum_income_cha_money = sum_income_cha_money;
	}

	public Double getSum_cost_money() {
		return sum_cost_money;
	}

	public void setSum_cost_money(Double sum_cost_money) {
		this.sum_cost_money = sum_cost_money;
	}

	public Double getSum_cos_plm_money() {
		return sum_cos_plm_money;
	}

	public void setSum_cos_plm_money(Double sum_cos_plm_money) {
		this.sum_cos_plm_money = sum_cos_plm_money;
	}

	public Double getSum_cost_cha_money() {
		return sum_cost_cha_money;
	}

	public void setSum_cost_cha_money(Double sum_cost_cha_money) {
		this.sum_cost_cha_money = sum_cost_cha_money;
	}

	public Double getSum_gross_profit() {
		return sum_gross_profit;
	}

	public void setSum_gross_profit(Double sum_gross_profit) {
		this.sum_gross_profit = sum_gross_profit;
	}

	public Double getSum_plm_gross_profit() {
		return sum_plm_gross_profit;
	}

	public void setSum_plm_gross_profit(Double sum_plm_gross_profit) {
		this.sum_plm_gross_profit = sum_plm_gross_profit;
	}

	public Double getSum_cha_gross_profit() {
		return sum_cha_gross_profit;
	}

	public void setSum_cha_gross_profit(Double sum_cha_gross_profit) {
		this.sum_cha_gross_profit = sum_cha_gross_profit;
	}	
	
		
	
}

