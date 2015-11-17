package cn.adwalker.ad.admin.app.service;

import java.util.List;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.ad.admin.app.bean.Blackbean;
import cn.adwalker.ad.admin.app.bean.SelCampaignQueryBean;
import cn.adwalker.ad.admin.app.vo.AppBlackAdVo;
import cn.adwalker.ad.admin.app.vo.AppSelCampaign;
import cn.adwalker.ad.admin.common.vo.SysUserVo;

/**
 * <p>
 * Title: IConfirmationPackageService
 * </p>
 * <p>
 * Description:媒体添加广告黑名单Service
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-16
 */
public interface IApplicationBlackService {

	/**
	 * <p>
	 * Title: findList
	 * </p>
	 * <p>
	 * Description:媒体添加广告黑名单List
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-17
	 * @return List<AppBlackAdVo>
	 * @version 1.0
	 * @param user 
	 */
	public List<AppBlackAdVo> findList(Blackbean bean, SysUserVo user, IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: appBlackAd
	 * </p>
	 * <p>
	 * Description:添加黑名单
	 * </p>
	 * 
	 * @param applicationId
	 * @param black
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-7-16
	 * @return void
	 * @version 1.0
	 */
	public void updateApplicationBlack(Long applicationId, String black) throws Exception;

	/**
	 * 删除应用黑名单
	 * @param applicationId
	 * @throws Exception
	 */
	public Integer deleteApplicationBlack(Long applicationId) throws Exception;

	public List<AppSelCampaign> findCampaign(SelCampaignQueryBean bean, IPageInfo pageInfo) throws Exception;

	/**
	 * @throws Exception 
	 * 
	* <p>Title: changetStatus</p>
	* <p>Description:修改广告是否屏蔽状态</p>
	* @param id
	* @param status
	* @author cuidd
	* @date 2014年10月11日
	* @return void
	* @version 1.0
	* @throws
	 */
	public void changetStatus(Long id, Integer status) throws Exception;
}
