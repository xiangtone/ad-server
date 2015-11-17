/**
 * 
 */
package cn.adwalker.ad.model.common.domain;

import java.util.Date;

/**
 * @author wjp
 * 广告主授信记录表
 */
public class AdvGiveCreditLog {

	/** 主键编号 */
	private Long id;
	
	/** 操作人编号 */
	private Long managerId;
	
	/** 广告主编号 */
	private Long advertiserId;
	
	/** 授信金额 */
	private int creditMoney;
	
	/** 创建时间 */
	private Date createTime;

	
	
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
	 * @return the advertiserId
	 */
	public Long getAdvertiserId() {
		return advertiserId;
	}

	/**
	 * @param advertiserId the advertiserId to set
	 */
	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}

	/**
	 * @return the creditMoney
	 */
	public int getCreditMoney() {
		return creditMoney;
	}

	/**
	 * @param creditMoney the creditMoney to set
	 */
	public void setCreditMoney(int creditMoney) {
		this.creditMoney = creditMoney;
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
