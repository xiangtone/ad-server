/*
 * IReportDevAdDayStatServer.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 20-Dec-2011
 */
package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.operation.bean.OperDevPresentbean;
import cn.adwalker.ad.admin.operation.vo.OperationDevPresentDayVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * 
* <p>Title: IOperationDevPresentDayServer</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-3-11
 */

public interface IOperationDevPresentDayServer {
	/**
	* <p>Title: findByRewardList</p>
	* <p>Description:查询奖励list</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-19
	* @return List<OperationDevPresentDayVo>
	* @version 1.0
	 */
	public List<OperationDevPresentDayVo> findByRewardList(
			OperDevPresentbean bean, IPageInfo pageInfo)throws Exception;
	/**
	* <p>Title: findSumReward</p>
	* <p>Description:查询奖励汇总</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-19
	* @return double
	* @version 1.0
	 */
	public double findSumReward(OperDevPresentbean bean, IPageInfo pageInfo)throws Exception;

}
