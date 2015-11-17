package cn.adwalker.model.app.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: PlacementTypeRel
 * </p>
 * <p>
 * Description:TOD
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-1-22
 */

public class Page implements Entity {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -1798887056290766844L;

	/** 主键 */
	private Long id;

	/** 状态（0：初始化，1：正常） */
	private int status;

	/**
	 * 应用id
	 */
	private Long app_id;

	/**
	 * 类型id
	 */
	private Integer type_id;

	/**
	 * 扣量比率
	 */
	private Double rate;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date updateTime;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}
