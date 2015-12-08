/**
* <p>Title: IAdDetailCache.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-19
* @version 1.0
*/
package cn.adwalker.ad.cache;

import cn.adwalker.ad.cache.element.AdDetailElement;
import cn.adwalker.ad.cache.element.UserInfo;

/**
 * <p>Title: IAdDetailCache</p>
 * <p>Description:广告详情页服务</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-19
 */
public interface IAdDetailCache {

	/**
	 * 
	* <p>Title: getAdDetail</p>
	* <p>Description:广告详情页服务</p>
	* @param id
	* @param userInfo
	* @param version
	* @param os
	* @return
	* @author cuidd
	* @date 2014年10月15日
	* @return AdDetailJson
	* @version 1.0
	 */
	public AdDetailElement getAdDetail(long id,UserInfo userInfo,String version,String os,String type);
	
	public void replaceAdDetail(long id);
}
