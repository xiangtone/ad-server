package cn.adwalker.model.ad.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.CampaignPlacementRel;

/**
 * 功能概述：<br>
 * 
 * @author jiaozhichao
 */
public interface ICampaignPlacementRelDao extends IBaseDao<CampaignPlacementRel> {
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
	public int updateStatus(Long id, Integer status)
			throws Exception;


	/**
	* <p>Title: getByCampaignId</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @author cuidd
	* @date 2013-5-15
	* @return CampaignPlacementRel
	* @version 1.0
	 * @throws Exception 
	*/
	public CampaignPlacementRel getByCampaignId(Long id) throws Exception;
	
	/**
	* <p>Title: getByCampaignId</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @author cuidd
	* @date 2013-5-15
	* @return CampaignPlacementRel
	* @version 1.0
	 * @throws Exception 
	*/
	public CampaignPlacementRel getByPlacementId(Long id) throws Exception;

	/**
	* <p>Title: updateStatusByPlacement</p>
	* <p>Description:更新投放状态</p>
	* @param id
	* @param i
	* @author cuidd
	* @date 2013-5-16
	* @return void
	* @version 1.0
	*/
	
	public void updateStatusByPlacement(Long id, Integer i);

	/**
	* <p>Title: batchOffLine</p>
	* <p>Description:TODO</p>
	* @param listForUpdate
	* @author cuidd
	* @date 2013-6-25
	* @return void
	* @version 1.0
	*/
	
	public void batchUpdate(List<CampaignPlacementRel> listForUpdate);
}
