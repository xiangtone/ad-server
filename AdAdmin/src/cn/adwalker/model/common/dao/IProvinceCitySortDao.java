/*
 * IBankInfoDao.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 14-Dec-2011
 */
package cn.adwalker.model.common.dao;

import java.util.List;

import cn.adwalker.model.common.domain.ProvinceCitySort;

/**
 * 功能概述：<br>
 * 银行信息关机接口
 * 
 * @author zhaozengbin
 */
public interface IProvinceCitySortDao {
	
		
	/**
	 * 
	* <p>Title: findIdCity</p>
	* <p>Description:TODO </p>
	* @param parent_id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2012-11-23
	* @return List<ProvinceCitySort>
	* @version 1.0
	* @throws
	 */
	public List<ProvinceCitySort> findIdCity( int parent_id) throws Exception;
	
	/**
	 * 根据省份城市
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ProvinceCitySort> findprovinceCity( int parent_id) throws Exception;
	
	/**
	 * 根据ID查询 省份城市
	 * @param category_Id
	 * @return
	 */
	public ProvinceCitySort findprovinceCityBean(Integer category_Id);
	
	/***
	 * 根据ID查询 所在省份的所有城市
	 * @param category_Id
	 * @return
	 */
	public List<ProvinceCitySort> findprovinceCityList(Integer category_Id);
}
