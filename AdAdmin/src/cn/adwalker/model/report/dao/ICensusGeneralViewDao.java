package cn.adwalker.model.report.dao;

import java.util.List;

import cn.adwalker.ad.admin.report.bean.CensusGeneralViewBean;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAdvVo;
import cn.adwalker.ad.admin.report.vo.CensusGeneralViewAppVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllOutincomeVo;
import cn.adwalker.ad.admin.report.vo.ReportAndroidIosAllVo;
import cn.adwalker.ad.admin.report.vo.ReportIncomeExpensesVo;
import cn.adwalker.core.repository.IBaseDao;

/**
 * <p>
 * Title: ICensusGeneralViewDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-18
 */
public interface ICensusGeneralViewDao extends IBaseDao {
	/**
	* <p>Title: findGeneralViewAppList</p>
	* <p>Description:每个媒体收入的和</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-5
	* @return List<CensusGeneralViewAppVo>
	* @version 1.0
	 */
	public	List<CensusGeneralViewAppVo> findGeneralViewAppList(
			CensusGeneralViewBean bean) throws Exception;
	/**
	* <p>Title: findGeneralsumCost</p>
	* <p>Description:媒体收入总和</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-5
	* @return double
	* @version 1.0
	 */
	public double findGeneralsumCost(CensusGeneralViewBean bean)throws Exception;
	/**
	* <p>Title: findGeneralViewAdvList</p>
	* <p>Description:广告消耗的和top10</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-5
	* @return List<CensusGeneralViewAdvVo>
	* @version 1.0
	 */
	public List<CensusGeneralViewAdvVo> findGeneralViewAdvList(
			CensusGeneralViewBean bean)throws Exception;
	/**
	* <p>Title: findAdvsumCost</p>
	* <p>Description:广告总sum</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-5
	* @return double
	* @version 1.0
	 */
	public double findAdvsumCost(CensusGeneralViewBean bean)throws Exception;
	/**
	* <p>Title: flowlist</p>
	* <p>Description:l流量趋势图ANDROID</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-6-19
	* @return List<ReportAndroidIosAllVo>
	* @version 1.0
	 */
	public List<ReportAndroidIosAllVo> flowlist(CensusGeneralViewBean bean)throws Exception;
	
	/**
	* <p>Title: findAllList</p>
	* <p>Description:渠道和平台收入支出</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-26
	* @return List<ReportIncomeExpensesVo>
	* @version 1.0
	 */
	public List<ReportIncomeExpensesVo> findAllList(CensusGeneralViewBean bean)throws Exception;
	/**
	* <p>Title: flowlistOuticome</p>
	* <p>Description:TODO</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-8-8
	* @return List<ReportAndroidIosAllOutincomeVo>
	* @version 1.0
	 */
	public List<ReportAndroidIosAllOutincomeVo> flowlistOuticome(
			CensusGeneralViewBean bean)throws Exception;

}
