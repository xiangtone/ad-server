package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostYJFbean;
import cn.adwalker.ad.admin.finance.bean.FinanceRevenueCostbean;
import cn.adwalker.ad.admin.finance.vo.FinanceRevenueCostVo;
import cn.adwalker.ad.admin.finance.vo.FinanceRevenueCostYJFVo;
import cn.adwalker.ad.admin.finance.vo.RevenueCostVo;
import cn.adwalker.ad.admin.finance.vo.RevenueCostYJFVo;

/**
* <p>Title: IFinanceRevenueCostService</p>
* <p>Description:确认收入成本Service 接口</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-7
 */
public interface IFinanceRevenueCostService {
	
	/**
	* <p>Title: findByCost</p>
	* <p>Description:确认收入成本list</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-7
	* @return List<FinanceRevenueCostVo>
	* @version 1.0
	 */
	public List<FinanceRevenueCostVo> findByCost(
			FinanceRevenueCostbean bean, IPageInfo pageInfo) throws Exception;
	/**
	* <p>Title: findByList</p>
	* <p>Description:确认收入成本(平台)list</p>
	* @param bean
	* @param pageInfo
	* @return
	* @author lichuang
	* @date 2013-7-3
	* @return List<FinanceRevenueCostYJFVo>
	* @version 1.0
	 */
	public List<FinanceRevenueCostYJFVo> findByList(
			FinanceRevenueCostYJFbean bean, IPageInfo pageInfo)throws Exception ;
	/**
	* <p>Title: getRevenueCostSum</p>
	* <p>Description:求和</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-8-27
	* @return RevenueCostVo
	* @version 1.0
	 */
	public RevenueCostVo getRevenueCostSum(FinanceRevenueCostbean bean)throws Exception ;
	/**
	* <p>Title: getRevenueCostYJFSum</p>
	* <p>Description:求和YJF</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-8-27
	* @return RevenueCostYJFVo
	* @version 1.0
	 */
	public RevenueCostYJFVo getRevenueCostYJFSum(FinanceRevenueCostYJFbean bean)throws Exception ;
	
}
