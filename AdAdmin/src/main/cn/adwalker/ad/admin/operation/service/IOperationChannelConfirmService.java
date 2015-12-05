package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.CahannelConfirmbean;
import cn.adwalker.ad.admin.operation.bean.CampaignConfirmbean;
import cn.adwalker.ad.admin.operation.form.CampaignConfirmEditForm;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmVo;
import cn.adwalker.ad.admin.operation.vo.ChannelCampaignConfirmSumVo;
import cn.adwalker.ad.admin.operation.vo.ChannelConfirmVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.domain.Area;

/**
 * <p>
 * Title: IOperationCampaignConfirmService
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
public interface IOperationChannelConfirmService {

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

	public List<ChannelConfirmVo> findByPage(CahannelConfirmbean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: updateConfirm
	 * </p>
	 * <p>
	 * Description:修改
	 * </p>
	 * 
	 * @param from
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-10-11
	 * @return void
	 * @version 1.0
	 */
	public void updateConfirm(CampaignConfirmEditForm from, SysUserVo manageUser)
			throws Exception;

	/**
	* <p>Title: findSum</p>
	* <p>Description:求和</p>
	* @param bean
	* @return
	* @author cuidd
	* @date 2014年11月26日
	* @return ChannelCampaignConfirmSumVo
	* @version 1.0
	 */
	public ChannelCampaignConfirmSumVo findSum(CahannelConfirmbean bean);

	/**
	 * <p>
	 * Title: getInfo
	 * </p>
	 * <p>
	 * Description:发票明细数据查询
	 * </p>
	 * 
	 * @param campaign_id
	 * @param pageInfo
	 * @return
	 * @author lichuang
	 * @date 2013-10-11
	 * @return List<CampaignConfirmEditVo>
	 * @version 1.0
	 */
	public List<CampaignConfirmVo> getInfo(Long invoice_id, IPageInfo pageInfo)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:结算数据导入全部
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-10-14
	 * @return List<CampaignConfirmEditVo>
	 * @version 1.0
	 */
	public List<CampaignConfirmVo> findAll(CahannelConfirmbean bean)
			throws Exception;


	public void testPlatformCost(Long balance_id) throws Exception;

	/**
	* <p>Title: testChannelCost</p>
	* <p>Description:TODO</p>
	* @author cuidd
	* @date 2013-12-10
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	public void testChannelCost(Long balance_id,Long media_id) throws Exception;
	/**
	* <p>Title: findByArea</p>
	* <p>Description:区域</p>
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2014-3-14
	* @return List<Area>
	* @version 1.0
	 */
	public List<Area> findByArea()throws Exception;
	/**
	* <p>Title: updateInCome</p>
	* <p>Description:通过结算数据审核</p>
	* @param objList
	* @author cuidd
	* @date 2014年11月15日
	* @return void
	* @version 1.0
	 */
	public int[] updateInCome(List<Object[]> objList,String[] idList)throws Exception;
	/**
	 * <p>Title: updateInCome</p>
	 * <p>Description:通过结算数据审核</p>
	 * @param objList
	 * @author cuidd
	 * @date 2014年11月15日
	 * @return void
	 * @version 1.0
	 */
	public int[] updateInCome(List<Object[]> objList,String[] idList,Object obj)throws Exception;

}
