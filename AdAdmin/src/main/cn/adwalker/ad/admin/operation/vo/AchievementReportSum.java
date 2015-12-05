package cn.adwalker.ad.admin.operation.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: AchievementReportSum
 * </p>
 * <p>
 * Description:求和domin
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-30
 */
public class AchievementReportSum {
	private Double sum_income_money;// 总收入 统计
	private Double sum_income_plm_money;// 平台收入 统计
	private Double sum_income_cha_money;// 渠道收入 统计
	private Double sum_cost_money;// 总成本 统计
	private Double sum_cost_plm_money;// 平台成本 统计
	private Double sum_cost_cha_money;// 渠道成本 统计
	private Double sum_gross_profit;// 总毛利率 统计
	private Double sum_plm_gross_profit;// 平台毛利率 统计
	private Double sum_cha_gross_profit;// 渠道毛利率 统计
	private Integer sum_income_amount;// 总确认数统计
	private Integer sum_forecast_amount;// 总预确认数 统计

	public Double getSum_income_money() {
		return sum_income_money;
	}

	public void setSum_income_money(Double sum_income_money) {
		this.sum_income_money = sum_income_money;
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
		if (ObjectUtils.isNotEmpty(sum_cost_plm_money)
				&& ObjectUtils.isNotEmpty(sum_cost_cha_money)) {
			sum_cost_money = sum_cost_plm_money + sum_cost_cha_money;
		}
		return sum_cost_money;
	}

	public void setSum_cost_money(Double sum_cost_money) {
		this.sum_cost_money = sum_cost_money;
	}

	public Double getSum_cost_plm_money() {
		return sum_cost_plm_money;
	}

	public void setSum_cost_plm_money(Double sum_cost_plm_money) {
		this.sum_cost_plm_money = sum_cost_plm_money;
	}

	public Double getSum_cost_cha_money() {
		return sum_cost_cha_money;
	}

	public void setSum_cost_cha_money(Double sum_cost_cha_money) {
		this.sum_cost_cha_money = sum_cost_cha_money;
	}

	public Double getSum_gross_profit() {
		if (ObjectUtils.isNotEmpty(sum_income_money)
				&& ObjectUtils.isNotEmpty(sum_cost_money)) {
			sum_gross_profit = (sum_income_money - sum_cost_money)
					/ sum_income_money * 100;
		}
		return sum_gross_profit;
	}

	public void setSum_gross_profit(Double sum_gross_profit) {
		this.sum_gross_profit = sum_gross_profit;
	}

	public Double getSum_plm_gross_profit() {
		if (ObjectUtils.isNotEmpty(sum_income_plm_money)
				&& ObjectUtils.isNotEmpty(sum_cost_plm_money)) {
			sum_plm_gross_profit = (sum_income_plm_money - sum_cost_plm_money)
					/ sum_income_plm_money * 100;
		}
		return sum_plm_gross_profit;
	}

	public void setSum_plm_gross_profit(Double sum_plm_gross_profit) {
		this.sum_plm_gross_profit = sum_plm_gross_profit;
	}

	public Double getSum_cha_gross_profit() {
		if (ObjectUtils.isNotEmpty(sum_income_cha_money)
				&& ObjectUtils.isNotEmpty(sum_cost_cha_money)) {
			sum_cha_gross_profit = (sum_income_cha_money - sum_cost_cha_money)
					/ sum_income_cha_money * 100;
		}
		return sum_cha_gross_profit;
	}

	public void setSum_cha_gross_profit(Double sum_cha_gross_profit) {
		this.sum_cha_gross_profit = sum_cha_gross_profit;
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
