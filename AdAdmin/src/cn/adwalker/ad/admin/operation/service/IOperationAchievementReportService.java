package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.operation.bean.AchievementReportbean;
import cn.adwalker.ad.admin.operation.form.AchievementPublishForm;
import cn.adwalker.ad.admin.operation.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.operation.vo.AchievementReportVo;
import cn.adwalker.ad.admin.operation.vo.ChannelDetailVo;
import cn.adwalker.ad.admin.operation.vo.ChannelPlatformSumVo;
import cn.adwalker.ad.admin.operation.vo.PlatformDetailVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.ad.domain.Area;

/**
 * <p>
 * Title: IAchievementReportService
 * </p>
 * <p>
 * Description:业绩报表service接口
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-24
 */
public interface IOperationAchievementReportService {
	/**
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:查询业绩报表List
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @author lichuang
	 * @date 2013-10-24
	 * @return List<CampaignConfirmEditVo>
	 * @version 1.0
	 */
	public List<AchievementReportVo> findByPage(AchievementReportbean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: findSum
	 * </p>
	 * <p>
	 * Description:求和
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-10-24
	 * @return AchievementReportSumVo
	 * @version 1.0
	 */
	public AchievementReportSumVo findSum(AchievementReportbean bean)
			throws Exception;

	/**
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:导出查询报表
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-10-25
	 * @return List<AchievementReportVo>
	 * @version 1.0
	 */
	public List<AchievementReportVo> findAll(AchievementReportbean bean)
			throws Exception;
	/**
	* <p>Title: updateStatus</p>
	* <p>Description:发布业绩</p>
	* @param form
	* @throws Exception
	* @author lichuang
	* @date 2013-10-25
	* @return void
	* @version 1.0
	 */
	public void publish(AchievementPublishForm form)throws Exception;
	/**
	* <p>Title: getChaInfo</p>
	* <p>Description:TODO</p>
	* @param id
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-10-26
	* @return List<CampaignConfirmEditVo>
	* @version 1.0
	 */
	public List<ChannelDetailVo> getChaInfo(Long id, IPageInfo pageInfo)throws Exception;
	/**
	* <p>Title: findChaSum</p>
	* <p>Description:TODO</p>
	* @param bean
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-10-26
	* @return CampaignConfirmSumVo
	* @version 1.0
	 */
	public ChannelPlatformSumVo findChaSum(Long id)throws Exception;
	/**
	* <p>Title: getPlaInfo</p>
	* <p>Description:</p>
	* @param id
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-10-26
	* @return List<PlatformDetailVo>
	* @version 1.0
	 */
	public List<PlatformDetailVo> getPlaInfo(AchievementReportbean bean, IPageInfo pageInfo)throws Exception;
	/**
	* <p>Title: findPlaSum</p>
	* <p>Description:渠道成本求和</p>
	* @param id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-10-26
	* @return ChannelPlatformSumVo
	* @version 1.0
	 */
	public ChannelPlatformSumVo findPlaSum(AchievementReportbean bean)throws Exception;
	/**
	* <p>Title: updatereduceAchievement</p>
	* <p>Description:业绩报表还原</p>
	* @param id
	* @throws Exception
	* @author lichuang
	* @date 2014-2-17
	* @return void
	* @version 1.0
	 */
	public void updatereduceAchievement(Long id)throws Exception;
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

}
