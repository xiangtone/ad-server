package cn.adwalker.ad.admin.finance.vo;

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
	private Integer finance_type; // 结算人ID
	private Date stat_date; // 结算日期
	private String finance_name; // 结算人
	private String activity_begin; // 活动周期-开始
	private String activity_end;
	private  Date create_time;
	private String dev_email; // 网站主名称
	private String note; // 活动名称
	private Double money; // 奖励金额
	
	
	public String getDev_email() {
		return dev_email;
	}
	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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
	public Long getFinance_id() {
		return finance_id;
	}
	public void setFinance_id(Long finance_id) {
		this.finance_id = finance_id;
	}
	public Integer getFinance_type() {
		return finance_type;
	}
	public void setFinance_type(Integer finance_type) {
		this.finance_type = finance_type;
	}
	public Date getStat_date() {
		return stat_date;
	}
	public void setStat_date(Date stat_date) {
		this.stat_date = stat_date;
	}
	public String getFinance_name() {
		return finance_name;
	}
	public void setFinance_name(String finance_name) {
		this.finance_name = finance_name;
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
	} // 活动周期-结束
	
	
}
	
