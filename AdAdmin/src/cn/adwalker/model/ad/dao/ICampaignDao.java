package cn.adwalker.model.ad.dao;

import java.util.Map;

import cn.adwalker.model.ad.domain.Campaign;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.repository.IBaseDao;

/**
 * 功能概述：<br>
 * 
 * @author jiaozhichao
 */
public interface ICampaignDao extends IBaseDao<Campaign> {
	/**
	 * 
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:更新状态
	 * </p>
	 * 
	 * @param id
	 * @param manageUser
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-4-12
	 * @return int
	 * @version 1.0
	 */
	public int updateStatus(Long id, SysUserVo manageUser, Integer status)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Title: findActiInfo
	 * </p>
	 * <p>
	 * Description:活动信息查看
	 * </p>
	 * 
	 * @param advId
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-4-7
	 * @return Campaign
	 * @version 1.0
	 */
	public Campaign get(Long advId) throws Exception;
	
	/**
	 * <p>
	 * Title: getByName
	 * </p>
	 * <p>
	 * Description:获得活动id
	 * </p>
	 * 
	 * @param campaign_name
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-8
	 * @return Campaign
	 * @version 1.0
	 */
	public Campaign getByName(String campaign_name) throws Exception;
	/**
	* <p>Title: getById</p>
	* <p>Description:获得活动名称</p>
	* @param campaign_id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-9-26
	* @return Campaign
	* @version 1.0
	 */
	public Campaign getById(String campaign_id) throws Exception;
	
	/**
	* <p>Title: findById</p>
	* <p>Description:确认收入的钱</p>
	* @param long1
	* @return
	* @author lichuang
	* @date 2013-9-26
	* @return Double
	* @version 1.0
	 */
	public Long findById(Long id)throws Exception;
	/**
	* <p>Title: getConfirmMode</p>
	* <p>Description:获得线下</p>
	* @param id
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年12月1日
	* @return Campaign
	* @version 1.0
	 */
	public Campaign getConfirmMode(Long id)throws Exception;
}
