/**
 * 
 */
package cn.adwalker.model.finance.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.DevApplyMoney;

/**
 * 
* <p>Title: IFinanceDevApplyMoneyDao</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-20
 */
@SuppressWarnings("rawtypes")
public interface IFinanceDevApplyMoneyDao extends IBaseDao{

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DevApplyMoney getById(Long id) throws Exception;

	/**
	 * 添加提现记录
	 * 
	 * @param DevApplyMoneyVo
	 * @return
	 * @throws Exception
	 */
	public Integer insert(DevApplyMoney devApplyMoney) throws Exception;
	/**
	 * 修改提现记录
	 * 
	 * @param DevApplyMoneyVo
	 * @return
	 * @throws Exception
	 */
	public Integer update(DevApplyMoney devApplyMoney) throws Exception;
	
	
	/**
	 * 
	* <p>Title: updateStatus</p>
	* <p>Description:更新处理状态</p>
	* @param id
	* @param status
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2012-11-22
	* @return Integer
	* @version 1.0
	* @throws
	 */
	public Integer updateStatus(Long id,Integer status) throws Exception;

	/**
	 * 根据用户ID查询待审核劲歌
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Double getApplyingByAppUserId(Long devUserId) throws Exception;
	
	/**
	 * 统计 
	 * tag:区分运营(0)和财务(1)和财务下载(2)
	 * @return
	 * @throws Exception
	 */
	public List<DevApplyMoney> findReport(String con,int tag)
			throws Exception;

	/**
	 * 财务批量审核
	 * 
	 * @param devApplyMoneyList
	 * @throws Exception
	 */
	public void batchAuditInFinance(List<DevApplyMoney> devApplyMoneyList)
			throws Exception;
	
	
	/**更新手续费和税率*/
	public void batchFinance_TaxAndDues(List<DevApplyMoney> updateList);
	/**
	* <p>Title: findAll</p>
	* <p>Description:财务网站主财务提款sum</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-5-15
	* @return List<FinanceApplyMoneySumVo>
	* @version 1.0
	 */
//	public List<FinanceApplyMoneySumVo> findAll(DevApplyMoneylbean bean);
}