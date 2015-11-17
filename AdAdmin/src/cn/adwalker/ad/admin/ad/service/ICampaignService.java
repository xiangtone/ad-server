package cn.adwalker.ad.admin.ad.service;

import java.util.List;

import cn.adwalker.ad.admin.ad.bean.CampaignOnlineSerach;
import cn.adwalker.ad.admin.ad.bean.CampaignQueryBean;
import cn.adwalker.ad.admin.ad.form.CampaignAddForm;
import cn.adwalker.ad.admin.ad.form.CampaignForm;
import cn.adwalker.ad.admin.ad.form.CampaignPriceForm;
import cn.adwalker.ad.admin.ad.vo.CampaignEditVo;
import cn.adwalker.ad.admin.ad.vo.CampaignInfoVo;
import cn.adwalker.ad.admin.ad.vo.CampaignVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.sales.vo.CampaignOnlineVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.domain.Campaign;
import cn.adwalker.model.ad.domain.CampaignSalesman;

/**
 * 
 * <p>
 * Title: ICampaignService
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-2
 */
public interface ICampaignService {

	/**
	 * @param manageUser
	 *            插入活动
	 *            <p>
	 *            Title: addPubAct
	 *            </p>
	 *            <p>
	 *            Description:TODO
	 *            </p>
	 * @param activityPublishVo
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-3-21
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public Long saveCampaign(CampaignAddForm form, SysUserVo manageUser,boolean isSubmit)
			throws Exception;

	/**
	 * @param note
	 * @param ispass
	 *            活动审核
	 *            <p>
	 *            Title: auditingStatus
	 *            </p>
	 *            <p>
	 *            Description:TODO
	 *            </p>
	 * @param id
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-2
	 * @return void
	 * @version 1.0
	 * @throws
	 */
	public void auditingStatus(Long id, Integer ispass, String note,
			SysUserVo manageUser) throws Exception;

	/**
	 * 活动的查询
	 * <p>
	 * Title: findActivityAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-1
	 * @return List<CampaignVo>
	 * @version 1.0
	 * @throws
	 */
	List<CampaignVo> findByPage(CampaignQueryBean bean, SysUserVo manageUser,
			IPageInfo pageInfo) throws Exception;

	/**
	 * 活动信息查看
	 * <p>
	 * Title: findActiInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param actId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-7
	 * @return CampaignInfoVo
	 * @version 1.0
	 * @throws
	 */
	public CampaignInfoVo getCampaignInfo(Long actId) throws Exception;

	/**
	 * 活动信息查看修改
	 * <p>
	 * Title: updateActivity
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param actId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-8
	 * @return CampaignInfoVo
	 * @version 1.0
	 * @throws
	 */
	public CampaignEditVo getCampaign(Long id) throws Exception;

	/**
	 * @throws Exception
	 * @param category
	 *            <p>
	 *            Title: updateCampaign
	 *            </p>
	 *            <p>
	 *            Description:TODO
	 *            </p>
	 * 
	 * @param form
	 * @author cuidd
	 * @date 2013-4-17
	 * @return void
	 * @version 1.0
	 */
	public void updateCampaign(CampaignForm form, Long[] category,
			Double price_update) throws Exception;

	public void updateCampaign(CampaignForm form) throws Exception;

	/**
	 * <p>
	 * Title: getCampaignInfoByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-6-8
	 * @return CampaignInfoVo
	 * @version 1.0
	 * @throws Exception
	 */

	public CampaignInfoVo getCampaignInfoByPlacement(Long id) throws Exception;

	/**
	 * <p>
	 * Title: getCampaignInfoByPlacement
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @author cuidd
	 * @date 2013-6-8
	 * @return CampaignInfoVo
	 * @version 1.0
	 * @throws Exception
	 */

	public Campaign getCampaignBaseInfoByPlacement(Long id) throws Exception;

	/**
	 * <p>
	 * Title: getSalesmanList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @author cuidd
	 * @date 2014-2-18
	 * @return List<AdvSalesman>
	 * @version 1.0
	 * @param adv_id
	 * @throws Exception
	 */
	public List<CampaignSalesman> getSalesmanList(Long adv_id) throws Exception;

	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @author mandy
	 * @date 2014-3-4
	 * @return List<MediaRatingVo>
	 * @version 1.0
	 * @throws
	 */

	public List<CampaignOnlineVo> findCampaignOnlineByPage(
			CampaignOnlineSerach bean, IPageInfo pageInfor) throws Exception;

	/**
	 * <p>
	 * Title: updateCampaignPrice
	 * </p>
	 * <p>
	 * Description:修改活动单价
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @author cuidd
	 * @date 2014年8月11日
	 * @return void
	 * @version 1.0
	 */
	public void updateCampaignPrice(CampaignPriceForm form, Double price_update,
			SysUserVo sysUser) throws Exception;
}
