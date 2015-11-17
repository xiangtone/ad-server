package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.finance.bean.PlatformCostByMonthQueryBean;
import cn.adwalker.ad.admin.finance.vo.DevIncomeAuditSumVo;
import cn.adwalker.ad.admin.finance.vo.PlatformCostByMonthVo;

/**
 * <p>
 * Title: IDevIncomeAuditService
 * </p>
 * <p>
 * Description:运营确认收入serice接口         
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang 
 * @date 2013-6-17
 */
public interface IPlatformCostQueryByMonthService {

	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:开发者收入审核List
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-17
	 * @return List<IncomeAuditVo>
	 * @version 1.0
	 */
	public List<PlatformCostByMonthVo> findList(PlatformCostByMonthQueryBean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:List报表导入列表
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-17
	 * @return List<IncomeAuditVo>
	 * @version 1.0
	 */
	public List<PlatformCostByMonthVo> findAll(PlatformCostByMonthQueryBean bean)
			throws Exception;

	/**
	 * <p>
	 * Title: findSummaryByCondition
	 * </p>
	 * <p>
	 * Description:获取收入总和
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-17
	 * @return DevIncomeAuditSumVo
	 * @version 1.0
	 */
	public DevIncomeAuditSumVo findSummaryByCondition(PlatformCostByMonthQueryBean bean)
			throws Exception;
}
