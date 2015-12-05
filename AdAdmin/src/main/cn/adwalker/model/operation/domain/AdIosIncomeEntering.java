package cn.adwalker.model.operation.domain;

import java.util.Date;



/**
* <p>Title: AdEntering</p>
* <p>Description:广告效果基础数据</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-10
 */
public class AdIosIncomeEntering {

	/** 主键id */
	private Long id;
	/** 活动id */
	private Long campaign_id;
	/** 广告id */
	private Long ad_id;
	/** 渠道id */
	private Long channel_id;
	/** 效果发生时间 */
	private String stat_date;	
	/**mac */
	private String income_mac;
	/**mac */
	private Date create_time ;
	/**渠道：0||平台：1*/
	private Integer ch_plf_type ;
	/**未发布：0，已发布：1*/
	private Integer status ;
	/**录入人*/
	private Long manager_id;
	/**活动名称 */
	private String campaign_name;
	/** 广告接入价 */
	private Double price;
	/**确认收入 */
	private Double income_money;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getStat_date() {
		return stat_date;
	}
	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}
	public String getIncome_mac() {
		return income_mac;
	}
	public void setIncome_mac(String income_mac) {
		this.income_mac = income_mac;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getCh_plf_type() {
		return ch_plf_type;
	}
	public void setCh_plf_type(Integer ch_plf_type) {
		this.ch_plf_type = ch_plf_type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getIncome_money() {
		return income_money;
	}
	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}
	public Long getAd_id() {
		return ad_id;
	}
	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
	public Long getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}
	
}
