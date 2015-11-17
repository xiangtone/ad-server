package cn.adwalker.ad.admin.report.vo;

import java.math.BigDecimal;

/**
* <p>Title: PlatformCensusGeneralViewVo</p>
* <p>Description:平台总数据表</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-18
 */
public class PlatformCensusOutcomeVo {
	/** 广告主确认数 */
	private  BigDecimal sum_income_amount;
	/** 成本*/
	private BigDecimal sum_cost_price;
	/** 收入 */
	private BigDecimal sum_income_money;
	
	public BigDecimal getSum_income_amount() {
		return sum_income_amount;
	}
	public void setSum_income_amount(BigDecimal sum_income_amount) {
		this.sum_income_amount = sum_income_amount;
	}
	public BigDecimal getSum_cost_price() {
		return sum_cost_price;
	}
	public void setSum_cost_price(BigDecimal sum_cost_price) {
		this.sum_cost_price = sum_cost_price;
	}
	public BigDecimal getSum_income_money() {
		return sum_income_money;
	}
	public void setSum_income_money(BigDecimal sum_income_money) {
		this.sum_income_money = sum_income_money;
	}
	
}
