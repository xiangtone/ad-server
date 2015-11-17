package cn.adwalker.ad.admin.report.service;

import java.util.List;

import cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean;
import cn.adwalker.ad.admin.report.vo.AllCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAdvVo;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAppVo;
import cn.adwalker.ad.admin.report.vo.ChannelCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.PlatformCensusGeneralViewVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllOutincomeVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllVo;
import cn.adwalker.ad.admin.report.vo.ReportIncomeExpensesVo;

/**
* <p>Title: ICensusGeneralViewService</p>
* <p>Description:统计概览</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-18
 */
public interface ICensusGeneralViewService {
	/**
	* <p>Title: findChannelList</p>
	* <p>Description:查看统计概览Advlist</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-18
	* @return List<ChannelListVo>
	* @version 1.0
	 */
	public List<CensusGeneralViewAdvVo> findGeneralViewAdvList(CensusGeneralViewBean bean) throws Exception;
	/**
	* <p>Title: findGeneralViewList</p>
	* <p>Description:查看统计概览Applist</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-18
	* @return List<CensusGeneralViewAppVo>
	* @version 1.0
	 */
	public List<CensusGeneralViewAppVo> findGeneralViewAppList(CensusGeneralViewBean bean) throws Exception;
	/**
	* <p>Title: findGeneralViewPlatformList</p>
	* <p>Description:平台总数据表</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-18
	* @return List<PlatformCensusGeneralViewVo>
	* @version 1.0
	 */
	public PlatformCensusGeneralViewVo findGeneralViewPlatformList(CensusGeneralViewBean bean) throws Exception;
	/**
	* <p>Title: findGeneralViewChannelList</p>
	* <p>Description:渠道总数据表</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-18
	* @return List<ChannelCensusGeneralViewVo>
	* @version 1.0
	 */
	public List<ChannelCensusGeneralViewVo> findGeneralViewChannelList(CensusGeneralViewBean bean) throws Exception;
	/**
	* <p>Title: findAllCensusGeneralViewList</p>
	* <p>Description:渠道加平台</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-28
	* @return List<AllCensusGeneralViewVo>
	* @version 1.0
	 */
	public AllCensusGeneralViewVo findAllCensusGeneralViewList(
			CensusGeneralViewBean bean)throws Exception;
	/**
	* <p>Title: flowlist</p>
	* <p>Description:流量趋势图</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-6-19
	* @return List<ReportAndroidIosAllVo>
	* @version 1.0
	 */
	public List<ReportAndroidIosAllVo> flowlist(CensusGeneralViewBean bean)throws Exception;
	/**
	* <p>Title: IncomeExpenseslist</p>
	* <p>Description:收入/支出</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-20
	* @return List<ReportIncomeExpensesVo>
	* @version 1.0
	 */
	public List<ReportIncomeExpensesVo> IncomeExpenseslist(CensusGeneralViewBean bean)throws Exception;
	/**
	* <p>Title: flowlistOuticome</p>
	* <p>Description:TODO</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-8-8
	* @return List<ReportAndroidIosAllVo>
	* @version 1.0
	 */
	public List<ReportAndroidIosAllOutincomeVo> flowlistOuticome(
			CensusGeneralViewBean bean)throws Exception;
}
