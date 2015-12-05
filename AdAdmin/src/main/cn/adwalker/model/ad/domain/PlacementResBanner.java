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
 * Title: PlacementResBanner
 * </p>
 * <p>
 * Description:活动banner
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-12
 */
public class PlacementResBanner implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 4739673150677390746L;

	// 主键
	private Long id;

	// 0-跳至详情页 1-跳至注册网页jump_url
	private Integer response_type;

	private Integer response_category;

	// 跳转地址
	private String redirect_url;

	// banner图片1
	private String img_url_first;

	// banner图片2
	private String img_url_second;

	// banner图片3
	private String img_url_third;
	
	private Integer weight;

	// 活动id
	private Long placement_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getImg_url_first() {
		return img_url_first;
	}

	public void setImg_url_first(String img_url_first) {
		this.img_url_first = img_url_first;
	}

	public String getImg_url_second() {
		return img_url_second;
	}

	public void setImg_url_second(String img_url_second) {
		this.img_url_second = img_url_second;
	}

	public String getImg_url_third() {
		return img_url_third;
	}

	public void setImg_url_third(String img_url_third) {
		this.img_url_third = img_url_third;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public Integer getResponse_type() {
		return response_type;
	}

	public void setResponse_type(Integer response_type) {
		this.response_type = response_type;
	}

	public Integer getResponse_category() {
		return response_category;
	}

	public void setResponse_category(Integer response_category) {
		this.response_category = response_category;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
