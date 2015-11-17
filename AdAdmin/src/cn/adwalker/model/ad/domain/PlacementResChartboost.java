/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.ad.domain;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: PlacementResChartboost
 * </p>
 * <p>
 * Description:活动插屏
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-12
 */
public class PlacementResChartboost implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -2572600287197067186L;

	// 主键
	private Long id;

	// 响应方式
	private Integer response_type;

	private Integer response_category;

	// 跳转地址
	private String redirect_url;

	// 插屏横向
	private String img_horizontal;

	// 插屏纵向
	private String img_vertical;
	
	private Integer weight;

	// 活动id
	private Long placement_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImg_horizontal() {
		return img_horizontal;
	}

	public void setImg_horizontal(String img_horizontal) {
		this.img_horizontal = img_horizontal;
	}

	public String getImg_vertical() {
		return img_vertical;
	}

	public void setImg_vertical(String img_vertical) {
		this.img_vertical = img_vertical;
	}

	public Integer getResponse_type() {
		return response_type;
	}

	public void setResponse_type(Integer response_type) {
		this.response_type = response_type;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public Integer getResponse_category() {
		return response_category;
	}

	public void setResponse_category(Integer response_category) {
		this.response_category = response_category;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
