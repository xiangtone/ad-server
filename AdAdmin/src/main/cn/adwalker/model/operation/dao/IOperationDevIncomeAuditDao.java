package cn.adwalker.model.operation.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.operation.domain.IncomeAudit;

/**
* <p>Title: IOperationDevIncomeAuditDao</p>
* <p>Description:运营开发者收入审核dao接口</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-17
 */
@SuppressWarnings("rawtypes")
public interface IOperationDevIncomeAuditDao extends IBaseDao {

	/**
	* <p>Title: findById</p>
	* <p>Description:TODO</p>
	* @param long1
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-17
	* @return IncomeAudit
	* @version 1.0
	 */
	public IncomeAudit findById(Long long1) throws Exception;

	/**
	 * 批量下载状态处理
	 * 
	 * @param map
	 * @param manageId
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public int batchDealAdStatus(List<Long> ids, Integer status,Long manageId, String manageName) throws Exception;
	
	
	/**
	 * 
	* <p>Title: batchDealAudit</p>
	* <p>Description:收入数据审核</p>
	* @param ids
	* @param status
	* @param manageId
	* @param string
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2013-10-25
	* @return int
	* @version 1.0
	 */
	public int batchDealAudit(List<Long> ids, Integer status,Long manageId, String manageName) throws Exception;

}
