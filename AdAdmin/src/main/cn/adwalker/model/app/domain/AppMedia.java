/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.app.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 销售信息VO
 * <p>
 * Title: AdvSalesmanForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-29
 */
public class AppMedia implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -3651037658706450609L;
	/** 主键ID */
	private Long id;
	
	/** 销售负责人 */
	private String name;
	/** 销售负责人手机号 */
	private String mobile;
	/** 销售负责人QQ */
	private String qq;
	/** 创建时间 */
	private Date create_time;

	/** 创建时间 */
	private Long create_user;
	/** 状态 */
	private Integer status;
	/** 区域类型(4:华南、1:华东、2:华北、0:平台) */
	private Integer area_type;
	
	private Long sys_user_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * @return area_type
	 */
	public Integer getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type the area_type to set
	 */
	
	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return sys_user_id
	 */
	public Long getSys_user_id() {
		return sys_user_id;
	}

	/**
	 * @param sys_user_id the sys_user_id to set
	 */
	
	public void setSys_user_id(Long sys_user_id) {
		this.sys_user_id = sys_user_id;
	}
}
