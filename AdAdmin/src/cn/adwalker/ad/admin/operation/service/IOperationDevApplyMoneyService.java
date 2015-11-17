package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.DevApplyMoneySearchBean;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.domain.DevApplyMoney;

/**
* <p>Title: IOperationDevApplyMoneyService</p>
* <p>Description:运营提款审核Service</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-21
 */
public interface IOperationDevApplyMoneyService {

	/**
	 * 根据ID查询提现记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DevApplyMoney getById(Long id) throws Exception;


	/**
	 * 修改提现记录
	 * 
	 * @param DevApplyMoney
	 * @throws Exception
	 */
	public void update(DevApplyMoney DevApplyMoney) throws Exception;
	
	/**
	 * 统计 
	 * tag:区分运营(0)和财务(1)和财务下载(2)
	 * @return
	 * @throws Exception
	 */
	public DevApplyMoney findReportSum(DevApplyMoneySearchBean bean,int tag)
			throws Exception;

	/**
	 * 运用批量审核
	 * 
	 * @param devApplyMoneyList
	 * @throws Exception
	 */
	public void tranAuditInOper(Long[] ids, int status, String[] managerDescs,
			double[] managerMoneys, SysUserVo manageUser)
			throws Exception;


	public Integer findOperator(String operator);

	/**
	* <p>Title: findAuditList</p>
	* <p>Description:运营提款List</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-5-21
	* @return List<DevApplyMoney>
	* @version 1.0
	 */
	public List<DevApplyMoney> findAuditList(DevApplyMoneySearchBean bean,
			IPageInfo pageInfo)throws Exception;


}