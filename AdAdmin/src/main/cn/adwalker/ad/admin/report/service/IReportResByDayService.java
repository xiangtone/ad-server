/**
 * 
 */
package cn.adwalker.ad.admin.report.service;

import java.util.List;

import cn.adwalker.ad.admin.report.bean.ResByDayQuery;
import cn.adwalker.ad.admin.report.vo.ReportResByDay;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.repository.IBaseService;

/**
* <p>Title: IReportResByDayService</p>
* <p>Description:Res数据统计</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年10月17日
 */
public interface IReportResByDayService extends IBaseService {

	/**
	* <p>Title: findByPage</p>
	* <p>Description:查詢Res数据统计</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年10月17日
	* @return List<ReportResByDay>
	* @version 1.0
	 */
	public List<ReportResByDay> findByPage(ResByDayQuery bean,
			IPageInfo pageInfo) throws Exception;

	/**
	* <p>Title: findAll</p>
	* <p>Description:查詢Res数据统计下载</p>
	* @param bean
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年10月17日
	* @return List<Object>
	* @version 1.0
	 */
	public List<Object> findAll(ResByDayQuery bean) throws Exception;

	/**
	* <p>Title: findTotal</p>
	* <p>Description:排序查詢Res数据统计</p>
	* @param bean
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年10月17日
	* @return ReportResByDay
	* @version 1.0
	 */

	public ReportResByDay findTotal(ResByDayQuery bean) throws Exception;
}