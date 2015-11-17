package cn.adwalker.ad.control.vo;

import java.util.Date;

public class AdActualDayVo {

	private Long id;
	private Long adId;
	private Integer impressionsAmount;
	private Integer clickAmount;
	private Integer downloadAmount;
	private Integer actionAmount;
	private Date updateTime;
	private String staticDate;
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Integer getImpressionsAmount() {
		return impressionsAmount;
	}

	public void setImpressionsAmount(Integer impressionsAmount) {
		this.impressionsAmount = impressionsAmount;
	}

	public Integer getClickAmount() {
		return clickAmount;
	}

	public void setClickAmount(Integer clickAmount) {
		this.clickAmount = clickAmount;
	}

	public Integer getDownloadAmount() {
		return downloadAmount;
	}

	public void setDownloadAmount(Integer downloadAmount) {
		this.downloadAmount = downloadAmount;
	}

	public Integer getActionAmount() {
		return actionAmount;
	}

	public void setActionAmount(Integer actionAmount) {
		this.actionAmount = actionAmount;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStaticDate() {
		return staticDate;
	}

	public void setStaticDate(String staticDate) {
		this.staticDate = staticDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
