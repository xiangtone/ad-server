package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 网站主奖励明细-显示
 * 
 * @author Administrator
 * 
 */
public class DevListAwardDetailVo {
	private Long dev_id; // 网站主id
	private String dev_account; // 网站主名称
	private String activity_name; // 活动名称
	private Double award_money; // 金额
	private String finance_name; // 结算人
	private String finance_type; // 结算类型
	private Date stat_date; // 结算日期
	private Date stat_date_begin; // 结算日期-开始
	private Date stat_date_end; // 结算日期-结束
	private String activity_begin; // 活动周期-开始
	private String activity_end; // 活动周期-结束

	/** 活动周期 */
	private String activityInterval;

	public String getActivityInterval() {
		StringBuffer sbf = new StringBuffer();
		try {
			if (ObjectUtils.isNotEmpty(activity_begin)) {
				sbf.append(activity_begin);
			}
			if (ObjectUtils.isNotEmpty(activity_begin)
					|| ObjectUtils.isNotEmpty(activity_end)) {
				sbf.append("至");
			}
			if (ObjectUtils.isNotEmpty(activity_end)) {
				sbf.append(activity_end);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		activityInterval = sbf.toString();
		return activityInterval;
	}

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

	public String getFinance_type() {
		return finance_type;
	}

	public void setFinance_type(String finance_type) {
		this.finance_type = finance_type;
	}

	public Date getStat_date() {
		return stat_date;
	}

	public void setStat_date(Date stat_date) {
		this.stat_date = stat_date;
	}

	public Date getStat_date_begin() {
		return stat_date_begin;
	}

	public void setStat_date_begin(Date stat_date_begin) {
		this.stat_date_begin = stat_date_begin;
	}

	public Date getStat_date_end() {
		return stat_date_end;
	}

	public void setStat_date_end(Date stat_date_end) {
		this.stat_date_end = stat_date_end;
	}

	public String getActivity_begin() {
		return activity_begin;
	}

	public void setActivity_begin(String activity_begin) {
		this.activity_begin = activity_begin;
	}

	public String getActivity_end() {
		return activity_end;
	}

	public void setActivity_end(String activity_end) {
		this.activity_end = activity_end;
	}
}
