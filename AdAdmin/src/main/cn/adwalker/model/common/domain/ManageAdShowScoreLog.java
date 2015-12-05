/**
 * 
 */
package cn.adwalker.model.common.domain;

import java.util.Date;

/**
 * @author wjp
 * 管理员设置广告显示积分
 */
public class ManageAdShowScoreLog {

	/** 主键编号 */
	private Long id;
	
	/** 操作人编号 */
	private Long managerId;
	
	/** 广告主编号 */
	private Long advertiserId;
	
	/** 广告编号 */
	private Long adId;
	
	/** 增加的积分 */
	private Long addScore;
	
	/** 状态，可设置为无效，1：有效，0：无效 */
	private int status;
	
	/** 逻辑删除，0：未删除，1：已删除 */
	private int del;
	
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
	 * @return the addScore
	 */
	public Long getAddScore() {
		return addScore;
	}

	/**
	 * @param addScore the addScore to set
	 */
	public void setAddScore(Long addScore) {
		this.addScore = addScore;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the del
	 */
	public int getDel() {
		return del;
	}

	/**
	 * @param del the del to set
	 */
	public void setDel(int del) {
		this.del = del;
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
