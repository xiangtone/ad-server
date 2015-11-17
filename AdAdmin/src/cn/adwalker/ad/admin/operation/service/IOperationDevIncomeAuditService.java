package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.DevIncomeAuditbean;
import cn.adwalker.ad.admin.operation.vo.DevIncomeAuditSumVo;
import cn.adwalker.ad.admin.operation.vo.IncomeAuditVo;
import cn.adwalker.core.page.IPageInfo;

/**
* <p>Title: IDevIncomeAuditService</p>
* <p>Description:运营确认收入serice接口 </p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-17
 */
public interface IOperationDevIncomeAuditService {
	
	/**
	* <p>Title: findByPage</p>
	* <p>Description:开发者收入审核List</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-17
	* @return List<IncomeAuditVo>
	* @version 1.0
	 */
	public List<IncomeAuditVo> findList(
			DevIncomeAuditbean bean, IPageInfo pageInfo) throws Exception;
	
	
	/**
	* <p>Title: findAll</p>
	* <p>Description:List报表导入列表</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-17
	* @return List<IncomeAuditVo>
	* @version 1.0
	 */
	public List<IncomeAuditVo> findAll(
			DevIncomeAuditbean bean) throws Exception;
	
	
	/**
	* <p>Title: findSummaryByCondition</p>
	* <p>Description:获取收入总和</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-6-17
	* @return DevIncomeAuditSumVo
	* @version 1.0
	 */
	public DevIncomeAuditSumVo findSummaryByCondition(
			DevIncomeAuditbean bean) throws Exception;
	
	/**
	* <p>Title: tranAudit</p>
	* <p>Description:审核开发者确认收入</p>
	* @param ids
	* @param types
	* @param manageUser
	* @throws Exception
	* @author lichuang
	* @date 2013-6-17
	* @return void
	* @version 1.0
	 */
	public void tranAudit(Long[] ids,SysUserVo manageUser) throws Exception;


	public void tranAudit_no(Long[] ids, SysUserVo manageUser);
	
}
