package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.DevApplyMoneylbean;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneyDownVo;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneySumVo;
import cn.adwalker.ad.admin.finance.vo.FinanceApplyMoneyVo;

/**
* <p>Title: IFinanceDevApplyMoneyService</p>
* <p>Description:财务网站主提款</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-20
 */
public interface IFinanceDevApplyMoneyService {

	/**
	* <p>Title: getApplyingByDevUserId</p>
	* <p>Description:根据用户获取申请中的提现金额</p>
	* @param devUserId
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-7
	* @return Double
	* @version 1.0
	 */
	public Double getApplyingByDevUserId(Long devUserId) throws Exception;
	/**
	* <p>Title: findReport</p>
	* <p>Description:TODO</p>
	* @param con
	* @param tag
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-7
	* @return List<DevApplyMoney>
	* @version 1.0
	 */
	public List<DevApplyMoney> findReport(String con,int tag)
			throws Exception;
	/**
	* <p>Title: tranAuditInFinance</p>
	* <p>Description:财务批量审核提款</p>
	* @param ids
	* @param status
	* @param financeDescs
	* @param financeMoneys
	* @param manageUser
	* @param pay_types
	* @param finance_taxs
	* @param finance_duess
	* @throws Exception
	* @author lichuang
	* @date 2013-6-7
	* @return void
	* @version 1.0
	 */
	public void tranAuditInFinance(Long[] ids, int status, String[] financeDescs,
			double[] financeMoneys, SysUserVo manageUser, int[] pay_types, double[] finance_taxs, double[] finance_duess)
			throws Exception;


	public void tranAuditInFinance(Long[] checkbox,
			double[] finance_taxsI, double[] finance_duessI) throws Exception;
	/**
	* <p>Title: findAll</p>
	* <p>Description:财务网站主财务提款sum</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-5-15
	* @return List<FinanceApplyMoneySumVo>
	* @version 1.0
	 * @throws Exception 
	 */
	public List<FinanceApplyMoneySumVo> findAll(DevApplyMoneylbean bean) throws Exception;
	/**
	* <p>Title: findDevApplyListPage</p>
	* <p>Description:询财务网站主财务提款ListPage</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-15
	* @return List<FinanceApplyMoneyVo>
	* @version 1.0
	 */
	public List<FinanceApplyMoneyVo> findDevApplyListPage(DevApplyMoneylbean bean,
			IPageInfo pageInfo)throws Exception;
	/**
	* <p>Title: findDevApplyList</p>
	* <p>Description:查询财务网站主财务提款List</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-15
	* @return List<FinanceApplyMoneyDownVo>
	* @version 1.0
	 */
	public List<FinanceApplyMoneyDownVo> findDevApplyList(DevApplyMoneylbean bean)
			throws Exception;
}