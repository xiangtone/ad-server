package cn.adwalker.model.operation.domain;

import java.util.Date;

/**
 * 网站主奖励明细-数据库
 * 
 * @author Administrator
 * 
 */
public class DevPunishDetail {
	private Long dev_id; // 开发者ID
	private String dev_account; // 开发者帐号
	private String activity_name; // 扣费原因
	private Double award_money; // 金额(元)
	private Long finance_id; // 结算人ID
	private Date stat_date; // 结算日期

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public String getDev_account() {
		return dev_account;
	}

	public void setDev_account(String dev_account) {
		this.dev_account = dev_account;
	}

	public Double getAward_money() {
		return award_money;
	}

	public void setAward_money(Double award_money) {
		this.award_money = award_money;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public Long getFinance_id() {
		return finance_id;
	}

	public void setFinance_id(Long finance_id) {
		this.finance_id = finance_id;
	}

	public Date getStat_date() {
		return stat_date;
	}

	public void setStat_date(Date stat_date) {
		this.stat_date = stat_date;
	}
}
