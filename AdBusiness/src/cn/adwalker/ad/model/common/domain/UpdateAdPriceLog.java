/**
 * 
 */
package cn.adwalker.ad.model.common.domain;

import java.util.Date;

/**
 * @author fangguanhong
 *	广告价格修改日志记录实体
 */
public class UpdateAdPriceLog {
	/** 主键 */
	private Long id;
	/** 管理员id */
	private Long managerId;
	/** 广告主id */
	private Long AdvId;
	/** 广告id */
	private Long AdId;
	/** 原来的价格 */
	private Double formerPrice;
	/** 修改后的价格 */
	private Double updatePrice;
	/** sysdate */
	private Date createTime;
	
	public UpdateAdPriceLog(){
		this.createTime = new Date();
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
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	/**
	 * @return the advId
	 */
	public Long getAdvId() {
		return AdvId;
	}
	/**
	 * @param advId the advId to set
	 */
	public void setAdvId(Long advId) {
		AdvId = advId;
	}
	/**
	 * @return the adId
	 */
	public Long getAdId() {
		return AdId;
	}
	/**
	 * @param adId the adId to set
	 */
	public void setAdId(Long adId) {
		AdId = adId;
	}
	/**
	 * @return the formerPrice
	 */
	public Double getFormerPrice() {
		return formerPrice;
	}
	/**
	 * @param formerPrice the formerPrice to set
	 */
	public void setFormerPrice(Double formerPrice) {
		this.formerPrice = formerPrice;
	}
	/**
	 * @return the updatePrice
	 */
	public Double getUpdatePrice() {
		return updatePrice;
	}
	/**
	 * @param updatePrice the updatePrice to set
	 */
	public void setUpdatePrice(Double updatePrice) {
		this.updatePrice = updatePrice;
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

	
}
