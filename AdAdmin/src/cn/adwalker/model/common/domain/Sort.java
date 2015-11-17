/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.common.domain;

import cn.adwalker.core.repository.Entity;


/**
 * 查看活动VO 
* <p>Title: PlacementVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-7
 */
public class Sort implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -5209045772878361485L;
	//定向类别id
	private Integer type;
	//属性值
	private Long id ;
	//活动分类id
	private String content_value ;
	//名字
	private String name ;
	//父类id
	private Integer sort ;
	

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent_value() {
		return content_value;
	}
	public void setContent_value(String content_value) {
		this.content_value = content_value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
