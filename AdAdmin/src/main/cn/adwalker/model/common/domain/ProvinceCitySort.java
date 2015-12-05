/*
 * BankInfoChannel.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 14-Dec-2011
 */
package cn.adwalker.model.common.domain;


/**
 * 功能概述：<br>
 * 银行信息实体
 * 
 * @author zhaozengbin
 */
public class ProvinceCitySort {
	/** 主键编号 */
	private Long id;

	/** 上级ID（如果为0，则为顶级） */
	private Long parent_Id;

	/** 省份城市 */
	private String province_City;
	
	/** 排序 */	
	private Long sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParent_Id() {
		return parent_Id;
	}

	public void setParent_Id(Long parent_Id) {
		this.parent_Id = parent_Id;
	}

	public String getProvince_City() {
		return province_City;
	}

	public void setProvince_City(String province_City) {
		this.province_City = province_City;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
		
	

	
}
