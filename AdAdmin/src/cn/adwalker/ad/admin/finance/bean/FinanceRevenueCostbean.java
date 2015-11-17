package cn.adwalker.ad.admin.finance.bean;
/**
* <p>Title: FinanceRevenueCostbean</p>
* <p>Description:确认收入成本bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-7
 */
public class FinanceRevenueCostbean {
	//广告主ID
	private Long adv_Id;
	//广告主名称
	private String adv_email;
	//活动id
	private Long campaign_id;
	//活动名称
	private String campaign_name;
	//平台类型
	private String os;
	//效果发生开始时间
	private String month_stat_date;
	//效果发生结束时间
	private String month_end_date;
	
	public Long getAdv_Id() {
		return adv_Id;
	}
	public void setAdv_Id(Long adv_Id) {
		this.adv_Id = adv_Id;
	}
	public String getAdv_email() {
		return adv_email;
	}
	public void setAdv_email(String adv_email) {
		this.adv_email = adv_email;
	}
	public Long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getMonth_stat_date() {
		return month_stat_date;
	}
	public void setMonth_stat_date(String month_stat_date) {
		this.month_stat_date = month_stat_date;
	}
	public String getMonth_end_date() {
		return month_end_date;
	}
	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
	}
	
}
