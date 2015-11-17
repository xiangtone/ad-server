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

import cn.adwalker.ad.cache.IMediaUserCache;
import cn.adwalker.ad.dao.IMediaUserDao;
import cn.adwalker.ad.dao.domain.MediaUser;
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
@Service
public class MediaUserCacheImpl implements IMediaUserCache {

	@Resource
	private IMediaUserDao mediaUserDao;
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
	public MediaUser getMediaUser(String media_user_id,Long appid) {
		MediaUser mediaUser = (MediaUser) memCached
				.get(AppConstant.MEMCACHE_MEDIA_USER + media_user_id+"_"+appid);
		if (mediaUser == null) {
			mediaUser = mediaUserDao.findMediaUser(media_user_id,appid);
			if (mediaUser != null) {
				memCached.add(AppConstant.MEMCACHE_MEDIA_USER + media_user_id,
						mediaUser);
			}

		}
		return mediaUser;
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
	public void replaceMediaUser(String media_user_id) {
		memCached.clear(AppConstant.MEMCACHE_MEDIA_USER + media_user_id);

	}

	@Override
	public MediaUser getMediaUser(String media_user_id, String os) {
		MediaUser mediaUser = (MediaUser) memCached
				.get(AppConstant.MEMCACHE_MEDIA_USER + media_user_id+"_"+os);
		if (mediaUser == null) {
			mediaUser = mediaUserDao.findMediaUserByOs(media_user_id,os);
			if (mediaUser != null) {
				memCached.add(AppConstant.MEMCACHE_MEDIA_USER + media_user_id,
						mediaUser);
			}

		}
		return mediaUser;
	}

}
