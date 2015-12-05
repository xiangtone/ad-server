package cn.adwalker.ad.admin.operation.service;

import java.util.HashMap;
import java.util.List;

import jxl.Sheet;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.IncomeRevenueBean;
import cn.adwalker.ad.admin.operation.form.AdIosEntering;
import cn.adwalker.ad.admin.operation.vo.IncomeConfirmVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.operation.domain.FinanceAdEffectData;

/**
* <p>Title: IOperationAdEffectDataService</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-16
 */
public interface IOperationConfirmIncome {

	/**
	 * 插入
	 * 
	 * @param financeAdEffectData
	 * @return
	 * @throws Exception
	 */
	public int insert(FinanceAdEffectData financeAdEffectData) throws Exception;

	/**
	 * 修改
	 * 
	 * @param financeAdEffectData
	 * @return
	 * @throws Exception
	 */
	public int update(FinanceAdEffectData financeAdEffectData) throws Exception;
	
	/**
	* <p>Title: tranAudit</p>
	* <p>Description:单个审核处理步骤： 1、发布</p>
	* @param ids
	* @param status
	* @param request
	* @throws Exception
	* @author lichuang
	* @date 2013-5-9
	* @return void
	* @version 1.0
	 */
	public void tranAudit(String [] s_date,Long[] ad_id,Integer[] ch_plf_type,SysUserVo curManageUser) throws Exception;

	/**
	* <p>Title: findByPage</p>
	* <p>Description:ios线下数据录入List</p>
	* @param bean
	* @param pageInfor
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-20
	* @return List<IncomeConfirmVo>
	* @version 1.0
	 */
	public List<IncomeConfirmVo> findByPage(IncomeRevenueBean bean,
			IPageInfo pageInfor) throws Exception;
	/**
	* <p>Title: insertAdEffectIosData</p>
	* <p>Description:插入基础数据</p>
	* @param list
	* @param currUser
	* @author lichuang
	* @date 2013-6-1
	* @return void
	* @version 1.0
	 */
	public void insertAdEffectIosData(List<AdIosEntering> list)throws Exception;
	/**
	* <p>Title: checkDataIos</p>
	* <p>Description:验证导入的数据</p>
	* @param sheet
	* @param currUser
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-21
	* @return HashMap<String,Object>
	* @version 1.0
	 */
	public HashMap<String, Object> checkDataIos(Sheet sheet,SysUserVo currUser)throws Exception;
	/**
	* <p>Title: tranAudit</p>
	* <p>Description:ios线下给积分</p>
	* @param ids
	* @param manageUser
	* @author lichuang
	* @date 2013-11-21
	* @return void
	* @version 1.0
	 */
	public void tranAudit(Long[] ids, SysUserVo manageUser)throws Exception;
	/**
	* <p>Title: findAll</p>
	* <p>Description:查询数据</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-11-21
	* @return List<IncomeConfirmVo>
	* @version 1.0
	 */
	public List<IncomeConfirmVo> findAll(IncomeRevenueBean bean)throws Exception;

}
