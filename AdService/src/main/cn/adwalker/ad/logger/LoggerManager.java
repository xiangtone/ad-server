package cn.adwalker.ad.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.picker.monitor.AdInfo;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.DateUtil;

//日志管理类
public class LoggerManager {
    public static String kt="\t";
	//0:时间 1:appId 2:pageType 3:channel 4:adId 5:categoryId 6:payType 7:price 8:uuid 9:area 10:ip 11:sdkVersion 12:imsi
	// AdDetailInfoLogger  /usr/local/ad/logs/adclick/click.log
	public static void logAdDetailInfo(String appId,String pageType,String channel,String adId,String categoryId,String payType,String price,String uuid,String area,String ip,String sdkVersion,String terminalType,String imsi){
		String dateString = DateUtil.getDateStringByPattern(new Date(),DateUtil.PARTTERN_DATE_TIME);
		AdDetailInfoLogger logger = new AdDetailInfoLogger();
		logger.logInfo(dateString, appId,pageType, channel,adId,categoryId,payType,price,uuid,area,ip,sdkVersion,terminalType,imsi);	
	}
	//0:时间 1:appId 1:page_type 2:channel 3:adId 4:categoryId 5:pay_type 6:price 7:uuid 8:area 9:ip:sdkversion 10:bannerTag 11:imsi
	public static void logAdDownLoad(String appId,String pageType,String channel,String adId,String categoryId,String payType,String price,String uuid,String area,String ip,String sdkversion,String bannerTag,String imsi){
		String dateString = DateUtil.getDateStringByPattern(new Date(),DateUtil.PARTTERN_DATE_TIME);
		AdDownLogLogger logger = new AdDownLogLogger();
		logger.logInfo(dateString, appId, pageType, channel, adId, categoryId, payType, price, uuid, area, ip, sdkversion, bannerTag, imsi);
	}
	//0:时间  1:appId 2:typeId	3:channel	4:adId	5:category_id		6:blance_mode		7:priceStr	8:uuid	9:area	10:ip	11:sdk版本      ssid,bssid,phoneName,latitude,longitude,idfa
	public static void logAdPv(Advertise ad,Long appId,String pageType,String channel,String uuid,String area,String ip,String version,String ssid,String bssid,String phoneName,String latitude,String longitude,String idfa){
		if(ad!=null){
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			String dateStr = formatter.format(new Date());
			String priceStr="0";
			if(ad.getBlance_mode()!=null&&ad.getBlance_mode().equals(AppConstant.AD_CPM)){
		        	priceStr = ad.getBlance_price().toString();
		    }
	        if(area==null||area.length()==0){
				 area="全国";
			 }
			try{				
				String s[] = {dateStr,String.valueOf(appId),String.valueOf(ad.getType_id()),channel,String.valueOf(ad.getId()),String.valueOf(ad.getCategory_id()),
						ad.getBlance_mode(),priceStr,uuid,area,ip,version,ssid,bssid,phoneName,latitude,longitude,idfa};
				
				AdInfoLogger logger = new AdInfoLogger();
				logger.log(s);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//0时间	1:appId	2:typeId	3:渠道	4:uuid	5:地区	6:ip	7:sdk版本
	public static void loggerTypeInfo(int type_id,Long appId,String version,String channel,String uuid,String area,String ip,String ssid,String bssid,String phoneName,String latitude,String longitude,String idfa){
			TypeInfoLogger logger = new TypeInfoLogger();
			logger.typeInfo(appId, version, channel, uuid, area, ip,ssid,bssid,phoneName,latitude,longitude,idfa,type_id);
	}
	
	//0:appId 1:pageType 2:channel 3:uuid 4:area 5:ip 6:sdkVersion 7:bannerTag 8:imsi
	///usr/local/ad/logs/adshow/adShow.log
	public static void logAdShowInfo(Advertise ad,Long appId,String pageType,String channel,String uuid,String area,String ip,String sdkVersion,Integer bannerTag,String imsi,String ssid,String bssid,String phoneName,String latitude,String longitude,String idfa){
		if(ad!=null){
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			String dateStr = formatter.format(new Date());
			String priceStr="0";	       
	        if(area==null||area.length()==0){
				 area="全国";
			 }
			try{
				String s[] = {dateStr,String.valueOf(appId),ad.getBlance_mode(),channel,String.valueOf(ad.getId()),String.valueOf(ad.getCategory_id()),
						ad.getBlance_mode(),priceStr,uuid,area,ip,sdkVersion,bannerTag.toString(),imsi,ssid,bssid,phoneName,latitude,longitude,idfa};
				AdShowInfoLogger logger = new AdShowInfoLogger();
				logger.log(s);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//0:时间 1:appId 2: pageType 3:channel 4:adId 5:categoryId 6:payType 7:price 8:uuid 9:area 10:ip 11:sdkVersion 12:bannerTag 13:imsi
	///usr/local/ad/logs/adclickd/clickd.log ka
	public static void logAdbannerAc4(String appId,String pageType,String channel,String adId,String categoryId,String payType,String price,String uuid,String area,String ip,String sdkVersion,String bannerTag,String imsi,String ssid,String bssid,String phoneName,String latitude,String longitude,String idfa){
		String dateString = DateUtil.getDateStringByPattern(new Date(),DateUtil.PARTTERN_DATE_TIME);
		AdbannerAc4Logger logger = new AdbannerAc4Logger();
        logger.logInfo(dateString, appId, pageType, channel, adId, categoryId, payType, price, uuid, area, ip, sdkVersion, bannerTag, imsi,ssid,bssid,phoneName,latitude,longitude,idfa);
	}
	
	/**
	 * 墙展示成功,展示一次记录一次日志
	 * 
	 * */
	public static void loggerPageShow(String appId,String sdkVersion,String channel,String uuid,String area,String ip,String pageType,Integer bannerTag){
		PageShowLogger pageShowLogger = new PageShowLogger();
		 String str = "全国";
		 if(area!=null){
			 str=area;
		 }
		String arr[] ={appId,sdkVersion,channel,uuid,str,ip,pageType,String.valueOf(bannerTag)};
		pageShowLogger.log(arr);
	}
	
	//控量日志
	public static void loggerMonitor(AdInfo ad){
		Logger logger = Logger.getLogger("monitor");
		logger.info(kt+ad.getAdId()+kt+ad.getCpm()+kt+ad.getCpc()+kt+ad.getCpa()+kt+ad.getCpd());
	}
	//callapi
	public static void loggerCallApi(Object ... strs ){
		Logger logger = Logger.getLogger("callapi");
		StringBuffer sbuf = new StringBuffer();
		for(Object str:strs){
			sbuf.append(kt+StringUtil.dealNull(str,"null"));
		}
		logger.info(sbuf.toString());
	}
	//0:时间 1:appId 2:pageType 3:channel 4:adId 5:categoryid 6:payType 7:price 8:uuid 9:area 10 sdkVersion 11:score 12:signNum
	public static void loggerDevAddScore(Long appId,String pageType,String channel,Long adId,String categoryid,String payType,Double price,String uuid,String area,String sdkVersion,Integer score,String ip,Integer signNum){
		String dateString = DateUtil.getDateStringByPattern(new Date(),DateUtil.PARTTERN_DATE_TIME);
		DevAddScoreLogger logger = new DevAddScoreLogger();
		logger.log(dateString,StringUtil.dealNull(appId),pageType,channel,StringUtil.dealNull(adId),StringUtil.dealNull(categoryid),payType,(StringUtil.equals(payType, AppConstant.AD_CPA)?StringUtil.dealNull(price):"0"),uuid,area,sdkVersion,StringUtil.dealNull(score),ip,StringUtil.dealNull(signNum));
	}
	
	
	public static void adDetailsInfo(Advertise ad,Long appId,String typeId,String channel,String uuid,String area,String ip,String sdk,String terminalType,String imsi) {	
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String dateStr = formatter.format(new Date());
		String priceStr="0";
		 if(ad.getBlance_mode().equals(AppConstant.AD_CPC)&&(terminalType==null||!terminalType.equals(AppConstant.PAD))){
	        	priceStr = ad.getBlance_price().toString();
	        }
		 if(area==null||area.length()==0){
			 area="全国";
		 }
		 if(terminalType==null){
			 terminalType="";
		 }
		try{
			AdDetailInfoLogger logger = new AdDetailInfoLogger();
			logger.logInfo(dateStr,String.valueOf(appId),typeId,channel,String.valueOf(ad.getId()),String.valueOf(ad.getCategory_id()),
					ad.getBlance_mode(),priceStr,uuid,area,ip,sdk,terminalType,imsi);
		}catch(Exception e){
			e.printStackTrace();
		}
}
	
	
}
