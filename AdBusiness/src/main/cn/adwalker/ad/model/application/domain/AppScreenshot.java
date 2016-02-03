package cn.adwalker.ad.model.application.domain;

import java.util.Date;

/**
 * 功能概述：<br>
 * 应用截图实体
 * 
 * @author zhaozengbin
 */
public class AppScreenshot {
	/** 主键编号 */
	private Long id;

	/** 开发者ID */
	private Long developerId;

	/** 应用ID */
	private Long appId;

	/** 图片地址 */
	private String imgUrl;

	/** 排序 */
	private Integer sort;

	/** 逻辑删除标示 */
	private Integer del;

	/** 创建时间 */
	private Date createTime;

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the developerId.
	 */
	public Long getDeveloperId() {
		return developerId;
	}

	/**
	 * @param developerId
	 *            The developerId to set.
	 */
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}

	/**
	 * @return Returns the appId.
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            The appId to set.
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	/**
	 * @return Returns the imgUrl.
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl
	 *            The imgUrl to set.
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return Returns the sort.
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            The sort to set.
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return Returns the del.
	 */
	public Integer getDel() {
		return del;
	}

	/**
	 * @param del
	 *            The del to set.
	 */
	public void setDel(Integer del) {
		this.del = del;
	}

	/**
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
