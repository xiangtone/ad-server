package cn.adwalker.ad.admin.sales.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BalanceAccountDetailInfoVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -1234074692204745748L;

	/** 开始日期 */
	private Date static_date;


	/**
	 * 预确认数
	 */
	private Integer confirm_amount;

	/**
	 * 预确认收入
	 */
	private BigDecimal confirm_money;

	/**
	 * @return static_date
	 */
	public Date getStatic_date() {
		return static_date;
	}

	/**
	 * @param static_date the static_date to set
	 */
	
	public void setStatic_date(Date static_date) {
		this.static_date = static_date;
	}

	/**
	 * @return confirm_amount
	 */
	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	/**
	 * @param confirm_amount the confirm_amount to set
	 */
	
	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	/**
	 * @return confirm_money
	 */
	public BigDecimal getConfirm_money() {
		return confirm_money;
	}

	/**
	 * @param confirm_money the confirm_money to set
	 */
	
	public void setConfirm_money(BigDecimal confirm_money) {
		this.confirm_money = confirm_money;
	}
}
