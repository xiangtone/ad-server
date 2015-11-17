/**
 * 
 */
package cn.adwalker.ad.model.common.domain;

import java.util.Date;

/**
 * @author wjp
 * 广告主还贷记录
 */
public class AdvRepayLog {

	/** 主键编号 */
	private Long id;
	
	/** 操作人编号 */
	private Long managerId;
	
	/** 广告主编号 */
	private Long advertiserId;
	
	/** 应付款 */
	private int copeMoney;
	
	/** 实际付款 */
	private int actualMoney;
	
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
	 * @return the copeMoney
	 */
	public int getCopeMoney() {
		return copeMoney;
	}

	/**
	 * @param copeMoney the copeMoney to set
	 */
	public void setCopeMoney(int copeMoney) {
		this.copeMoney = copeMoney;
	}

	/**
	 * @return the actualMoney
	 */
	public int getActualMoney() {
		return actualMoney;
	}

	/**
	 * @param actualMoney the actualMoney to set
	 */
	public void setActualMoney(int actualMoney) {
		this.actualMoney = actualMoney;
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
