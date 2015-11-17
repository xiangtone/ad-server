/**
* <p>Title: MemcacheChannelServiceImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-3-6
* @version 1.0
*/
package cn.adwalker.IOSChannel.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.dao.IChannelDao;
import cn.adwalker.IOSChannel.picker.xmemcached.ConfigCache;
import cn.adwalker.IOSChannel.service.IMemcacheChannelService;
import cn.adwalker.IOSChannel.vo.AdvertisementChannel;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.ad.bean.CacheElement;
import cn.adwalker.ad.pool.MemCached;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;

/**
 * <p>Title: MemcacheChannelServiceImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-3-6
 */
@Service("memcacheChannelService")
public class MemcacheChannelServiceImpl implements IMemcacheChannelService{

	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil
			.getString("cache_time"));
	
	private Map<String,CacheElement<Advertisement_IOS>> cache=new HashMap<String, CacheElement<Advertisement_IOS>>();
	
	@Resource
	private IChannelDao channelDao;
	@Resource
	private MemCached memCached;
	
	
	@Override
	public AdvertisementChannel getChannel(String channel) {
		AdvertisementChannel channelBean = (AdvertisementChannel) memCached.get(AppConstant.MEM_API_CHANNEL+ channel);
		if(channelBean==null){
		  channelBean = channelDao.getChannel(channel);
		  memCached.add(AppConstant.MEM_API_CHANNEL+ channel, channelBean, new Date(CACHE_TIME));
		}
		return channelBean;
	}

	
	@Override
	public Advertisement_IOS getIOSByKey(String adkey) {
		Advertisement_IOS ios = (Advertisement_IOS)memCached.get(AppConstant.MEM_API_IOS_KEY+ adkey);
		if(ios==null){
			ios = channelDao.getIOS(adkey);
			memCached.add(AppConstant.MEM_API_IOS_KEY+ adkey, ios, new Date(CACHE_TIME));
		}
		return ios;
	}

	
	@Override
	public Advertisement_IOS getIOSById(String id) {
		Advertisement_IOS ios=null;
		if (!StringUtils.isEmpty(id)) {
			try {
				CacheElement<Advertisement_IOS> cacheElement=cache.get(AppConstant.MEM_API_IOS_ID+ id);
				if (cacheElement==null||cacheElement.getElement()==null) {
					ios = channelDao.getIOS(id);
					cacheElement=new CacheElement<Advertisement_IOS>(60);
					cacheElement.setElement(ios);
					cache.put(AppConstant.MEM_API_IOS_ID+ id,cacheElement);
				}
			} catch (Exception e) {
				System.out.println("系统有错");
			}
		}
		if (ios==null) {
			ios=this.get(id);
		}
		
		
//		Advertisement_IOS ios = (Advertisement_IOS)memCached.get(AppConstant.MEM_API_IOS_ID+ id);
//		if(ios==null){
//			ios = channelDao.getIOS(id);
//			memCached.add(AppConstant.MEM_API_IOS_ID+ id, ios, new Date(CACHE_TIME));
//		}
		return ios;
	}
	
	
	private Advertisement_IOS get(String id){
		Advertisement_IOS ios = (Advertisement_IOS)memCached.get(AppConstant.MEM_API_IOS_ID+ id);
		if(ios==null){
			ios = channelDao.getIOS(id);
			memCached.add(AppConstant.MEM_API_IOS_ID+ id, ios, new Date(CACHE_TIME));
		}
		return ios;
	}


	/**  (non-Javadoc)
	* <p>Title: updateCache</p>
	* <p>Description:TODO</p>
	* @see cn.adwalker.IOSChannel.service.IMemcacheChannelService#updateCache()
	*/
	@Override
	public void updateCache() {
		List<AdvertisementChannel> channelList = channelDao.getChannelList();
		List<Advertisement_IOS> iosList = channelDao.getISOList();

		for (AdvertisementChannel channel : channelList) {
			Object obj = memCached.get(AppConstant.MEM_API_CHANNEL
					+ channel.getChannel());
			if (obj == null) {
				memCached.add(AppConstant.MEM_API_CHANNEL + channel.getChannel(),
						channel, new Date(CACHE_TIME));
			} else {
				memCached.replace(
						AppConstant.MEM_API_CHANNEL + channel.getChannel(), channel,
						new Date(CACHE_TIME));
			}
		}

		
		
		for (Advertisement_IOS ios : iosList) {

			Object objId = memCached.get(AppConstant.MEM_API_IOS_ID + ios.getId());
			if (objId == null) {
				memCached.add(AppConstant.MEM_API_IOS_ID + ios.getId(), ios,
						new Date(CACHE_TIME));
			} else {
				memCached.replace(AppConstant.MEM_API_IOS_ID + ios.getId(), ios,
						new Date(CACHE_TIME));
			}

			Object obj = memCached
					.get(AppConstant.MEM_API_IOS_ID + ios.getAd_key());
			if (obj == null) {
				memCached.add(AppConstant.MEM_API_IOS_ID + ios.getAd_key(), ios,
						new Date(CACHE_TIME));
			} else {
				memCached.replace(AppConstant.MEM_API_IOS_ID + ios.getAd_key(),
						ios, new Date(CACHE_TIME));
			}
		}
		
		
		memCached.clear(AppConstant.MEM_API_URLS);
	}


	@Override
	public void deleteSchemeCache(String schemeId) {
		List<Long> la=channelDao.getAdsSchemeBySchemeId(schemeId);
		if(la!=null && la.size()>0){
			for(Long id:la){
				memCached.clear(ConfigCache.AD_CONFIG_KEY+id);
			}
		}
		List<Long> lm=channelDao.getmiediasSchemeBySchemeId(schemeId);
		if(lm!=null && lm.size()>0){
			for(Long id:lm){
				memCached.clear(ConfigCache.MEDIA_CONFIG_KEY+id);
			}
		}
		memCached.clear(ConfigCache.AREA_DEF_SCHEME+"0");
		memCached.clear(ConfigCache.AREA_DEF_SCHEME+"1");
	}

}
