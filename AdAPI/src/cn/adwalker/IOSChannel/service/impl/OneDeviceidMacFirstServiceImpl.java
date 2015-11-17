package cn.adwalker.IOSChannel.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.adwalker.IOSChannel.logger.ChannelSendLogger;
import cn.adwalker.IOSChannel.service.ISendClickService;
import cn.adwalker.IOSChannel.util.IOSUtil;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.StringUtil;

public class OneDeviceidMacFirstServiceImpl implements ISendClickService{
private static final Logger log=Logger.getLogger(OneDeviceidMacFirstServiceImpl.class);
	
	public static OneDeviceidMacFirstServiceImpl oneDeviceidMacFirstService=null;
	private OneDeviceidMacFirstServiceImpl(){}
	public static OneDeviceidMacFirstServiceImpl getInstance(){
	   if(oneDeviceidMacFirstService==null){
		   synchronized (RawMacServiceImpl.class) {
			   if(oneDeviceidMacFirstService==null){
				   oneDeviceidMacFirstService=new OneDeviceidMacFirstServiceImpl();
			   }
		   }
		}
		return oneDeviceidMacFirstService;
	}
	@Override
	public void SendClick(ChannelRequestResult vo, Advertisement_IOS ios) {
		//广告主接口
				StringBuffer params = new StringBuffer();
				//回调接口 add by jief
				StringBuilder callbackUrl=new StringBuilder(ConfigUtil.getString("callback_url"));
				callbackUrl.append("?").append("appid=").append(ios.getAd_key());
				callbackUrl.append("&deviceid=").append(vo.getDeviceId());
				//add by jief 2014-02-19
				String url=ios.getUrl();
				if(url!=null && url.endsWith("&")){
					params.append(ios.getUrl());
				}else{
					params.append(ios.getUrl()).append("?");
				}
//				params.append(ios.getUrl()).append("?");
				if(StringUtils.isNotBlank(vo.getDeviceId())){
					params.append(ios.getDeviceid_para()).append("=").append(vo.getDeviceId());
				}else if(StringUtils.isNotBlank(vo.getIdfa())){
					params.append(ios.getDeviceid_para()).append("=").append(vo.getIdfa());
				}
				params.append("&").append(ios.getAdid_str()).append("=").append(ios.getAd_key())
			      	  .append("&").append(ios.getSourse_str());
				//add by jief
			    if(StringUtils.isNotBlank(ios.getEventtime_para())){
					params.append("&").append(ios.getEventtime_para()).append("=").append(vo.getStatDate());
				}
				//changed by jief 2013-11-07
				if(StringUtils.isNotBlank(ios.getOpenudid())){
					params.append("&").append(ios.getOpenudid()).append("=").append(vo.getOpenUDID());
					callbackUrl.append("&OPENUDID=").append(vo.getOpenUDID());
				}
				//changed by jief 2013-11-06
				if(StringUtils.isNotBlank(ios.getIdfa())){
					params.append("&").append(ios.getIdfa()).append("=").append(vo.getIdfa());
					callbackUrl.append("&IDFA=").append(vo.getIdfa());
				} 
				if(StringUtils.isNotBlank(ios.getIdfv())&& StringUtils.isNotBlank(vo.getIdfv())){
					params.append("&").append(ios.getIdfv()).append("=").append(vo.getIdfv());
					callbackUrl.append("&IDFV=").append(vo.getIdfv());
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
				try{
					String k =IOSUtil.sendGet(params.toString());
					ChannelSendLogger logger = new ChannelSendLogger();
					logger.logInfo(params.toString(),k);
					}catch(Exception e){
						log.error( params+"给广告主发送点击出错啦",e);
					}
	}

}