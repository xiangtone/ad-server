package cn.adwalker.IOSChannel.picker.xmemcached;

import java.util.Date;

import cn.adwalker.IOSChannel.picker.bean.AdAreaRate;
import cn.adwalker.IOSChannel.picker.bean.ApplicateAreaNum;
import cn.adwalker.IOSChannel.picker.bean.IosActionLog;
import cn.adwalker.IOSChannel.picker.bean.UserInfo;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.HGB2PINYIN;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.SpringUtil;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.ad.bean.Advertise;
import cn.adwalker.ad.bean.CacheElement;
import cn.adwalker.ad.bean.DevApp;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;

public class PickerCache {
	private static final Logger logger = Logger.getLogger(PickerCache.class);
	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil.getString("cache_time"));
	public final static String USER_INFO="userInfoc_";
	public final static String applicateAreaNum="applicateArea_";
	public final static String adAreaRate="adAreaRate_";
	public final static String IOS_CLICK_KEY="ic";
	public final static String MEMCACHE_AD = "memAd_new";
	public final static String MEMCACHE_APPLICATION = "memApps_new";
	
	
	public static UserInfo loadUserInfo(String uuid){
		UserInfo u = MemcacheCached.getInstance().get(USER_INFO+uuid, UserInfo.class);
		if(u==null){
			SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
			u=supporDao.loadUserInfo(uuid);
			if(u!=null){
				MemcacheCached.getInstance().add(USER_INFO+uuid, u, new Date(CACHE_TIME));
			    return u;
			}
		}
		return u;
	}
	public static String getAreaCodeByIp(String ip){
		if(!StringUtil.isEmpty(ip)){
			String key = "ip_"+ip.replaceAll("\\.", "");
			String areaCode =  MemcacheCached.getInstance().get(key, String.class);
			if(null==areaCode){
				areaCode = StringUtil.getAreaCodeByIp(ip);
				if(null!=areaCode){
					MemcacheCached.getInstance().add(key, areaCode, new Date(CACHE_TIME));
				}
			}else{
				logger.logInfo("memcache_areacode:"+areaCode);
			}
			return areaCode;
		}else{
			return null;
		}
	}
	
	
	public static ApplicateAreaNum findApplicateAreaNum(Integer appId,String areaCode){
		  HGB2PINYIN pinyin = new HGB2PINYIN();
		  String code = pinyin.getAllPY(areaCode);
		  String key = applicateAreaNum+code;
		  ApplicateAreaNum aan = MemcacheCached.getInstance().get(key,ApplicateAreaNum.class);
		  if(aan==null){
				SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
			   	aan = supporDao.findApplicateAreaNum(areaCode);
			   	if(aan==null){
			   		aan = new ApplicateAreaNum(0, appId, areaCode, 999999999);
			   	}
            	MemcacheCached.getInstance().add(key, aan, new Date(CACHE_TIME));
		  }
		  return aan;
	}
	
	public static AdAreaRate findAdAreaRate(Integer adId,String areaCode){
		  HGB2PINYIN pinyin = new HGB2PINYIN();
		  String code = pinyin.getAllPY(areaCode);
		  AdAreaRate rate = MemcacheCached.getInstance().get(adAreaRate+adId+code,AdAreaRate.class);
		  if(rate==null){
				SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class); 
				rate = supporDao.findAdAreaRate(adId, areaCode);
				if(rate==null){
					rate = new AdAreaRate(0, adId, areaCode, 100);
				}
				MemcacheCached.getInstance().add(adAreaRate+adId+code, rate, new Date(CACHE_TIME));
		  }
		  return rate;
	}
	
	public static boolean addIosActionLog( IosActionLog action){
          String key = IOS_CLICK_KEY+action.getAdId()+action.getMac();
          if(!MemcacheCached.getInstance().keyExists(key)){
        	  Integer time = ConfigUtil.getInteger("clickcache.time", 86400000);
        	  return MemcacheCached.getInstance().add(key, action, new Date(time));
          }else{
        	  logger.logInfo("keyExists [ appId:"+action.getAdId()+",deviceid:"+action.getMac()+"]");
          }
          return false;
	}
	public static IosActionLog getIosActionLog(String appId,String deviceid){
        long a = System.currentTimeMillis();
		String key = IOS_CLICK_KEY+appId+deviceid;
        IosActionLog action = MemcacheCached.getInstance().get(key, IosActionLog.class);
        logger.logInfo((System.currentTimeMillis()-a)+" cachems ");
        if(action==null){
        	SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class); 
        	action = supporDao.findIosActionLog(deviceid,appId);
            logger.logInfo("findIosActionLog from db  [ appId:"+appId+",deviceid:"+deviceid+"]");
        }
        logger.logInfo((System.currentTimeMillis()-a)+" cachems2 ");
         
        return action;
	}
	public static void removeIosActionLog(String appId,String deviceid){
		String key = IOS_CLICK_KEY+appId+deviceid;
		MemcacheCached.getInstance().delete(key);
		logger.logInfo("remove memcacheKey:"+key);
	}
	
	
	/**
	 * 	public String appid;
	public String mac;
	public String appId;
	public String deviceid;
	public String source;
	public String udid;
	public String client_ip;
	public String callback;
	public String app_key;
	public String ad_key;
	public String page_type;
	public String sdkVersion;
	public String OPENUDID;
	public String IDFA;
	public String IDFV;
	public String os;
	public String eventtime;
	public String areaCode;
	 * */
	
	
	public static void deleteCache(String key){
		MemcacheCached.getInstance().delete(key);
	}
	
	public static void main(String[] args){
		System.out.println(24*60*60*1000);
		MemcacheCached.getInstance().add("testaa", "hellworld!", new Date(100000));
		for(int i=200;i>0;i--){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.logInfo(MemcacheCached.getInstance().get("testaa"));
		}
	}
	@SuppressWarnings("unchecked")
	public static Advertise getAdvertise(String adId){
		Advertise ad=null;
		if (!StringUtil.isEmpty(adId)) {
			CacheElement<Advertise> element=MemcacheCached.getInstance().get(MEMCACHE_AD+adId, CacheElement.class);
			if (element!=null&&element.getElement()!=null) {
				ad=element.getElement();
			}else {
				//查询广告，放入缓存
				SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
				ad=supporDao.getAdvertise(adId);
				element=new CacheElement<Advertise>(200);
				element.setElement(ad);
				MemcacheCached.getInstance().clear(MEMCACHE_AD+ adId);
				MemcacheCached.getInstance().add(MEMCACHE_AD + adId, element,new Date(AppConstant.CACHE_TIME));
				
			}
		}
		return ad;
	}
	/**
	 * 获取app对象
	 * @param appId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static DevApp getDevApp(Long appId) {
		// 获得应用
		DevApp app =null;
		if (!StringUtil.isEmpty(appId)) {
			CacheElement<DevApp> element= MemcacheCached.getInstance().get(MEMCACHE_APPLICATION+ appId,CacheElement.class);
			if (element!=null&&element.getElement()!= null) {
				app=element.getElement();
			}else{
				SupportDao supporDao = SpringUtil.getBean("supportDao",SupportDao.class);
				app = supporDao.getApplication(appId);
				element=new CacheElement<DevApp>(200);
				element.setElement(app);
				MemcacheCached.getInstance().clear(MEMCACHE_APPLICATION+ appId);
				MemcacheCached.getInstance().add(MEMCACHE_APPLICATION + appId, element,new Date(AppConstant.CACHE_TIME));
			}
		}
		
		return app;
	}
	
}
