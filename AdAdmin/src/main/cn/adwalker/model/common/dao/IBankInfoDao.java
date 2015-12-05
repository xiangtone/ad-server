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

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.channel.domain.BankInfoChannel;
import cn.adwalker.model.common.domain.BankInfo;

/**
 * 功能概述：<br>
 * 银行信息关机接口
 * 
 * @author zhaozengbin
 */
public interface IBankInfoDao extends IBaseDao<BankInfo> {
	/**
	 * 根据用户名查找银行信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<BankInfo> findByUser(Long userId) throws Exception;
		
	 
	
	 
	/**
	 * 根据用户ID和用户角色查找用户信息
	 * 
	 * @param userId
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public BankInfo getDevBlank(Long userId,Integer role) throws Exception;
	/**
	 * 删除银行信息
	 * 
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	public Integer deleteById(Long id) throws Exception;

	/**
	 * 删除银行信息
	 * 
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	public Integer deleteByUserId(Long userId) throws Exception;

	/**
	 * 根据ID查询银行信息
	 * 
	 * @param bankInfo
	 * @return
	 * @throws Exception
	 */
	public BankInfo findById(Long id) throws Exception;

	/**
	 * 
	* <p>Title: update</p>
	* <p>Description:更新</p>
	* @param bankInfo
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-7-9
	* @return Integer
	* @version 1.0
	 */
	public Integer update(BankInfo bankInfo)throws Exception;

	/**
	 * 
	* <p>Title: insert</p>
	* <p>Description:插入</p>
	* @param bankInfo
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-7-9
	* @return Integer
	* @version 1.0
	 */
	public long insert(BankInfo bankInfo)throws Exception;
	
	/**
	 * 
	* <p>Title: insertbankInfo</p>
	* <p>Description:TODO</p>
	* @param bankInfoChannel
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-8-26
	* @return int
	* @version 1.0
	 */
	public long insertbankInfo(BankInfoChannel bankInfoChannel) throws Exception;
	
	
}
