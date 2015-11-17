package cn.adwalker.ad.admin.ad.vo;

import java.util.Date;

/**
 * <p>
 * Title: AdByPlacementVo
 * </p>
 * <p>
 * Description:TODO   
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class AdAjustmentVo {

	// 排期开始时间
	private Date  schedule_start_time;

	/**
	 * 排期开始是否开启
	 */
	private boolean schedule_start;
	// 排期结束时间
	private Date schedule_end_time;

	/**
	 * 排期结束是否开启
	 */
	private boolean schedule_end;
	// 广告id
	private Long adId;
	// 广告主id
	private Long advertisersId;
	// 广告主邮箱
	private String advertisersMail;
	
	// 广告主邮箱
	private String adv_name;
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;
	// 投放名称
	private String placement_name;
	// 平台类型
	private String os;
	// 广告形式
	private String adForm;
	//
	private String blanceMode;

	private Date submitTime;
	// 上线时间
	private Date onLineTime;
	// 下线时间
	private Date offLineTime;
	// 媒体类型
	private Integer ad_type;
	// 媒体id
	private Long app_id;
	// 媒体名称
	private String media_name;
	// t投放状态（0:上线1:人工下线,-10:推广结束,-20:超量下线）
	private String status;

	public Long getAdvertisersId() {
		return advertisersId;
	}

	public void setAdvertisersId(Long advertisersId) {
		this.advertisersId = advertisersId;
	}

	public String getAdvertisersMail() {
		return advertisersMail;
	}

	public void setAdvertisersMail(String advertisersMail) {
		this.advertisersMail = advertisersMail;
	}


	public boolean isSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(boolean schedule_start) {
		this.schedule_start = schedule_start;
	}

	public Date getSchedule_start_time() {
		return schedule_start_time;
	}

	public void setSchedule_start_time(Date schedule_start_time) {
		this.schedule_start_time = schedule_start_time;
	}

	public Date getSchedule_end_time() {
		return schedule_end_time;
	}

	public void setSchedule_end_time(Date schedule_end_time) {
		this.schedule_end_time = schedule_end_time;
	}

	public boolean isSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(boolean schedule_end) {
		this.schedule_end = schedule_end;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getAdForm() {
		return adForm;
	}

	public void setAdForm(String adForm) {
		this.adForm = adForm;
	}

	public String getBlanceMode() {
		return blanceMode;
	}

	public void setBlanceMode(String blanceMode) {
		this.blanceMode = blanceMode;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public String getAdv_name() {
		return adv_name;
	}

	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getOnLineTime() {
		return onLineTime;
	}

	public void setOnLineTime(Date onLineTime) {
		this.onLineTime = onLineTime;
	}

	public Date getOffLineTime() {
		return offLineTime;
	}

	public void setOffLineTime(Date offLineTime) {
		this.offLineTime = offLineTime;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public Integer getAd_type() {
		return ad_type;
	}

	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}

	public String getPlacement_name() {
		return placement_name;
	}

	public void setPlacement_name(String placement_name) {
		this.placement_name = placement_name;
	}

}
