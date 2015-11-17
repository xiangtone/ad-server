/**
 * 
 */
package cn.adwalker.model.app.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.app.domain.DevApplyMoney;

/**
 * 功能概述： <br>
 * 开发者提现接口
 * 
 * @author guoyongxiang
 * 
 */
public interface IDevApplyMoneyDao extends IBaseDao<DevApplyMoney> {
	
	
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
	public long insert(DevApplyMoney devApplyMoney) throws Exception;

	/**
	 * 根据ID删除提现记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void delete(Long id) throws Exception;
	
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
	 * 运用批量审核
	 * 
	 * @param devApplyMoneyList
	 * @throws Exception
	 */
	public void batchAuditInOper(List<DevApplyMoney> devApplyMoneyList)
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

	public List<DevApplyMoney> findAll(String con);

}