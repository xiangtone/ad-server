package cn.adwalker.ad.admin.finance.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.CampaignConfirmbean;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmSumVo;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmVo;

/**
 * <p>
 * Title: ICampaignConfirmService
 * </p>
 * <p>
 * Description:活动确认数service接口
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-8
 */
public interface ICampaignConfirmService {

	/**
	 * <p>
	 * Title: findCampaignList
	 * </p>
	 * <p>
	 * Description:活动确认数List
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-8
	 * @return List<CampaignConfirmEditVo>
	 * @version 1.0
	 */

	public List<CampaignConfirmVo> findByPage(CampaignConfirmbean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	* <p>Title: updateStatus</p>
	* <p>Description:发布</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2013-6-8
	* @return void
	* @version 1.0
	 */
	public void updateStatus(Long id)throws Exception;
	/**
	* <p>Title: getEntering</p>
	* <p>Description:查看录入信息</p>
	* @param id
	* @return
	* @author lichuang
	* @date 2013-10-11
	* @return CampaignConfirmEditVo
	* @version 1.0
	 * @throws Exception 
	 */
	public CampaignConfirmVo getEntering(Long id) throws Exception;
	/**
	* <p>Title: findSum</p>
	* <p>Description:求和</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-10-11
	* @return CampaignConfirmSumVo
	* @version 1.0
	 */
	public CampaignConfirmSumVo findSum(CampaignConfirmbean bean);
	/**
	* <p>Title: getInfo</p>
	* <p>Description:发票明细数据查询</p>
	* @param campaign_id
	* @param pageInfo
	* @return
	* @author lichuang
	* @date 2013-10-11
	* @return List<CampaignConfirmEditVo>
	* @version 1.0
	 */
	public List<CampaignConfirmVo> getInfo(Long invoice_id, IPageInfo pageInfo)throws Exception ;
	/**
	 * 
	* <p>Title: findAll</p>
	* <p>Description:结算数据导入全部</p>
	* @param bean
	* @return
	* @author lichuang
	* @date 2013-10-14
	* @return List<CampaignConfirmEditVo>
	* @version 1.0
	 */
	public List<CampaignConfirmVo> findAll(CampaignConfirmbean bean) throws Exception;
	
}
