/**
* <p>Title: AdvertisementService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.dao.IChannelDao;
import cn.adwalker.IOSChannel.logger.AdSendLogger;
import cn.adwalker.IOSChannel.service.IAdvertisementService;
import cn.adwalker.IOSChannel.service.IMemcacheChannelService;
import cn.adwalker.IOSChannel.thread.AdvertisementThread;
import cn.adwalker.IOSChannel.util.IOSUtil;
import cn.adwalker.IOSChannel.vo.AdvertisementChannel;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
import cn.adwalker.ad.pool.ThreadPool;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.StringUtil;

/**
 * <p>Title: AdvertisementService</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
@Service("advertisementService")
public class AdvertisementServiceImpl implements IAdvertisementService{

	 private static final Logger log = LoggerFactory.getLogger(AdvertisementServiceImpl.class);
	 
	 
	 
	@Resource
	private IChannelDao channelDao;
	
	@Resource
	private AdvertisementServiceImpl advertisementService;
	
	@Resource
	private IMemcacheChannelService memcacheChannelService;
	
	
//	/**
//	* <p>Title: comfirmActivateCheck</p>
//	* <p>Description:TODO</p>
//	* @param appkey
//	* @param deviceid
//	* @return void
//	* @throws
//	*/
//	@Override
//	public Map comfirmActivateCheck(ChannelRequestResult result) {
//	   Advertisement_IOS adios=memcacheChannelService.getIOSByKey(result.getAdId());
//	   //修改携程的回调函数中的idfa参数  add by jief 2013-09-11
//	   if(adios!=null && "ctrip8892".equals(result.getAdId())){
//	    	//修改将携程发送过来的idfa转换成正常格式
//	    	String idfa = result.getIdfa();
//	    	if(StringUtils.isNotBlank(idfa) && idfa.length()==32){
//	    		String idfaNew = "";
//	    		char []ifas=idfa.toCharArray();
//	    		for(int i=0;i<ifas.length;i++){
//	    			idfaNew+=ifas[i];
//	    			if(i==7 || i==11 || i==15 || i==19){
//	    				idfaNew+="-";
//	    			}
//	    		}
//			result.setIdfa(idfaNew);
//	       }
//	    }
//		Map<String,String> map =new HashMap<String,String>();
//		ChannelRequestResult chanVO =null; 
//		//专门为了淘800和折800的积分墙
//		ChannelRequestResult vo=null;
//		if("tuan800".equals(result.getAdId()) || "tao800".equals(result.getAdId())){
//			 chanVO=channelDao.getActivateMAcFor800(result.getAdId(),result.getDeviceId(),result.getOpenUDID(),result.getIdfa(),result.getIdfv(),adios);
//			 vo=channelDao.getSelectOneByMacFor800(result.getAdId(),result.getDeviceId(),result.getOpenUDID(),result.getIdfa(),result.getIdfv(),adios);
//		}else{
//			 chanVO=channelDao.getActivateMAc(result.getAdId(),result.getDeviceId(),result.getOpenUDID(),result.getIdfa(),result.getIdfv(),adios);
//			 vo = channelDao.getSelectOneByMac(result.getAdId(),result.getDeviceId(),result.getOpenUDID(),result.getIdfa(),result.getIdfv(),adios);
//		}
//		if(chanVO!=null){
//			map.put("msg", "不可重复确认激活");
//			map.put("flag", "false");
//		}else if(vo==null){
//			map.put("msg", "确认失败,没有记录");
//			map.put("flag", "false");
//		}else if(vo.getActiviteStatus().equals(Constant.IOS_ACTION_LOG_ACTIVITE_STATUS_CONFIRMED)){
//			map.put("msg", "不可重复确认激活");
//			map.put("flag", "false");
//		}else{			
//			result.setId(vo.getId());
//			channelDao.updateChannel(vo);
//			map.put("msg", "确认成功");
//			map.put("flag", "true");
//			//sendComfirmDate(vo);
//			//启动线程执行service
//			AdvertisementThread r = new AdvertisementThread(vo,advertisementService);
//			r.start();
//			//积分墙
//			if(vo.getPage_type()!=null&&vo.getChannel()!=null)
//			//去掉墙类型限制 jief 2013-11-25
//			if(vo.getChannel().equals(AppConstant.ZIJIREN)){
//				Urls urls =urlsCache.getUrls();
//				StringBuffer  params = new StringBuffer();
//				params.append(urls.getService_url()).append(ConfigUtil.getString("iosAddScore"))
//				      .append("?adId=").append(result.getAdId()).append("&deviceId=").append(StringUtils.isBlank(result.getDeviceId())?vo.getOpenUDID():result.getDeviceId());
//				try{
//				log.info("params:" + params);
//				String k =IOSUtil.sendGet(params.toString());
//				}catch(Exception e){
//					log.info( params+"给自己人返积分出错啦..",e);
//				}
////				log.info("params:" + params);
////				log.info("result:" + k);
//			}
//		  }
//		return map;
//	}

	/**
	* <p>Title: sendComfirmDate</p>
	* <p>Description:渠道回调接口</p>
	* @return void
	* @throws
	*/
	@Override
	public void sendComfirmDate(ChannelRequestResult vo) {
		AdSendLogger logger =new AdSendLogger();
		//如果渠道提供了回调接口了直接调用回调接口就可以了
	   if(StringUtils.isNotBlank(vo.getCallback_url())){
			String url=vo.getCallback_url();
			try{
				log.info("params="+url);
				String k =IOSUtil.sendGet(url);
				logger.logInfo(url, k);
			}catch(Exception e){
				log.error(url+"给渠道返回确认出错啦..",e);
			}
		}else{
		   AdvertisementChannel channel = memcacheChannelService.getChannel(vo.getChannel());
		   // add by jief 2014-01-20 add channel!=null
		   if(channel!=null && StringUtils.isNotBlank(channel.getUrl())){
			//changed by jief 2013-09-10 
			if(channel.getChannel().equals("adSage") && StringUtils.isNotBlank(vo.getMac())){
				String macOne = vo.getMac();
				vo.setMac(StringUtil.formatMac(macOne));
			}
			//change by jief 2013-09-24 for 米迪 因为米迪只用一个字段存值 即存mac又存idfa
			if(channel.getChannel().contains("miidi") || 
					channel.getChannel().equals("mopan") || 
					channel.getChannel().contains("dianru")){
				if(StringUtils.isBlank(vo.getMac())){
				    vo.setMac(vo.getIdfa());
				}
			}
			StringBuffer  params = new StringBuffer();
			params.append(channel.getUrl()).append("")
			              .append(channel.getAdid_para()).append("=").append(vo.getAdId())
			              .append("&").append(channel.getDeviceid_para()).append("=").append(StringUtils.isBlank(vo.getMac())?"":vo.getMac())
			              .append("&").append(channel.getTime_para()).append("=").append(System.currentTimeMillis());
			if(StringUtils.isNotBlank(channel.getOpenUdid()) && StringUtils.isNotBlank(vo.getOpenUDID())){
				params.append("&").append(channel.getOpenUdid()).append("=").append(vo.getOpenUDID());
			}
			if(StringUtils.isNotBlank(channel.getIdfa()) && StringUtils.isNotBlank(vo.getIdfa())){
				params.append("&").append(channel.getIdfa()).append("=").append(vo.getIdfa());
			}
			if(StringUtils.isNotBlank(channel.getIdfv()) && StringUtils.isNotBlank(vo.getIdfv())){
				params.append("&").append(channel.getIdfv()).append("=").append(vo.getIdfv());
			}
			if(channel.getChannel().equals(AppConstant.CHANNEL_RUANLIE)){
				if(vo.getAdId().equals("9c69842b389a48b38ab8c5a3a9cfeaa7")){
					params.append("&").append("appId=").append("1122");
				}
			}
			try{
				log.info("params="+params.toString());
				String k =IOSUtil.sendGet(params.toString());
				logger.logInfo(params.toString(), k);
			}catch(Exception e){
				log.error(params+"给渠道返回确认出错啦..",e);
			}
		}
	 }
	}

	/**
	 * <p>回调检查更新 author: jief 2013-11-28</p>
	 * @param result
	 */
	@Override
	public Map<String, String> callbackActivateCheck(ChannelRequestResult result) {
		  Advertisement_IOS adios=memcacheChannelService.getIOSByKey(result.getAdId());
		  Map<String,String> map =new HashMap<String,String>();
		  //修改携程的回调函数中的idfa参数  add by jief 2013-09-11
		   if(adios!=null && "ctrip8892".equals(result.getAdId())){
		    	//修改将携程发送过来的idfa转换成正常格式
				result.setIdfa(StringUtil.formatIDFA(result.getIdfa()));
		   }
			ChannelRequestResult vo = channelDao.getClickLog(result, adios);
			if(vo==null){
				map.put("msg", "确认失败,没有记录");
				map.put("flag", "false");
				return map;
			}
//			//验证重复激活 change by jief 2014-02-10 但是不是线程安全的，可能会有重复插入激活数据的问题
//		    boolean isa=channelDao.isActivate(result, adios);
//		    if(isa){
//				map.put("msg", "不可重复确认激活");
//				map.put("flag", "false");
//				return map;
//			}
		    //但是不是线程安全的，可能会有重复插入激活数据的问题
//			channelDao.saveActiveLog(vo);
			//验证重复激活并插入 add by jief 2014-02-10 线程安全的。防止了重复插入数据的问题
			channelDao.saveActiveLogIfNotExist(vo);
			map.put("msg", "确认成功");
			map.put("flag", "true");
			//启动线程执行service
			AdvertisementThread r = new AdvertisementThread(vo,advertisementService);
			//从线程池获取线程执行
			ThreadPool.getInstance().getThreadPoolExecutor().execute(r);
//			r.start();
			//积分墙
			/*
			if(vo.getPage_type()!=null&&vo.getChannel()!=null)
			//去掉墙类型限制 jief 2013-11-25
			if(vo.getChannel().equals(AppConstant.ZIJIREN)){
			Urls urls =urlsCache.getUrls();
			StringBuffer  params = new StringBuffer();
					params.append(urls.getService_url()).append(ConfigUtil.getString("iosAddScore"))
					      .append("?adId=").append(vo.getAdId())
					      .append("&appid=").append(vo.getApplication_key())
					      .append("&deviceId=").append((StringUtils.isBlank(vo.getDeviceId())||"020000000000".equals(vo.getDeviceId()))?vo.getOpenUDID():vo.getDeviceId())
						  .append("&pageType=").append(vo.getPage_type())
						  .append("&channel=").append(vo.getChannel());
						   
					try{
					 String k =IOSUtil.sendGet(params.toString());
					 log.info("params:" + params);
					 log.info("result:" + k);
					}catch(Exception e){
						log.error(params+"给自己人返积分出错啦。。",e);
					}
		   }
		   */
		return map;
	}


}
