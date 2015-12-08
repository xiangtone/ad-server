/**
 * <p>Title: MemcacheServiceImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-1-16
 * @version 1.0
 */
package cn.adwalker.ad.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IAdClusterCache;
import cn.adwalker.ad.cache.IAdDetailCache;
import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.IAppBlackCache;
import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.ISystemConfigCache;
import cn.adwalker.ad.cache.ITypeCache;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.dao.IDevelopedAppDao;
import cn.adwalker.ad.dao.IPageDao;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.service.IMemcacheService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;

/**
 * <p>
 * Title: MemcacheServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-1-16
 */
@Service("memcacheService")
public class MemcacheServiceImpl implements IMemcacheService {
	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil
			.getString("cache_time"));
	
	private static final Logger log = LoggerFactory
			.getLogger(MemcacheServiceImpl.class);
	@Resource
	private MemCached memCached;
	@Resource
	private IPageDao pageDao;
	@Resource
	private IDevelopedAppDao developedAppDao;
	@Resource
	private IDevAppCache devAppCache;
	
	@Resource
	private ITypeCache typeCache;
	@Resource
	private IAdCache advCache;
	@Resource
	private IAdDetailCache adDetailCache;
	@Resource
	private ISystemConfigCache systemConfigCache;	
	@Resource
	private IAppBlackCache appBlackCache;	
	
	@Resource
	private IAdClusterCache adClusterCache;
	/**
	 * <p>
	 * Title: updateCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adIds
	 * @see cn.adwalker.ad.service.IMemcacheService#updateCache(java.lang.String)
	 */
	@Override
	public void updateCache(String adId) {
		long id = Long.parseLong(adId);
		advCache.replaceAdv(id);
		typeCache.replaceType(id, AppConstant.OS_ANDROID);
		typeCache.replaceType(id, AppConstant.OS_IOS);
		adDetailCache.replaceAdDetail(id);
		adClusterCache.replaceCacheElement(id);
	}

	/**
	 * <p>
	 * Title: updateDevCurrencyCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @see cn.adwalker.ad.service.IMemcacheService#updateDevCurrencyCache(java.lang.String)
	 */
	@Override
	public void updateDevCurrencyCache(String appId) {

		log.info("appId" + appId);
		devAppCache.updateApp(Long.parseLong(appId));
	
	}

	/**
	 * <p>
	 * Title: updateDevCurrencyCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @see cn.adwalker.ad.service.IMemcacheService#updateDevCurrencyCache()
	 */
	@Override
	public void updateConfigFinance() {	
		systemConfigCache.replaceSystemConfig();	
		List<DevApp> apps = developedAppDao.appList();
		for (DevApp app : apps) {
			devAppCache.updateApp(app.getId());
		}

	}

	


	/**
	 * <p>
	 * Title: clearUserCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * @param uuid
	 * @see cn.adwalker.ad.service.IMemcacheService#clearUserCache(java.lang.String)
	 */
	@Override
	public void clearUserCache(String uuid) {
		if (!StringUtils.isEmpty(uuid)) {
			memCached.clear(uuid);
		}
	}

	/**
	 * <p>
	 * Title: updateChannelCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * @see cn.adwalker.ad.service.IMemcacheService#updateChannelCache()
	 */
	@Override
	public void updateChannelCache() {
/**
		List<AdvertisementChannel> channelList = channelDao.getChannelList();
		List<Advertisement_IOS> iosList = channelDao.getISOList();

		for (AdvertisementChannel channel : channelList) {
			Object obj = memCached.get(AppConstant.AD_CHANNEL
					+ channel.getChannel());
			if (obj == null) {
				memCached.add(AppConstant.AD_CHANNEL + channel.getChannel(),
						channel, new Date(CACHE_TIME));
			} else {
				memCached.replace(
						AppConstant.AD_CHANNEL + channel.getChannel(), channel,
						new Date(CACHE_TIME));
			}
		}

		for (Advertisement_IOS ios : iosList) {

			Object objId = memCached.get(AppConstant.AD_IOS_ID + ios.getId());
			if (objId == null) {
				memCached.add(AppConstant.AD_IOS_ID + ios.getId(), ios,
						new Date(CACHE_TIME));
			} else {
				memCached.replace(AppConstant.AD_IOS_ID + ios.getId(), ios,
						new Date(CACHE_TIME));
			}

			Object obj = memCached
					.get(AppConstant.AD_IOS_KEY + ios.getAd_key());
			if (obj == null) {
				memCached.add(AppConstant.AD_IOS_KEY + ios.getAd_key(), ios,
						new Date(CACHE_TIME));
			} else {
				memCached.replace(AppConstant.AD_IOS_KEY + ios.getAd_key(),
						ios, new Date(CACHE_TIME));
			}
		}
		*/
	}


	
	

	

	/**  
	* <p>Title: updateSignCache</p>
	* <p>Description:TODO</p>
	* @see cn.adwalker.ad.service.IMemcacheService#updateSignCache()
	*/
	@Override
	public void updateSignCache() {
		systemConfigCache.replaceSystemConfig();
	}

	/**  (non-Javadoc)
	* <p>Title: updateBlackCache</p>
	* <p>Description:TODO</p>
	* @see cn.adwalker.ad.service.IMemcacheService#updateBlackCache()
	*/
	@Override
	public void updateBlackCache(long appId) {
		appBlackCache.replaceAppBlack(appId);
		
	}

	/**  (non-Javadoc)
	* <p>Title: updateSspCache</p>
	* <p>Description:TODO</p>
	* @param id
	* @see cn.adwalker.ad.service.IMemcacheService#updateSspCache(long)
	*/
	@Override
	public void updateSspCache(long id) {
		//adSspCache.replaceAdSsp(id);
		
	}
}
