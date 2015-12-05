/**
 * 
 */
package cn.adwalker.ad.admin.report.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.IBaseService;
import cn.adwalker.ad.admin.report.bean.SalesAchievementBean;
import cn.adwalker.ad.admin.report.vo.ReportSalesAchievementVo;
import cn.adwalker.ad.admin.report.vo.SumSalesAchievementVo;

/**
* <p>Title: IReportSalesAchievementService</p>
* <p>Description:销售业绩</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-8-19
 */
public interface IReportSalesAchievementService extends IBaseService {

 

	/**
	* <p>Title: findByPage</p>
	* <p>Description:销售业绩查询</p>
	* @param pageInfo
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-8-19
	* @return List<ReportAdByHour>
	* @version 1.0
	 */
	public List<ReportSalesAchievementVo> findByPage(IPageInfo pageInfo, SalesAchievementBean bean)
			throws Exception;

	/**
	* <p>Title: findAll</p>
	* <p>Description:销售业绩查询Down</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-8-19
	* @return List<Object>
	* @version 1.0
	 */
	public List<ReportSalesAchievementVo> findAll(SalesAchievementBean bean) throws Exception;
	/**
	* <p>Title: getSumSales</p>
	* <p>Description:销售业绩查询和</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-8-20
	* @return SumSalesAchievementVo
	* @version 1.0
	 */
	public SumSalesAchievementVo getSumSales(SalesAchievementBean bean)throws Exception;

}