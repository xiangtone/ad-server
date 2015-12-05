/**
 * 
 */
package cn.adwalker.model.ad.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.ad.domain.PlacementScreenshot;


/**
 * 
* <p>Title: IPlacementScreenshotDao</p>
* <p>Description:广告屏幕截图</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2013-8-26
 */
public interface IPlacementScreenshotDao extends IBaseDao<PlacementScreenshot> {
	
	/**
	 * 添加广告截图
	 * 
	 * @param appScreenshot
	 * @return
	 * @throws Exception
	 */
	public Integer saveOrUpdate(Long placement_id,String[] strings) throws Exception;
	
	
	public List<PlacementScreenshot> getByPlacement(Long placement_id) throws Exception;
}
