/**
 * <p>Title: AdDetailCacheImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-4-11
 * @version 1.0
 */
package cn.adwalker.ad.cache.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IDownloadTokenCache;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>
 * Title: AdDetailCacheImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-4-11
 */
@Service
public class DownloadTokenCacheImpl implements IDownloadTokenCache {
	

	@Resource
	private MemCached memCached;



	@Override
	public String getCache(String id) {
		String adv = (String) memCached.get(AppConstant.MEMCACHE_DOWNLOAD_TOKEN + id);
		return adv;
	}

	@Override
	public String addCache(String id,String token) {
		memCached.clear(AppConstant.MEMCACHE_DOWNLOAD_TOKEN + id);
		memCached.add(AppConstant.MEMCACHE_DOWNLOAD_TOKEN + id, token, new Date(AppConstant.CACHE_TIME));
		return null;
	}

	@Override
	public void clear(String id) {
		memCached.clear(AppConstant.MEMCACHE_DOWNLOAD_TOKEN + id);
	}


}
