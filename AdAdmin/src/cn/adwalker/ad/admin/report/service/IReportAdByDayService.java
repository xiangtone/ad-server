/**
 * 
 */
package cn.adwalker.ad.admin.report.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.IBaseService;
import cn.adwalker.ad.admin.report.bean.AdByDayQuery;
import cn.adwalker.ad.admin.report.vo.ReportAdByDay;

/**
 * 功能概述：<br>
 * 开发者SDK应用服务接口 
 * 
 * @author guoyongxiang
 */
public interface IReportAdByDayService extends IBaseService {

 

	/**
	 * @throws Exception
	 * @param bean
	 * 
	 *            <p>
	 *            Title: findByPage
	 *            </p>
	 *            <p>
	 *            Description:后台应用列表分页查询
	 *            </p>
	 * @param pageInfo
	 * @return
	 * @author cuidd
	 * @date 2013-1-17
	 * @return List<VApplication>
	 * @version 1.0
	 */
	public List<ReportAdByDay> findByPage(IPageInfo pageInfo, AdByDayQuery bean)
			throws Exception;

	/**
	* <p>Title: findAll</p>
	* <p>Description:TODO</p>
	* @param bean
	* @return
	* @author cuidd
	* @date 2013-8-15
	* @return List<Object>
	* @version 1.0
	 * @throws Exception 
	*/
	public List<Object> findAll(AdByDayQuery bean) throws Exception;

	/**
	* <p>Title: findTotal</p>
	* <p>Description:TODO</p>
	* @param bean
	* @return
	* @author cuidd
	* @date 2013-8-16
	* @return ReportAdByHour
	* @version 1.0
	 * @throws Exception 
	*/
	
	public ReportAdByDay findTotal(AdByDayQuery bean) throws Exception;
}