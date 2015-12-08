/**
* <p>Title: IAdvertiseDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.ad.dao;

import java.util.List;

import cn.adwalker.ad.dao.domain.Ad;
import cn.adwalker.ad.dao.domain.AdCluster;

/**
 * <p>Title: IAdvertiseDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
public interface IAdClusterDao {

	/**
	* <p>Title: getAdvertiseList</p>
	* <p>Description:TODO</p>
	* @return List<Advertise>
	* @throws
	 */
	public List<Ad> getAdClusterList();

	public AdCluster getAdClusterListId(Long id);
	
	
	public Long getClusterIdByAdId(Long ad_id);
	
	
	
}
