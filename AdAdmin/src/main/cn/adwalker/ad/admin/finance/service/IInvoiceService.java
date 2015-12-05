package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.ad.admin.finance.bean.IncomeBean;
import cn.adwalker.ad.admin.finance.bean.InvoiceBean;
import cn.adwalker.ad.admin.finance.bean.PaymentBean;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.IncomeVo;
import cn.adwalker.ad.admin.finance.vo.InvoiceVo;
import cn.adwalker.ad.admin.finance.vo.PaymentVo;
import cn.adwalker.core.page.IPageInfo;

public interface IInvoiceService {

	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:开票明细
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014年11月13日
	 * @return List<IncomeDetailVo>
	 * @version 1.0
	 */
	public List<InvoiceVo> findByPage(InvoiceBean bean, IPageInfo pageInfo) throws Exception;

	/**
	 * 批量开票
	 * @param objList
	 * @return
	 * @throws Exception
	 */
	public int[] updateInvoice(List<Object[]> objList)throws Exception;

	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:付款明细
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014年11月13日
	 * @return List<IncomeDetailVo>
	 * @version 1.0
	 */
	public List<PaymentVo> findByPage(PaymentBean bean, IPageInfo pageInfo) throws Exception;
	
	/**
	 * 批量付款
	 * @param objList
	 * @return
	 * @throws Exception
	 */
	public int[] updatePayment(List<Object[]> objList)throws Exception;

	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:付款明细
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014年11月13日
	 * @return List<IncomeDetailVo>
	 * @version 1.0
	 */
	public List<IncomeVo> findByPage(IncomeBean bean, IPageInfo pageInfo) throws Exception;
	/**
	* <p>Title: findSum</p>
	* <p>Description:求和</p>
	* @param bean
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年12月17日
	* @return AchievementReportSumVo
	* @version 1.0
	 */
	public AchievementReportSumVo findSum(IncomeBean bean)throws Exception;
	
}
