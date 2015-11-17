/**
* <p>Title: IDevAppCache.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.ad.cache;

import cn.adwalker.ad.cache.element.DevApp;

/**
 * <p>Title: IDevAppCache</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
public interface IDevAppCache {

	
	/***
	 * 获取应用信息
	 * <p>Title: getDevApp</p>
	 * <p>Description:TODO</p>
	 * @param appId
	 * @return DevApp
	 */
	public DevApp getDevApp(Long appId);
	
	/**
	 * 
	* <p>Title: updateApp</p>
	* <p>Description:TODO</p>
	* @param appId
	* @return void
	* @throws
	 */
	public void updateApp(Long appId);
	
	public DevApp getCache(String appKey);
}
