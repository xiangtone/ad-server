/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.form;

/**
 * 活动主持VO
 * <p>
 * Title: PlacementEditForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-7
 */
public class PlacementEditForm {

	private Long id;

	// 投放名称
	private String name;

	// 星级
	private Double star_level;

	// 状态
	private Integer status;

	// 平台类型
	private String os;

	/**
	 * 分类id
	 */
	private Long category_id;

	/**
	 * 广告语
	 */
	private String slogan;

	/**
	 * 关键字
	 */
	private String keyword;
	
	/**
	 * 是否热门推荐
	 */
	private Integer popular_app;
	/**
	 * 是否优先推荐
	 */
	private Integer priority;
	/**
	 * 是否是新应用
	 */
	private Integer new_app;
	/**
	 * 是否是链接
	 */
	private Integer is_url_params;

	/**
	 * 活动介绍
	 */
	private String campaign_desc;

	private String[] types;

	/********************** getter\setter方法 ****************************/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getStar_level() {
		return star_level;
	}

	public void setStar_level(Double star_level) {
		this.star_level = star_level;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getCampaign_desc() {
		return campaign_desc;
	}

	public void setCampaign_desc(String campaign_desc) {
		this.campaign_desc = campaign_desc;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getNew_app() {
		return new_app;
	}

	public void setNew_app(Integer new_app) {
		this.new_app = new_app;
	}

	public Integer getPopular_app() {
		return popular_app;
	}

	public void setPopular_app(Integer popular_app) {
		this.popular_app = popular_app;
	}

	public Integer getIs_url_params() {
		return is_url_params;
	}

	public void setIs_url_params(Integer is_url_params) {
		this.is_url_params = is_url_params;
	}
	
}
