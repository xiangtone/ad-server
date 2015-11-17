package cn.adwalker.ad.control.domain;

import java.io.Serializable;
import java.util.Date;

public class Ad implements Serializable {
	private static final long serialVersionUID = -549029193439658179L;
	// 主键
	private Long id;
	// 活动id
	private Long placementId;
	// 媒体id
	private Long mediaId;
	// 日投放量
	private Double budgetDay;
	// 日投放量单位
	private String budgetType;
	/**
	 * 状态(-40：草稿、-30：超量下线;-20：推广结束;-10、暂停；-1：人工下线;0:初始化；1：上线)
	 */
	private Integer status;
	/**
	 * 广告类型（渠道或媒体）
	 */
	private Integer adType;
	/**
	 * 上线时间
	 */
	private Date onlineTime;
	/**
	 * 下线时间
	 */
	private Date offlineTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlacementId() {
		return placementId;
	}

	public void setPlacementId(Long placementId) {
		this.placementId = placementId;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public Double getBudgetDay() {
		return budgetDay;
	}

	public void setBudgetDay(Double budgetDay) {
		this.budgetDay = budgetDay;
	}

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

}
