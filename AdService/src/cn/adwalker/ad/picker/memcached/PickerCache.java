package cn.adwalker.ad.picker.memcached;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.adwalker.ad.beans.PortalAdInfo;
import cn.adwalker.ad.dao.impl.PortalDao;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.StringUtil;
import cn.adwalker.core.spring.AppContext;

public class PickerCache {
	private static final Logger logger = Logger.getLogger(PickerCache.class);
	private static String PORTAL_AD_KEY="portalad_";
	private static String blackIpKey="backIpMap";
	private static boolean isCache=false;

	
	public static PortalAdInfo findPortalAdInfo(Long adId){
		PortalAdInfo pad = MemcachedUtil.getInstance().get(PORTAL_AD_KEY+adId, PortalAdInfo.class);
		if(pad==null){
		
		}
		return pad;
	}
	
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> blackIpMap(){
		 Map<String,String> map = MemcachedUtil.getInstance().get(blackIpKey,HashMap.class);
		 if(map==null){
			 if(!isCache){
				 isCache=true;
				 return createblackIpMap();
			 }
		 }
		return map==null?new HashMap<String, String>():map;
	}
	public static Map<String,String> createblackIpMap(){
	     MemcachedUtil.getInstance().delete(blackIpKey);
		 Map<String,String> map = new HashMap<String, String>();
		 PortalDao portalDao = AppContext.getApplicationContext().getBean("portalDao", PortalDao.class);
		 List<String> l = portalDao.queryBlackIpList();
		 for(String ip:l){
			 String ipduan = StringUtil.ipdo(ip);
			 logger.info(ip+"  "+ipduan);
			 map.put(ipduan, "1");
		 }
		 MemcachedUtil.getInstance().add(blackIpKey, map, new Date(AppConstant.CACHE_TIME));
		 return map;
	}
}
