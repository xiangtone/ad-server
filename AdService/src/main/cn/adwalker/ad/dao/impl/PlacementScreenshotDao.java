/**
* <p>Title: CampaignScreenshotDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-16
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IPlacementScreenshotDao;
import cn.adwalker.ad.dao.domain.PlacementScreenshot;

/**
 * <p>Title: CampaignScreenshotDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-16
 */
@Repository("placementScreenshotDao")
public class PlacementScreenshotDao extends CommonDao implements IPlacementScreenshotDao {

	
	/** 
	* <p>Title: findCampaignScreenshot</p>
	* <p>Description:TODO</p>
	* @param campaign_id
	* @return
	* @see cn.adwalker.ad.dao.IPlacementScreenshotDao#findCampaignScreenshot(java.lang.Long)
	*/
	@Override
	public List<PlacementScreenshot> findCampaignScreenshot(Long placement_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_PLACEMENT_SCREENSHOT t where  del = 0  and PLACEMENT_ID = ? ");
		return simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<PlacementScreenshot>(PlacementScreenshot.class),new Object[]{placement_id});
	}

}
