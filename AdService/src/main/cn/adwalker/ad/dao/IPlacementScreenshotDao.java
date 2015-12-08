/**
* <p>Title: ICampaignScreenshotDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-16
* @version 1.0
*/
package cn.adwalker.ad.dao;

import java.util.List;

import cn.adwalker.ad.dao.domain.PlacementScreenshot;

/**
 * <p>Title: ICampaignScreenshotDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-16
 */
public interface IPlacementScreenshotDao {

	/**
	* <p>Title: findCampaignScreenshot</p>
	* <p>Description:TODO</p>
	* @param campaign_id
	* @return
	* @return List<CampaignScreenshot>
	* @throws
	 */
	public List<PlacementScreenshot> findCampaignScreenshot(Long placement_id);
}
