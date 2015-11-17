/**
 * 
 */
package cn.adwalker.model.ad.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * @author fangguanhong
 * 
 */
public class PlacementScreenshot implements Entity {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 6014935547138989297L;

	/** 主键编号 */
	private Long id;

	/** 广告ID */
	private Long placement_id;

	/** 图片地址 */
	private String img_url;

	/** 排序 */
	private Integer sort;

	/** 逻辑删除标示 */
	private Integer del;

	/** 创建时间 */
	private Date create_time;

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

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the del
	 */
	public Integer getDel() {
		return del;
	}

	/**
	 * @param del
	 *            the del to set
	 */
	public void setDel(Integer del) {
		this.del = del;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
