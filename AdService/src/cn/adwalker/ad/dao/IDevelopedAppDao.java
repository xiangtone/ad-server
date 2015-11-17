package cn.adwalker.ad.dao;

import java.util.List;
import java.util.Map;

import cn.adwalker.ad.cache.element.DevApp;

/**
 * <p>Title: IDevelopedAppDao</p>
 * <p>Description:</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-4
 */
public interface IDevelopedAppDao {
	
	
	public DevApp findByAppKey(String key);
	
	/**
	 * 
	* <p>Title: appList</p>
	* <p>Description:TODO</p>
	* @return
	* @return List<DevApp>
	* @throws
	 */
	public List<DevApp> appList();
	
	/**
	 * 
	* <p>Title: getApplication</p>
	* <p>Description:TODO</p>
	* @param appId
	* @return
	* @return DevApp
	* @throws
	 */
	public DevApp getApplication(Long appId);

	/**
	* <p>Title: appPlacementList</p>
	* <p>Description:TODO</p>
	* @return
	* @return List<DevApp>
	* @throws
	*/
	public List<Map<String, Object>> appPlacementList();
}
