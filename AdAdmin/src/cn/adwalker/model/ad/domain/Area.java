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
* <p>Title: Area</p>
* <p>Description:区域</p>
* <p>Company: emar</p> 
* @author   lichuang
* @date       2014-3-12
 */
public class Area implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -3651037658706450609L;
	/** 主键ID */
	private Long id;
	/** 区域类型 */
	private String area_name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	
}
