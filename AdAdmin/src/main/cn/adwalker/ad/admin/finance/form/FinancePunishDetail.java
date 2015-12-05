package cn.adwalker.ad.admin.finance.form;


/**
 * 网站主奖励明细-显示
 * 
 * @author Administrator
 * 
 */
public class FinancePunishDetail {
	private Long entryDevId; // 网站主id
	private String entryDevName; // 网站主名称
	private Double entryActMoney; // 奖励金额
	private String entryActName; // 活动名称
	public Long getEntryDevId() {
		return entryDevId;
	}
	public void setEntryDevId(Long entryDevId) {
		this.entryDevId = entryDevId;
	}
	public String getEntryDevName() {
		return entryDevName;
	}
	public void setEntryDevName(String entryDevName) {
		this.entryDevName = entryDevName;
	}
	public Double getEntryActMoney() {
		return entryActMoney;
	}
	public void setEntryActMoney(Double entryActMoney) {
		this.entryActMoney = entryActMoney;
	}
	public String getEntryActName() {
		return entryActName;
	}
	public void setEntryActName(String entryActName) {
		this.entryActName = entryActName;
	}
}
