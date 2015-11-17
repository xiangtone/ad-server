package cn.adwalker.ad.admin.finance.vo;


/**
 * <p>
 * Title: IncomeAuditVo
 * </p>
 * <p>
 * Description:开发者收入vo
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-17
 */
public class PlatformCostByMonthVo {
	
	
	private String year;
	
	
	private String month;


	/** 开发者id */
	private Long dev_id;
	/** 开发者邮箱 */
	private String dev_email;
	/** 应用id */
	private Long app_id;
	/** 应用名称 */
	private String app_name;
	
	private String os;

	/** 活动id */
	private Long campaign_id;
	/** 活动名称 */
	private String ad_name;


	/** 确认收入 */
	private Double dev_cost;


	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public String getDev_email() {
		return dev_email;
	}

	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	/**
	 * @return dev_cost
	 */
	public Double getDev_cost() {
		return dev_cost;
	}

	/**
	 * @param dev_cost the dev_cost to set
	 */
	
	public void setDev_cost(Double dev_cost) {
		this.dev_cost = dev_cost;
	}

	/**
	 * @return ad_name
	 */
	public String getAd_name() {
		return ad_name;
	}

	/**
	 * @param ad_name
	 *            the ad_name to set
	 */

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	/**
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os the os to set
	 */
	
	public void setOs(String os) {
		this.os = os;
	}
}
