package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.ad.admin.finance.bean.CostBean;
import cn.adwalker.ad.admin.finance.bean.InvoiceChannelBean;
import cn.adwalker.ad.admin.finance.bean.PaymentChannelBean;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.CostVo;
import cn.adwalker.ad.admin.finance.vo.IncomeVo;
import cn.adwalker.ad.admin.finance.vo.InvoiceChannelVo;
import cn.adwalker.ad.admin.finance.vo.PaymentChannelVo;
import cn.adwalker.core.page.IPageInfo;

public interface IInvoiceChannelService {

	/**
	* <p>Title: findByPage</p>
	* <p>Description:渠道收票</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return List<InvoiceChannelVo>
	* @version 1.0
	 */
	public List<InvoiceChannelVo> findByPage(InvoiceChannelBean bean, IPageInfo pageInfo) throws Exception;

	/**
	* <p>Title: InvoiceChannel</p>
	* <p>Description:批量收票</p>
	* @param objList
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return int[]
	* @version 1.0
	 */
	public int[] updateInvoiceChannel(List<Object[]> objList)throws Exception;

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
	public List<PaymentChannelVo> findByPage(PaymentChannelBean bean, IPageInfo pageInfo) throws Exception;
	
	/**
	 * 批量付款
	 * @param objList
	 * @return
	 * @throws Exception
	 */
	public int[] updatePayment(List<Object[]> objList)throws Exception;

	/**
	* <p>Title: findByPage</p>
	* <p>Description:渠道成本结算明细</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return List<IncomeVo>
	* @version 1.0
	 */
	public List<CostVo> findByPage(CostBean bean, IPageInfo pageInfo) throws Exception;
	/**
	* <p>Title: findSum</p>
	* <p>Description:渠道求和</p>
	* @param bean
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年12月17日
	* @return AchievementReportSumVo
	* @version 1.0
	 */
	public AchievementReportSumVo findSum(CostBean bean)throws Exception;


}
