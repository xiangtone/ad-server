/**
 * <p>Title: AdByHourQuery.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-15
 * @version 1.0
 */
package cn.adwalker.ad.admin.report.bean;

/**
 * <p>
 * Title: AdByHourQuery
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-15
 */
public class SalesAchievementBean {
	private String beginTime;
	private String endTime;
	private String adv_id;
	private Long type_id;
	private String placement_name;
	private String id;
	//平台类型
	private String os;
	//活动id
	private Long campaign_id;
	//活动名称
	private String campaign_name;
	//广告主名称
	private String adv_email;
	// 销售人
	private String sales_name;
	
	private Long sales_id;
	/** 4:华南,1:华东,2:华北,0:平台 */
	private Integer area_type_ad;
	
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
	}

	public String getPlacement_name() {
		return placement_name;
	}

	public void setPlacement_name(String placement_name) {
		this.placement_name = placement_name;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
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

	public String getAdv_email() {
		return adv_email;
	}

	public void setAdv_email(String adv_email) {
		this.adv_email = adv_email;
	}

	public String getSales_name() {
		return sales_name;
	}

	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}

	public Integer getArea_type_ad() {
		return area_type_ad;
	}

	public void setArea_type_ad(Integer area_type_ad) {
		this.area_type_ad = area_type_ad;
	}

	/**
	 * @return sales_id
	 */
	public Long getSales_id() {
		return sales_id;
	}

	/**
	 * @param sales_id the sales_id to set
	 */
	
	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}
}
