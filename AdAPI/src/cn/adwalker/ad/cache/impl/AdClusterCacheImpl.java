/**
* <p>Title: AdDetailCacheImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-19
* @version 1.0
*/
package cn.adwalker.ad.cache.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.dao.IAdClusterDao;
import cn.adwalker.ad.cache.IAdClusterCache;
import cn.adwalker.ad.cache.element.AdCluster;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>Title: AdDetailCacheImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-19
 */
@Service
public class AdClusterCacheImpl implements IAdClusterCache {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(AdClusterCacheImpl.class);
	
	@Resource
	private MemCached memCached;
	
	@Resource
	private IAdClusterDao adClusterDao;
	/**  
	* <p>Title: getAdDetail</p>
	* <p>Description:TODO</p>
	* @param id
	* @param userInfo
	* @return
	* @see cn.adwalker.ad.cache.IAdDetailCache#getAdDetail(long, cn.adwalker.ad.bean.UserInfo)
	*/
	@Override
	public AdCluster getCacheElement(long id) {
		AdCluster entity = (AdCluster) memCached.get(AppConstant.MEMCACHE_AD_CLUSTER+id);	 
		if(entity == null){
			entity =adClusterDao.getAdClusterByAdId(id);
			memCached.add(AppConstant.MEMCACHE_AD_CLUSTER+id, entity, new Date(AppConstant.CACHE_TIME));
		}
		
		return entity;
	}

	/**  (non-Javadoc)
	* <p>Title: replaceAdDetail</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @see cn.adwalker.ad.cache.IAdDetailCache#replaceAdDetail(long)
	*/
	@Override
	public void replaceCacheElement(long id) {
		memCached.clear(AppConstant.MEMCACHE_AD_CLUSTER+id);
	}
}
