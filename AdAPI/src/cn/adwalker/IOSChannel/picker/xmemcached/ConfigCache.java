package cn.adwalker.IOSChannel.picker.xmemcached;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.bean.ParamConfig;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.SpringUtil;
import cn.adwalker.IOSChannel.strategy.AreaScheme;
import cn.adwalker.IOSChannel.strategy.admaster.AdSendConfig;
import cn.adwalker.IOSChannel.strategy.media.MediaCallbackConfig;
import cn.adwalker.ad.util.ConfigUtil;

public class ConfigCache {
	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil.getString("cache_time"));
	public final static String CAMPAIGN_CONFIG_KEY="campaignConfigne_n_1";
	public final static String CHANNEL_CONFIG_KEY="channelConfigene_n";
	//媒体防作弊配置缓存
	public final static String MEDIA_CONFIG_KEY="mediaCallbackConfig_";
	public final static String AD_CONFIG_KEY="ADSendConfig_";
	public final static String ALL_IOS_AD_NUM="alliosadnum";
	public final static String AREA_DEF_SCHEME="area_def_scheme_";
	
	public static CampaignConfig findCampaignCongfig(String key){
		CampaignConfig c = MemcacheCached.getInstance().get(CAMPAIGN_CONFIG_KEY+key, CampaignConfig.class);
		if(c==null){
			c = queryCampaignConfig(key);
			if(c!=null){
				MemcacheCached.getInstance().add(CAMPAIGN_CONFIG_KEY+key, c, new Date(CACHE_TIME));
				//XMemcachedUtil.getInstance().add(CAMPAIGN_CONFIG_KEY+key, CACHE_TIME, c);
			    return c;
			}
		}
		return c;
	}
	
	private static CampaignConfig queryCampaignConfig(String key){
		SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
		CampaignConfig c = supporDao.findCampaignConfig(key);
		if(c!=null){
		  List<ParamConfig> list = supporDao.queryParamConfig(BaseAttribute.AD_PARTHER, key);
		  Map<String, ParamConfig> map = new HashMap<String, ParamConfig>();
		  for(ParamConfig pc:list){
			  map.put(pc.getParam_name(), pc);
		  }
		  c.map=map;
		}
		return c;
	}
	
	public static ChannelConfig findChannelConfig(String channel){
		ChannelConfig c = MemcacheCached.getInstance().get(CHANNEL_CONFIG_KEY+channel, ChannelConfig.class);
		if(c==null){
			//SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
			c = queryChannelConfig(channel);
			if(c!=null){
				MemcacheCached.getInstance().add(CAMPAIGN_CONFIG_KEY+channel, c, new Date(CACHE_TIME));
				//XMemcachedUtil.getInstance().add(CHANNEL_CONFIG_KEY+channel, CACHE_TIME, c);
				return c;
			}
		}
		return c;
	}
	public static ChannelConfig queryChannelConfig(String channel){
		SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
		ChannelConfig c = supporDao.findChannelConfig(channel);
		if(c!=null){
			 List<ParamConfig> list = supporDao.queryParamConfig(BaseAttribute.CHANNEL_PARTHER, channel);
			 Map<String, ParamConfig> map = new HashMap<String, ParamConfig>();
			 for(ParamConfig pc:list){
				 map.put(pc.getParam_name(), pc);
			 }
			  c.setMap(map);
			  
		}
		return c;
	}
	/**
	 * 从缓存中获取媒体防作弊协议
	 * @param appid
	 * @return
	 */
	public static MediaCallbackConfig getMediaCallbackConfig(String appid){
		MediaCallbackConfig m=MemcacheCached.getInstance().get(MEDIA_CONFIG_KEY+appid, MediaCallbackConfig.class);
		if(null==m){
			SupportDao supportDao = SpringUtil.getBean("supportDao",SupportDao.class);
			m=supportDao.getMediaConfigById(appid);
			if(null!=m){
				MemcacheCached.getInstance().add(MEDIA_CONFIG_KEY+appid, m, new Date(CACHE_TIME));
			}
		}
		return m;
	}
	/**
	 * 从缓存中获取广告防作弊协议
	 * @param adid
	 * @return
	 */
	public static AdSendConfig getAdSendConfig(String adid){
		AdSendConfig a=MemcacheCached.getInstance().get(AD_CONFIG_KEY+adid, AdSendConfig.class);
		if(null==a){
			SupportDao supportDao = SpringUtil.getBean("supportDao",SupportDao.class);
			a=supportDao.getAdSendConfigById(adid);
			if(a!=null){
				MemcacheCached.getInstance().add(AD_CONFIG_KEY+adid, a, new Date(CACHE_TIME));
			}
		}
		return a;
	}
	/**
	 * 获取默认的媒体或广告端防作弊协议
	 * @param schemetype
	 */
	public static AreaScheme getDefaultAreaScheme(String schemetype){
		AreaScheme a=MemcacheCached.getInstance().get(AREA_DEF_SCHEME+schemetype, AreaScheme.class);
		if(null==a){
			SupportDao supportDao = SpringUtil.getBean("supportDao",SupportDao.class);
			a=supportDao.getAreaSchemeByType(schemetype);
			if(a!=null){
				MemcacheCached.getInstance().add(AREA_DEF_SCHEME+schemetype, a, new Date(CACHE_TIME));
			}
		}
		return a;
	}
	/**
	 * 从缓存中获取当前在线广告数目
	 * @param adid
	 * @return
	 */
	public static int getiosAdNum(){
		Integer n=(Integer)MemcacheCached.getInstance().get(ALL_IOS_AD_NUM);
		if(null==n){
			SupportDao supportDao = SpringUtil.getBean("supportDao",SupportDao.class);
			n=supportDao.getiosAdNum();
			if(n!=null){
				MemcacheCached.getInstance().add(ALL_IOS_AD_NUM, n, new Date(CACHE_TIME));
			}
		}
		return n;
	}
	
	
}
