/**
* <p>Title: IAdvertiseDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.IOSChannel.dao;

import cn.adwalker.ad.cache.element.AdCluster;

/**
 * <p>Title: IAdvertiseDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
public interface IAdClusterDao {

	public AdCluster getAdClusterByAdId(Long id);
	
	
	
}
