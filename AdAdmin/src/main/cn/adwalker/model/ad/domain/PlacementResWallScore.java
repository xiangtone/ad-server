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
 * Title: PlacementResWallScore
 * </p>
 * <p>
 * Description:活动墙
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-12
 */
public class PlacementResWallScore implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -1994289908373335405L;


	// 主键
	private Long id;
	

	// 0-跳至详情页 1-跳至注册网页jump_url
	private Integer response_type;

	private Integer response_category;

	// 跳转地址
	private String redirect_url;

	// 积分描述
	private String score_desc;
	
	
	// 积分长描述
	private String score_long_desc;
	
	private String weixin_desc;

	// banner图片地址
	private String banner_url;
	// 权重
	private Integer weight;
	//积分延时
	private Integer score_delay;
	
	private Integer	limit_time;//(秒)
	
	// 活动id
	private Long placement_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getScore_desc() {
		return score_desc;
	}

	public void setScore_desc(String score_desc) {
		this.score_desc = score_desc;
	}

	public String getScore_long_desc() {
		return score_long_desc;
	}

	public void setScore_long_desc(String score_long_desc) {
		this.score_long_desc = score_long_desc;
	}

	public String getBanner_url() {
		return banner_url;
	}

	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
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

	public Integer getScore_delay() {
		return score_delay;
	}

	public void setScore_delay(Integer score_delay) {
		this.score_delay = score_delay;
	}

	public String getWeixin_desc() {
		return weixin_desc;
	}

	public void setWeixin_desc(String weixin_desc) {
		this.weixin_desc = weixin_desc;
	}

	public Integer getLimit_time() {
		return limit_time;
	}

	public void setLimit_time(Integer limit_time) {
		this.limit_time = limit_time;
	}
	
}
