/**
 * 
 */
package cn.adwalker.model.operation.domain;

import java.util.Date;


/**
 * @author wjp
 * 广告主广告效果基础数据
 */
public class FinanceAdEffectData {

	/** 主键id */
	private Long id;
	
	/** 统计日期 */
	private String statDate;
	
	/** 广告主id */
	private Long advId;
	
	/** 广告id */
	private Long adId;
	
	/** 有效激活数 */
	private Integer validAmount;
	
	/** 创建时间 */
	private Date createTime;
	
	private int flagConfirm;

	
	public int getFlagConfirm() {
		return flagConfirm;
	}

	public void setFlagConfirm(int flagConfirm) {
		this.flagConfirm = flagConfirm;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the statDate
	 */
	public String getStatDate() {
		return statDate;
	}

	/**
	 * @param statDate the statDate to set
	 */
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	

	public Long getAdvId() {
		return advId;
	}

	public void setAdvId(Long advId) {
		this.advId = advId;
	}

	/**
	 * @return the adId
	 */
	public Long getAdId() {
		return adId;
	}

	/**
	 * @param adId the adId to set
	 */
	public void setAdId(Long adId) {
		this.adId = adId;
	}

	/**
	 * @return the validAmount
	 */
	public Integer getValidAmount() {
		return validAmount;
	}

	/**
	 * @param validAmount the validAmount to set
	 */
	public void setValidAmount(Integer validAmount) {
		this.validAmount = validAmount;
	}
	
	
	
}
