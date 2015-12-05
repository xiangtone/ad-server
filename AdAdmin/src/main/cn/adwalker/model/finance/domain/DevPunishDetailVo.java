package cn.adwalker.model.finance.domain;

import java.util.Date;

/**
 * 
* <p>Title: DevPunishDetailVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-16
 */

public class DevPunishDetailVo {
	private Long dev_id; // 开发者ID
	private String dev_account; // 开发者帐号
	private String activity_name; // 扣费原因
	private Double award_money; // 金额(元)
	private String finance_name; // 结算人
	private Date stat_date; // 结算日期
	private String stat_date_begin; // 结算日期-开始
	private String stat_date_end; // 结算日期-结束

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

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public Double getAward_money() {
		return award_money;
	}

	public void setAward_money(Double award_money) {
		this.award_money = award_money;
	}

	public String getFinance_name() {
		return finance_name;
	}

	public void setFinance_name(String finance_name) {
		this.finance_name = finance_name;
	}

	public Date getStat_date() {
		return stat_date;
	}

	public void setStat_date(Date stat_date) {
		this.stat_date = stat_date;
	}

	public String getStat_date_begin() {
		return stat_date_begin;
	}

	public void setStat_date_begin(String stat_date_begin) {
		this.stat_date_begin = stat_date_begin;
	}

	public String getStat_date_end() {
		return stat_date_end;
	}

	public void setStat_date_end(String stat_date_end) {
		this.stat_date_end = stat_date_end;
	}
}
