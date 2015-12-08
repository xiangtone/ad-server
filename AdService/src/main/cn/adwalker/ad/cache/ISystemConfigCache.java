/**
* <p>Title: SystemConfigCache.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-6-9
* @version 1.0
*/
package cn.adwalker.ad.cache;

import cn.adwalker.ad.cache.element.ServiceConfig;

/**
 * <p>Title: SystemConfigCache</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-6-9
 */
public interface ISystemConfigCache {

	/**
	* <p>Title: getSystemConfig</p>
	* <p>Description:TODO</p>
	* @return
	* @return SystemConfig
	* @throws
	 */
	public ServiceConfig getSystemConfig();
	
	public void replaceSystemConfig();	
	
}
