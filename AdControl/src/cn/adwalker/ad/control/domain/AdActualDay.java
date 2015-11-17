package cn.adwalker.ad.control.domain;

import java.io.Serializable;
import java.util.Date;

public class AdActualDay implements Serializable {

	private static final long serialVersionUID = 6537106732667085938L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 广告ID
	 */
	private Long adId;
	
	/**
	 * 广告展示数
	 */
	private Integer impressionsAmount;
	
	/**
	 * 广告点击数
	 */
	private Integer clickAmount;
	
	/**
	 * 广告下载数
	 */
	private Integer downloadAmount;
	
	/**
	 * 广告激活数
	 */
	private Integer actionAmount;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 记录广告数量的日期,格式2013-10-01
	 */
	private String staticDate;

	/**
	 * 状态,0为有效,1'无效
	 */
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
