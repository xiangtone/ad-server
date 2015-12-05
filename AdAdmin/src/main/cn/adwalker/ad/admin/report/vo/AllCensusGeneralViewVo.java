package cn.adwalker.ad.admin.report.vo;

import java.math.BigDecimal;

import cn.adwalker.core.util.lang.MathUtil;


/**
 * <p>
 * Title: AllCensusGeneralViewVo
 * </p>
 * <p>
 * Description:平台加渠道总体数据表
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-18
 */
public class AllCensusGeneralViewVo {
	/** 主键ID */
	private Long id;
	/** 时间 */
	private String stat_date;
	/** PV */
	private  BigDecimal pv_amount;
	/** 点击 */
	private  BigDecimal click_amount;
	/** 下载 */
	private  BigDecimal down_amount;
	/** 平台确认数 */
	private  BigDecimal sum_platform_amount;
	/** 广告主确认数 */
	private  BigDecimal sum_income_amount;
	/** 成本*/
	private BigDecimal sum_cost_price;
	/** 收入 */
	private BigDecimal sum_income_money;
	/** 利率 */
	private BigDecimal profit_money;
	/** 毛利率 */
	private BigDecimal gross_profit_margin;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStat_date() {
		return stat_date;
	}
	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}
	public BigDecimal getPv_amount() {
		return pv_amount;
	}
	public void setPv_amount(BigDecimal pv_amount) {
		this.pv_amount = pv_amount;
	}
	public BigDecimal getClick_amount() {
		return click_amount;
	}
	public void setClick_amount(BigDecimal click_amount) {
		this.click_amount = click_amount;
	}
	public BigDecimal getDown_amount() {
		return down_amount;
	}
	public void setDown_amount(BigDecimal down_amount) {
		this.down_amount = down_amount;
	}
	public BigDecimal getSum_platform_amount() {
		return sum_platform_amount;
	}
	public void setSum_platform_amount(BigDecimal sum_platform_amount) {
		this.sum_platform_amount = sum_platform_amount;
	}
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
	public BigDecimal getProfit_money() {
		if(this.sum_income_money!=null&&this.sum_cost_price!=null){
			profit_money=MathUtil.subtractBig(this.sum_income_money, this.sum_cost_price);
		}
		return profit_money;
	}
	public void setProfit_money(BigDecimal profit_money) {
		this.profit_money = profit_money;
	}
	public BigDecimal getGross_profit_margin() {
		if(this.profit_money!=null && this.sum_cost_price!=null){
			gross_profit_margin=MathUtil.divideBig(this.profit_money,this.sum_cost_price);
		}
		return gross_profit_margin;
	}
	public void setGross_profit_margin(BigDecimal gross_profit_margin) {
		this.gross_profit_margin = gross_profit_margin;
	}

}
