/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.ad.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: PlacementVo
 * </p>
 * <p>
 * Description:活动投放
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-7
 */
public class Placement implements Entity {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 2511610362440944938L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 投放名称
	 */
	private String name;

	/**
	 * 平台类型
	 */
	private String os;

	/**
	 * 关键字
	 */
	private String keyword;

	/**
	 * 广告语
	 */
	private String slogan;

	/**
	 * 活动介绍
	 */
	private String campaign_desc;

	/**
	 * 
	 */
	private String icon_url;

	/**
	 * 排期开始时间
	 */
	private Date plan_start;

	/**
	 * 活动类别(对应app的应用分类)
	 */
	private Long category_id;

	/**
	 * 排期结束时间
	 */
	private Date plan_end;

	/**
	 * 数据入库时间
	 */
	private Date create_time;

	/**
	 * 操作人id
	 */
	private Long create_user;

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
	 * 应用星级
	 */
	private Double star_level;
	
	
	private String config_id;
	//  增加 IS_DSP 判断是否是DSP广告 默认为0（否）,
	private Integer is_dsp;
	//   增加 DSP_ID。DSP标识. 
	private String dsp_id;
	/********************** getter\setter方法 ****************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public Date getPlan_start() {
		return plan_start;
	}

	public void setPlan_start(Date plan_start) {
		this.plan_start = plan_start;
	}

	public Date getPlan_end() {
		return plan_end;
	}

	public void setPlan_end(Date plan_end) {
		this.plan_end = plan_end;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCampaign_desc() {
		return campaign_desc;
	}

	public void setCampaign_desc(String campaign_desc) {
		this.campaign_desc = campaign_desc;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getConfig_id() {
		return config_id;
	}

	public void setConfig_id(String config_id) {
		this.config_id = config_id;
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

	public Integer getIs_dsp() {
		return is_dsp;
	}

	public void setIs_dsp(Integer is_dsp) {
		this.is_dsp = is_dsp;
	}

	public String getDsp_id() {
		return dsp_id;
	}

	public void setDsp_id(String dsp_id) {
		this.dsp_id = dsp_id;
	}

	public Integer getIs_url_params() {
		return is_url_params;
	}

	public void setIs_url_params(Integer is_url_params) {
		this.is_url_params = is_url_params;
	}
	
}
