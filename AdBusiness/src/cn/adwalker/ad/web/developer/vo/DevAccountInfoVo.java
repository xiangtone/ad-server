package cn.adwalker.ad.web.developer.vo;

import cn.adwalker.ad.util.ObjectUtils;

public class DevAccountInfoVo {

	/** ID */
	private Long id;
	/** 可提款收入 */
	private Double finance_income;
	/** 奖励 */
	private Double award_income;
	/** 未确认收入 */
	private Double confirmMoney;
	/** 累计提款 */
	private Double total_money;
	/** 扣税 */
	private Double dues;
	/** 累计收入 */
	private Double total_income;
	/** 账户类型（1.个人2：公司） */
	private Integer type;
	/** 开户银行 */
	private String bank_subbranch;
	/** 银行账号 */
	private String bank_account;
	/** 正在审核 */
	private Double apply_money;
	/** 当月的钱 */
	private Double month_income;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getFinance_income() {
		if (ObjectUtils.isEmpty(finance_income)) {
			finance_income = 0.00;
		} else if (ObjectUtils.isNotEmpty(apply_money)) {
			finance_income = finance_income - apply_money;
		}
		return finance_income;
	}

	public void setFinance_income(Double finance_income) {
		this.finance_income = finance_income;
	}

	public Double getAward_income() {
		if (ObjectUtils.isEmpty(award_income)) {
			award_income = 0.00;
		}
		return award_income;
	}

	public void setAward_income(Double award_income) {
		this.award_income = award_income;
	}

	public Double getConfirmMoney() {
		if (ObjectUtils.isEmpty(confirmMoney)) {
			confirmMoney = 0.00;
		}
		return confirmMoney;
	}

	public void setConfirmMoney(Double confirmMoney) {
		this.confirmMoney = confirmMoney;
	}

	public Double getTotal_money() {
		if (ObjectUtils.isEmpty(total_money)) {
			total_money = 0.00;
		}
		return total_money;
	}

	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}

	public Double getDues() {
		if (ObjectUtils.isEmpty(dues)) {
			dues = 0.00;
		}
		return dues;
	}

	public void setDues(Double dues) {
		this.dues = dues;
	}

	public Double getTotal_income() {
		/*if (ObjectUtils.isEmpty(confirmMoney)) {
			total_income = 0.00;
		}*/
		return total_income;
	}

	public void setTotal_income(Double total_income) {
		this.total_income = total_income;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBank_subbranch() {
		return bank_subbranch;
	}

	public void setBank_subbranch(String bank_subbranch) {
		this.bank_subbranch = bank_subbranch;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public Double getApply_money() {
		if (ObjectUtils.isEmpty(apply_money)) {
			apply_money = 0.00;
		}
		return apply_money;
	}

	public void setApply_money(Double apply_money) {
		this.apply_money = apply_money;
	}

	public Double getMonth_income() {
		return month_income;
	}

	public void setMonth_income(Double month_income) {
		this.month_income = month_income;
	}

}
