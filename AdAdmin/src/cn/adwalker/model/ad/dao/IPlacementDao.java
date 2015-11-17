package cn.adwalker.model.ad.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.Placement;

/**
 * 功能概述：<br>
 * 
 * @author jiaozhichao
 */
public interface IPlacementDao extends IBaseDao<Placement> {

	/**
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:更新活动图标
	 * </p>
	 * 
	 * @param id
	 * @param iconimg_url
	 * @author cuidd
	 * @date 2013-4-17
	 * @return void
	 * @version 1.0
	 */

	public void update(Long id, String iconimg_url);

	/**
	 * @return <p>
	 *         Title: getAdvEmailByPlacement
	 *         </p>
	 *         <p>
	 *         Description:TODO
	 *         </p>
	 * @param placement_id
	 * @author cuidd
	 * @date 2013-4-18
	 * @return void
	 * @version 1.0
	 */
	public String getAdvEmailByPlacement(Long placement_id);

	/**
	* <p>Title: Offline</p>
	* <p>Description:TODO</p>
	* @param placement_id
	* @author cuidd
	* @date 2013-7-18
	* @return void
	* @version 1.0
	*/
	
	public void offline(Long placement_id);
	/**
	 * <p>根据投放id获取广告配置id</p>
	 * @author jief 2014-02-21
	 * @param placment_id
	 * @return
	 * 
	 */
	public String getConfigId(Long placment_id);

}
