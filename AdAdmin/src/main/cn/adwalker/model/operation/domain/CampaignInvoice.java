package cn.adwalker.model.operation.domain;

import cn.adwalker.core.repository.Entity;

/**
 * 
* <p>Title: CampaignInvoice</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-10-28
 */
public class CampaignInvoice implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -5209045772878361485L;
	//id
	private Long id ;
	//广告主id
	private Long adv_id ;
	//活动id
	private Long campaign_id ;
	//状态
	private Integer invoice_status ;
	//活动名称名字
	private String campaign_name ;
	//钱
	private Double income_money ;
	//开始时间
	private String month_stat_date ;
	//结束时间
	private String month_end_date ;
	
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
	public Double getIncome_money() {
		return income_money;
	}
	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}
	public Integer getInvoice_status() {
		return invoice_status;
	}
	public void setInvoice_status(Integer invoice_status) {
		this.invoice_status = invoice_status;
	}

}
