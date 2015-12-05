package cn.adwalker.ad.admin.finance.bean;


/**
* <p>Title: DevPunishDetailbean</p>
* <p>Description:扣费bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-7-3
 */

public class DevPunishDetailbean {
	private Long dev_id; // 网站主id
	private String dev_account; // 网站主名称
	private String stat_date_end; // 活动周期-结束
	private String stat_date_begin; // 活动周期-开始
	private String finance_name; // 操作人
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
	public String getStat_date_end() {
		return stat_date_end;
	}
	public void setStat_date_end(String stat_date_end) {
		this.stat_date_end = stat_date_end;
	}
	public String getStat_date_begin() {
		return stat_date_begin;
	}
	public void setStat_date_begin(String stat_date_begin) {
		this.stat_date_begin = stat_date_begin;
	}
	public String getFinance_name() {
		return finance_name;
	}
	public void setFinance_name(String finance_name) {
		this.finance_name = finance_name;
	}
	
	

	
}
