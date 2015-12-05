package cn.adwalker.ad.admin.operation.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: InvoiceSum
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
public class InvoiceSum {
	private Double sum_balance;// 差值
	private Double sum_invoice_money;// 发票的总金额
	private Double sum_income_money;// 确认总金额 统计
	private Double sum_bad_money;// 坏账总金额 统计
	private Double sum_circle_money;// 回款总金额 统计
	private Double sum_not_circle_money;// not回款总金额 统计

	public Double getSum_balance() {
		if(ObjectUtils.isNotEmpty(sum_income_money)&&ObjectUtils.isNotEmpty(sum_invoice_money)){
			sum_balance=sum_income_money-sum_invoice_money;
		}
		return sum_balance;
	}

	public void setSum_balance(Double sum_balance) {
		this.sum_balance = sum_balance;
	}

	public Double getSum_invoice_money() {
		return sum_invoice_money;
	}

	public void setSum_invoice_money(Double sum_invoice_money) {
		this.sum_invoice_money = sum_invoice_money;
	}

	public Double getSum_income_money() {
		return sum_income_money;
	}

	public void setSum_income_money(Double sum_income_money) {
		this.sum_income_money = sum_income_money;
	}

	public Double getSum_bad_money() {
		return sum_bad_money;
	}

	public void setSum_bad_money(Double sum_bad_money) {
		this.sum_bad_money = sum_bad_money;
	}

	public Double getSum_circle_money() {
		return sum_circle_money;
	}

	public void setSum_circle_money(Double sum_circle_money) {
		this.sum_circle_money = sum_circle_money;
	}

	public Double getSum_not_circle_money() {
		if(ObjectUtils.isNotEmpty(sum_income_money)&&ObjectUtils.isNotEmpty(sum_circle_money)){
			sum_not_circle_money=sum_income_money-sum_circle_money;
		}
		return sum_not_circle_money;
	}

	public void setSum_not_circle_money(Double sum_not_circle_money) {
		this.sum_not_circle_money = sum_not_circle_money;
	}

}
