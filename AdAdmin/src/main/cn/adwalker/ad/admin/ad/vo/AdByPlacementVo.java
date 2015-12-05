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
public class AdByPlacementVo {
	// 广告id
	private Long adId;
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
}
