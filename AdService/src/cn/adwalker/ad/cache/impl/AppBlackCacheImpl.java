/**
 * <p>Title: AppBlackCacheImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-6-14
 * @version 1.0
 */
package cn.adwalker.ad.cache.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.bean.AppBlack;
import cn.adwalker.ad.cache.IAppBlackCache;
import cn.adwalker.ad.dao.IAppBlackDao;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>
 * Title: AppBlackCacheImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-6-14
 */
@Service("appBlackCache")
public class AppBlackCacheImpl implements IAppBlackCache {

	@Resource
	private IAppBlackDao appBlackDao;
	@Resource
	private MemCached memCached;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAppBlack
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @return
	 * @see cn.adwalker.ad.cache.IAppBlackCache#getAppBlack(long)
	 */
	@Override
	public AppBlack getAppBlack(long appId) {
		AppBlack appBlack = (AppBlack) memCached.get(AppConstant.MEMCACHE_APP_BLACK + appId);
		if (appBlack == null) {
			appBlack = appBlackDao.getAppBlack(appId);
			if (appBlack == null) {
				appBlack = new AppBlack();
				appBlack.setApp_id(appId);
			}
			memCached.add(AppConstant.MEMCACHE_APP_BLACK + appId, appBlack);
		}
		return appBlack;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: replaceAppBlack
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @see cn.adwalker.ad.cache.IAppBlackCache#replaceAppBlack(long)
	 */
	@Override
	public void replaceAppBlack(long appId) {
		memCached.clear(AppConstant.MEMCACHE_APP_BLACK + appId);

	}

}
