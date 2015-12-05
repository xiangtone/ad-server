package cn.adwalker.ad.admin.finance.service;


import java.util.List;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.AchievementReportbean;
import cn.adwalker.ad.admin.finance.form.CampaignConfirmFrom;
import cn.adwalker.ad.admin.finance.vo.AchievementReportSumVo;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmVo;
import cn.adwalker.ad.admin.finance.vo.ChannelDetailVo;
import cn.adwalker.ad.admin.finance.vo.ChannelPlatformSumVo;
import cn.adwalker.ad.admin.finance.vo.IncomeDetailVo;
import cn.adwalker.ad.admin.finance.vo.PlatformDetailVo;
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
public interface IAchievementReportService {
	/**
	* <p>Title: findByPage</p>
	* <p>Description:收入明细表</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月13日
	* @return List<IncomeDetailVo>
	* @version 1.0
	 * @param manageUser 
	 */
	public List<IncomeDetailVo> findByPage(AchievementReportbean bean,
			SysUserVo manageUser, IPageInfo pageInfo) throws Exception;

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
	public List<IncomeDetailVo> findAll(AchievementReportbean bean)
			throws Exception;
	
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
	public List<PlatformDetailVo> getPlaInfo(Long confirm_id, IPageInfo pageInfo)throws Exception;
	/**
	* <p>Title: findPlaSum</p>
	* <p>Description:渠道成本求和</p>
	* @param confirm_id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-11-1
	* @return ChannelPlatformSumVo
	* @version 1.0
	 */
	public ChannelPlatformSumVo findPlaSum(Long confirm_id)throws Exception;
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
	* <p>Title: submitIncomeNum</p>
	* <p>Description:插入汇总数据</p>
	* @param ids
	* @throws Exception
	* @author cuidd
	* @date 2014年11月14日
	* @return void
	* @version 1.0
	 */
	public Long submitIncomeNum(String ids,SysUserVo manageUser)throws Exception;
	/**
	* <p>Title: getById</p>
	* <p>Description:查询明细</p>
	* @param income_id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年11月14日
	* @return IncomeMonthCampaign
	* @version 1.0
	 */
	public CampaignConfirmVo getById(Long income_id)throws Exception;
	/**
	* <p>Title: updateStatus</p>
	* <p>Description:TODO</p>
	* @param id
	* @throws Exception
	* @author cuidd
	* @date 2014年11月15日
	* @return void
	* @version 1.0
	 */
	public void updateStatus(CampaignConfirmFrom from)throws Exception;
	/**
	* <p>Title: deleteStatus</p>
	* <p>Description:取消申请</p>
	* @param id
	* @throws Exception
	* @author cuidd
	* @date 2014年12月10日
	* @return void
	* @version 1.0
	 */
	public void deleteStatus(Long id)throws Exception;

}
