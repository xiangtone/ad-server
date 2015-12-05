package cn.adwalker.model.finance.domain;

import java.util.Date;

/**
 * 网站主奖励明细-显示
 * 
 * @author Administrator
 * 
 */
public class FinanceAward {

	private Long id; // 网站主id
	private Long dev_id; // 网站主id
	private Double money; // 奖励金额
	private String note; // 活动名称
	private String activity_begin; // 活动周期-开始
	private String activity_end; // 活动周期-结束
	private Long finance_id; // 结算人id 0:平台
	private Integer finance_type; // 结算类型 0:已结算 1:未结算
	private Integer type; // 结算类型 0:已结算 1:未结算
	private Date create_time; // 结算日期
	public Long getDev_id() {
		return dev_id;
	}
	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
