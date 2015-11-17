package cn.adwalker.ad.admin.finance.service;

import java.io.IOException;
import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.ChannelCostQueryBean;
import cn.adwalker.ad.admin.finance.form.ChannelConfirmFrom;
import cn.adwalker.ad.admin.finance.vo.ChannelConfirmVo;
import cn.adwalker.ad.admin.finance.vo.ChannelCostSumVo;
import cn.adwalker.ad.admin.finance.vo.ChannelCostVo;

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
public interface IChannelCostQueryService {

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
	public List<ChannelCostVo> findList(ChannelCostQueryBean bean,
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
	public List<ChannelCostVo> findAll(ChannelCostQueryBean bean)
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
	public ChannelCostSumVo findSumByCondition(ChannelCostQueryBean bean)
			throws Exception;
	/**
	* <p>Title: submitIncomeNum</p>
	* <p>Description:渠道成本明细</p>
	* @param ids
	* @param manageUser
	* @return
	* @throws IOException
	* @author cuidd
	* @date 2014年11月17日
	* @return Long
	* @version 1.0
	 */
	public Long submitIncomeNum(String ids, SysUserVo manageUser)throws Exception ;
	/**
	* <p>Title: getById</p>
	* <p>Description:渠道成本结算明细</p>
	* @param income_id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return ChannelConfirmVo
	* @version 1.0
	 */
	public ChannelConfirmVo getById(Long income_id)throws Exception ;
	
	/**
	* <p>Title: updateStatus</p>
	* <p>Description:提交渠道成本结算明细</p>
	* @param from
	* @throws Exception
	* @author cuidd
	* @date 2014年11月17日
	* @return void
	* @version 1.0
	 */
	public void updateStatus(ChannelConfirmFrom from)throws Exception ;
	/**
	* <p>Title: deleteStatus</p>
	* <p>Description:取消渠道申请</p>
	* @param id
	* @throws Exception
	* @author cuidd
	* @date 2014年12月10日
	* @return void
	* @version 1.0
	 */
	public void deleteStatus(Long id)throws Exception ;
}
