/**
* <p>Title: ChannelService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2012-12-21
* @version 1.0
*/
package cn.adwalker.IOSChannel.service.impl;


import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.IOSChannel.dao.IChannelDao;
import cn.adwalker.IOSChannel.factory.ClickChannelFactory;
import cn.adwalker.IOSChannel.logger.ChannelReceiveLogger;
import cn.adwalker.IOSChannel.logger.ChannelSendLogger;
import cn.adwalker.IOSChannel.service.IChannelService;
import cn.adwalker.IOSChannel.service.IMemcacheChannelService;
import cn.adwalker.IOSChannel.service.ISendClickService;
import cn.adwalker.IOSChannel.util.IOSUtil;
import cn.adwalker.IOSChannel.vo.AdvertisementChannel;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
import cn.adwalker.IOSChannel.vo.Constant;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.StringUtil;


/**
 * <p>Title: ChannelService</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2012-12-21
 */
@Service("channelService")
public class ChannelServiceImpl implements IChannelService{
	
	@Resource
	private IChannelDao channelDao;
	@Resource
	private IMemcacheChannelService memcacheChannelService;
	
	private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);
	//保存点击日志
	@SuppressWarnings("unused")
	private final ChannelReceiveLogger logger =new ChannelReceiveLogger();
	 
	/**
	* json解析
	*/
	@SuppressWarnings("unused")
	@Override
	public ChannelRequestResult AdRequestOpenJson(String jsonSource) {
		long startTime = System.currentTimeMillis();
		
		ChannelRequestResult result = new ChannelRequestResult();
		//打开从服务的链接
		try {
			if(!jsonSource.equals("null")){
				jsonSource =  "{data:"+jsonSource+"}";				
				String strJson = jsonSource.replace("?(", "").replace(")", "").replace(";", "");				
				if(strJson.startsWith("{")){
					JSONObject jsonObject = JSONObject.fromObject(strJson);		
					JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("data"));
					
					JSONObject json = jsonArray.getJSONObject(0);
					String channel = String.valueOf(json.get(Constant.SOURSE));
					AdvertisementChannel advertisementChannel = memcacheChannelService.getChannel(channel);
					// jierfei ask 为什么循环而且每次都覆盖原来的值
					for(int i=0;i<jsonArray.size();i++){
						JSONObject jsonVideo = jsonArray.getJSONObject(i);
						result.setAdId(String.valueOf(jsonVideo.get(Constant.APP_ID)));
						result.setSource(String.valueOf(jsonVideo.get(Constant.SOURSE)));
						result.setDeviceId(String.valueOf(jsonVideo.get(Constant.DEVICE_ID)).toUpperCase());
						result.setStatDate(Long.valueOf(String.valueOf(jsonVideo.get(Constant.STAT_DATE))));
						result.setUdid(String.valueOf(jsonVideo.get(Constant.UDID)));
						result.setClient_ip(String.valueOf(jsonVideo.get(Constant.CLIENT_IP)));
						/*
						String pp=String.valueOf(jsonVideo.get(advertisementChannel.getPara_b_re_1()));
						System.out.println(pp);
						String str= new String(pp.getBytes("iso8859_1"), "UTF-8");
						System.out.println("UTF-8"+str);
						str= new String(pp.getBytes("iso8859_1"), "GBK");
						System.out.println("GBK"+str);
						*/
					}
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		log.info(" 请求参数解析方法 AdRequestOpenJson执行时间:"+(endTime-startTime)+"");
		return result;
	}


	/*****
	 * 构建插入数据库list
	 * @throws ParseException 
	 */
	public void saveList(ChannelRequestResult result) {
//		List<ChannelRequestResult>  channelList= new ArrayList<ChannelRequestResult>();
		String [] macAdress = (String.valueOf(result.getDeviceId())).split(",");//全部 mac地址
		
		result.setStatus(Constant.IOS_ACTION_LOG_STATUS_UNCONFIRMED);
		result.setActiviteStatus(Constant.IOS_ACTION_LOG_ACTIVITE_STATUS_UNCONFIRMED);
		channelDao.saveDate(result,macAdress);
		
	}


	/**
	* 向广告主发数据
	*/
	@Override
	public void sendDate(ChannelRequestResult vo) {
		Advertisement_IOS ios = memcacheChannelService.getIOSById(vo.getAdId());
		if(ios!=null && ios.getUrl()!=null&&!ios.getUrl().equals("")){
			if(ios.getSend_type().equals(AppConstant.SEND_TYPE_POST)){
				sendPost(vo,ios);
			}else{
				sendGet(vo,ios);
			}
		}	
	}

	
	/**
	* <p>Title: sendGet</p>
	* <p>Description:TODO</p>
	* @param vo
	* @param ios
	* @return void
	* @throws
	*/
	@SuppressWarnings("rawtypes")
	private void sendGet(ChannelRequestResult vo, Advertisement_IOS ios) {
		ISendClickService clickservice=ClickChannelFactory.createClickService(ios);
		if(null!=clickservice){
			clickservice.SendClick(vo, ios);
		}else{
		StringBuffer params = new StringBuffer();
		//回调接口 add by jief
		StringBuilder callbackUrl=new StringBuilder(ConfigUtil.getString("callback_url"));
		callbackUrl.append("?").append("appid=").append(ios.getAd_key());
		callbackUrl.append("&deviceid=").append(vo.getDeviceId());
		String url=ios.getUrl();
		if(url!=null && url.endsWith("&")){
			params.append(ios.getUrl());
		}else{
			params.append(ios.getUrl()).append("?");
		}
		params.append(ios.getDeviceid_para()).append("=").append(vo.getDeviceId());
		params.append("&").append(ios.getAdid_str()).append("=").append(ios.getAd_key());
		params .append("&").append(ios.getSourse_str());
			 //add by jief
			 if(StringUtils.isNotBlank(ios.getEventtime_para())){
				params.append("&").append(ios.getEventtime_para()).append("=").append(vo.getStatDate());
			 }
			//add by jief 2013-09-03 发送给广告主 openudid 或 idfa 或idfv
			//changed by jief 2013-11-07
			  if(StringUtils.isNotBlank(ios.getOpenudid())){
			  	   params.append("&").append(ios.getOpenudid()).append("=").append(vo.getOpenUDID());
			  	   callbackUrl.append("&OPENUDID=").append(vo.getOpenUDID());
			   }
			  //changed by jief 2013-11-06
			  if(StringUtils.isNotBlank(ios.getIdfa())){
				   //为携程修改idfa格式
				    if(ios.getId().equals("110025")){
				    	String ifa="";
				    	if(StringUtils.isNotBlank(vo.getIdfa())){
				    		ifa=vo.getIdfa().replaceAll("-","").toUpperCase();
				    	}
				    	params.append("&").append(ios.getIdfa()).append("=").append(ifa);
				    	callbackUrl.append("&IDFA=").append(ifa);
				    }else{
				    	params.append("&").append(ios.getIdfa()).append("=").append(vo.getIdfa());
				    	callbackUrl.append("&IDFA=").append(vo.getIdfa());
				    }
			   } 
			   if(StringUtils.isNotBlank(ios.getIdfv())&& StringUtils.isNotBlank(vo.getIdfv())){
			  		params.append("&").append(ios.getIdfv()).append("=").append(vo.getIdfv());
			  		callbackUrl.append("&IDFV=").append(vo.getIdfv());
			   }
		if(ios.getUdid()!=null&&!ios.getUdid().equals("")){
			params.append("&").append(ios.getUdid()).append("=").append(vo.getUdid());
		}
		
		if(ios.getClient_ip()!=null&&!ios.getClient_ip().equals("")){
			params.append("&").append(ios.getClient_ip()).append("=").append(vo.getClient_ip());
		}
		
		//add by jief callback_url 2013-11-20
		if(StringUtil.isNotBlank(ios.getCallback())){
			params.append("&")
			      .append(ios.getCallback()).append("=")
			      .append(StringUtil.encode(callbackUrl.toString()));
		}
		if(ios.getId().equals("jd")){
			String msg="mac_udid="+vo.getDeviceId()+"&unionId=41763&subunionId=adwalker601&timestamp="+vo.getStatDate()+"49f21bb3410df1c3104a1b25c87e6fba";
			msg=vo.getDeviceId()+"41763adwalker601"+vo.getStatDate()+"49f21bb3410df1c3104a1b25c87e6fba";
			System.out.println(msg);
			Map map = IOSUtil.md5s(msg); 
			params.append("&unionId=41763&subunionId=adwalker601");
			params.append("&sign=").append(map.get("32"));
			
		}else if(ios.getId().equals("tuan800_tuijian_yjf")){
			//changed by jief 2013-09-11 因为ios7 无法获取到mac 所以devicedid为空 因此必须验证
			String macNew = "";
			String mac="";
			if(StringUtils.isBlank(vo.getDeviceId())){
			    mac="020000000000";
			}else{
				mac=vo.getDeviceId().toLowerCase();
			}
			macNew=StringUtil.formatMac(mac);
			params.append("&udid=").append(macNew);
			String msg=ios.getAd_key()+macNew+"qgzapxggx574mbma";
			Map map = IOSUtil.md5s(msg); 
			params.append("&sign=").append(map.get("32"));
			params.append("&openudid=0");
			
		}else if(ios.getId().equals("tao800_tuijian_yjf")){
			String macNew = "";
			//changed by jief 2013-09-11 因为ios7 无法获取到mac 所以devicedid为空 因此必须验证
			String mac="";
			if(StringUtils.isBlank(vo.getDeviceId())){
			    mac="020000000000";
			}else{
				mac=vo.getDeviceId().toLowerCase();
			}
			macNew=StringUtil.formatMac(mac);
			params.append("&udid=").append(macNew);
			String msg=ios.getAd_key()+macNew+"wf5m79o4pdixz1mp";
			Map map = IOSUtil.md5s(msg); 
			params.append("&sign=").append(map.get("32"));
			params.append("&openudid=0");
		}
		//add by jief 2013-09-25 新增两个接口start
		else if(ios.getId().equals("tuan800_jifenqiang_yjf")){
			//changed by jief 2013-09-11 因为ios7 无法获取到mac 所以devicedid为空 因此必须验证
			String macNew = "";
			String mac="";
			if(StringUtils.isBlank(vo.getDeviceId())){
			    mac="020000000000";
			}else{
				mac=vo.getDeviceId().toLowerCase();
			}
			macNew=StringUtil.formatMac(mac);
			params.append("&udid=").append(macNew);
			String msg="tuan800"+macNew+"crov2zrj8uldokce";
			Map map = IOSUtil.md5s(msg); 
			params.append("&sign=").append(map.get("32"));
			params.append("&openudid=0");
			
		}else if(ios.getId().equals("tao800_jifenqiang_yjf")){
			String macNew = "";
			//changed by jief 2013-09-11 因为ios7 无法获取到mac 所以devicedid为空 因此必须验证
			String mac="";
			if(StringUtils.isBlank(vo.getDeviceId())){
			    mac="020000000000";
			}else{
				mac=vo.getDeviceId().toLowerCase();
			}
			macNew=StringUtil.formatMac(mac);
			params.append("&udid=").append(macNew);
			String msg="tao800"+macNew+"q6cko8i51hfnyymk";
			Map map = IOSUtil.md5s(msg); 
			params.append("&sign=").append(map.get("32"));
			params.append("&openudid=0");
		}
		//add by jief 2013-09-25 新增两个接口end
		else if(ios.getId().equals("594175342_yjf")){
			//add by jief for 游族
			params.append("&").append("sign=");
			if(StringUtils.isNotBlank(vo.getOpenUDID())){
			  	 params.append(vo.getOpenUDID());	
			}
			params.append("|");
			if(StringUtils.isNotBlank(vo.getIdfa())){
				   params.append(vo.getIdfa());
			}
		}else if(ios.getId().equals("662008120_yjf") || ios.getId().equals("590307469_yjf")){
		   //为了仙变和搜狐买车宝做的开发 add  by jief2013-10-09
			String macNew = "";
			if(StringUtils.isNotBlank(vo.getDeviceId())){
				String mac=vo.getDeviceId().toUpperCase();
				String[] strs = new String[mac.length() - 1];
				for (int k = 0; k < mac.length() - 1; k += 2) {
				strs[k] = mac.substring(k, k + 2);
				macNew += strs[k] + ":";
				}	
			  macNew = macNew.substring(0, macNew.length()-1);
			}
			params.append("&mac=").append(macNew);
		}
		try{
		String k =IOSUtil.sendGet(params.toString());
		ChannelSendLogger logger = new ChannelSendLogger();
		logger.logInfo(params.toString(),k);
		}catch(Exception e){
			log.error( params+"给广告主发送点击出错啦",e);
		}
	 }
//		log.info("result:" + k);
	}


	/**
	 * 
	* <p>Title: sendPost</p>
	* <p>Description:TODO</p>
	* @return void
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	private void sendPost(ChannelRequestResult vo,Advertisement_IOS ios){
		StringBuffer params = new StringBuffer();
		params.append("{'")
		      .append(ios.getAdid_str()).append("':'").append(ios.getAd_key()).append("'")
		      .append(",'").append(ios.getDeviceid_para()).append("':'").append(vo.getDeviceId()).append("'")
		      //.append(",'").append(ios.getSourse_str()).append("':'").append(ios.getAd_key()).append("'")
		      .append(",'").append(ios.getEventtime_para()).append("':'").append(vo.getStatDate()).append("'");
	    
		String[] soures = ios.getSourse_str().split("=");
		if(soures.length==2){
			params.append(",'").append(soures[0]).append("':'").append(soures[1]).append("'");
		}
		
		if(ios.getUdid()!=null&&!ios.getUdid().equals("")){
			params.append(",'").append(ios.getUdid()).append(':').append(vo.getUdid()).append("'");
		}
		
		if(ios.getClient_ip()!=null&&!ios.getClient_ip().equals("")){
			params.append(",'").append(ios.getClient_ip()).append(':').append(vo.getClient_ip()).append("'");
		}
		//add by jief 2013-09-03 发送给广告主 openudid 或 idfa 或idfv
		if(StringUtils.isNotBlank(ios.getOpenudid())){
		  	params.append(",'").append(ios.getOpenudid()).append("':'").append(vo.getOpenUDID()).append("'");	
		}else if(StringUtils.isNotBlank(ios.getIdfa())){
		  	params.append(",'").append(ios.getIdfa()).append("':'").append(vo.getIdfa()).append("'");	
		}else if(StringUtils.isNotBlank(ios.getIdfv())){
		  	params.append(",'").append(ios.getIdfv()).append("':'").append(vo.getIdfv()).append("'");	
		}     
	    if(ios.getAd_key().equals("PMH")){
	    	String msg="AllianceNo=yiqifa&AppSign=PMH&DeviceID="+vo.getDeviceId()+"&SiteNo=a018883afbb443f298b3e27dc0262365";
	    	System.out.println(msg);
	    	Map map = IOSUtil.md5s(msg);
	    	params.append(",'Sign':'"+map.get("32")+"'");	    	
	    }
		
		params.append("}");	
		String k =IOSUtil.sendPost(ios.getUrl(),params.toString());
		
		ChannelSendLogger logger = new ChannelSendLogger();
		logger.logInfo(params.toString(),k);
		log.info("params:" + params);
		log.info("result:" + k);
	  
	}


//	@Override
//	public ResponseResult requestvalidate(ChannelRequestResult result,
//			HttpServletRequest request) throws Exception {
//		ResponseResult resp=new ResponseResult();
//		 String appid = String.valueOf(request.getParameter(Constant.APP_ID));			//广告接口配置表中的id值
//		 String channel = String.valueOf(request.getParameter(Constant.SOURSE));		//渠道名称
//		 String deviceid = request.getParameter(Constant.DEVICE_ID);					//设备信息mac change by jief del String.vlaueOf()
//		 String udid = String.valueOf(request.getParameter(Constant.UDID));				//udid
//		 String client_ip = String.valueOf(request.getParameter(Constant.CLIENT_IP));	//客户端ip
//		 String callback_url=request.getParameter("callback_url");						//渠道的回调地址
//		 String app_key=request.getParameter("app_key");								//自有的应用id add By jief
//		 String ad_key=request.getParameter("ad_key");									//自有的广告id add by jief
//		 String page_type=request.getParameter("page_type");							//自有平台的广告类型	add by jief
//		 String sdkVersion=request.getParameter("sdkVersion");							//sdk版本
//		 String redirect=request.getParameter("redirect");								//是否跳转1、跳转。0、非跳转
//		 String OpenUDID = request.getParameter(Constant.IOS_OpenUDID);					//openudid
//		 if(StringUtils.isBlank(OpenUDID)) {
//			 OpenUDID="";
//		 }
//		 String IDFA = request.getParameter(Constant.IOS_IDFA);							//idfa
//		 if(StringUtils.isBlank(IDFA)){
//			 IDFA="";
//		 }else{
//			 IDFA=IDFA.toUpperCase();
//		 }
//		 String IDFV = request.getParameter(Constant.IOS_IDFV);							//idfv
//		 if(StringUtils.isBlank(IDFV)){
//			 IDFV="";
//		 }
//		 String os=request.getParameter("os");											//ios操作系统版本
//		//add by jief ios7 无法获取mac 所以渠道或自己平台不要发送mac值了
//		 if(StringUtils.isBlank(deviceid) || 
//				 deviceid.replaceAll(":", "").equals("020000000000") || 
//				 (StringUtils.isNotBlank(os) && os.compareTo("7")>=0)) {
//			 if(StringUtil.isBlank(os))
//			    os="7.*";
//		 }else{
//			deviceid = deviceid.replaceAll(":", ""); 
//			if(StringUtil.isBlank(os))
//				os="6.*";
//		 }
//		 //该参数非必须，如果不填，默认：数据接收时间。
//		 Long statDate =null;
//		 if(request.getParameter(Constant.STAT_DATE)!=null){
//			 statDate = Long.valueOf(String.valueOf(request.getParameter(Constant.STAT_DATE)));
//		 }else{
//			 statDate =  System.currentTimeMillis()/1000;  
//		 }
//		 
//		 if(StringUtils.isBlank(deviceid) || 
//				 deviceid.equals("020000000000")){
//		     logger.logInfo(appid, channel, OpenUDID, client_ip, statDate.toString());
//		 }else{
//			 logger.logInfo(appid, channel, deviceid, client_ip, statDate.toString());
//		 }
//		 //验证appid 是否合法
//		 Advertisement_IOS aios= memcacheChannelService.getIOSById(appid);
//		 if(aios==null){
//			 resp.setSuccess("false");
//			 resp.setMessage("appid 不合法");
//			 return resp;
//		 }
//		 result.setAdId(appid);
//		 result.setChannel(channel);
//		 result.setSource(channel);
//		 result.setDeviceId(deviceid.toUpperCase());
//		 result.setStatDate(statDate);
//		 result.setUdid(udid);
//		 result.setClient_ip(client_ip);
//		 result.setOpenUDID(OpenUDID);
//		 result.setIdfa(IDFA);
//		 result.setIdfv(IDFV);
//		 result.setOs_version(os);
//		 result.setAd_key(ad_key);
//		 result.setApplication_key(app_key);
//		 result.setRedirect(redirect);
//		 if(StringUtil.isNotBlank(page_type) && page_type.matches("[0-9]+")){
//			 result.setPage_type(Integer.parseInt(page_type));
//		 }
//		 if(StringUtils.isNotBlank(callback_url)){
//			 callback_url=StringUtil.decode(callback_url);
//		     result.setCallback_url(callback_url);
//		 }
//		 result.setSdkversion(sdkVersion);
//		return resp;
//	}
}