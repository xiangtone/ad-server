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

import java.util.List;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.dao.domain.Ad;
import cn.adwalker.ad.dao.domain.AdCluster;

/**
 * <p>Title: IAdDetailCache</p>
 * <p>Description:广告详情页服务</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-19
 */
public interface IAdClusterCache {

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
	public AdCluster getCacheElement(long id);
	
	/**
	 * 
	* <p>Title: getClusterAdList</p>
	* <p>Description:获取集群接入的广告</p>
	* @return
	* @author cuidd
	* @date 2014年10月17日
	* @return List<Advertise>
	* @version 1.0
	 */
	public List<Advertise> getClusterAdList();
	
	
	public Advertise getClusterAd(Ad ad);
	
	
	
	
	public void replaceCacheElement(long ad_id);
}
