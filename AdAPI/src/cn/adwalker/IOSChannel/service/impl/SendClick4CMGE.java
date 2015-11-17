package cn.adwalker.IOSChannel.service.impl;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.adwalker.IOSChannel.logger.ChannelSendLogger;
import cn.adwalker.IOSChannel.service.ISendClickService;
import cn.adwalker.IOSChannel.util.IOSUtil;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.StringUtil;

/**
 * <p>中手游接口对接</p>
 * @author jief
 */
public class SendClick4CMGE implements ISendClickService{
	private static final Logger log=Logger.getLogger(SendClick4CMGE.class);
	private static final String key="417b531b51fbe33c4575f7df3e3cc2ab";
	public static SendClick4CMGE click4cmge=null;
	private SendClick4CMGE(){}
	public static SendClick4CMGE getInstance(){
	   if(click4cmge==null){
		   synchronized (SendClick4CMGE.class) {
			  click4cmge=new SendClick4CMGE();
		   }
		}
		return click4cmge;
	}
	@Override
	public void SendClick(ChannelRequestResult vo, Advertisement_IOS ios) {
		// secret:jfq_yima
		// key:417b531b51fbe33c4575f7df3e3cc2ab
		// 加密串
		DateFormat df = DateUtil.getDateFormat(DateUtil.PARTTERN_TIME);
		String clickTime = df.format(new Date());
		StringBuffer signs = new StringBuffer();
		// 点解url
		StringBuffer params = new StringBuffer();
		// 回调接口 add by jief
		StringBuilder callbackUrl = new StringBuilder(
				ConfigUtil.getString("callback_url"));
		callbackUrl.append("?").append("appid=").append(ios.getAd_key());
		callbackUrl.append("&deviceid=").append(vo.getDeviceId());
		//add by jief 2014-02-19
		String url=ios.getUrl();
		if(url!=null && url.endsWith("&")){
			params.append(ios.getUrl());
		}else{
			params.append(ios.getUrl()).append("?");
		}
//		params.append(ios.getUrl()).append("?");
		if (StringUtils.isBlank(vo.getDeviceId())) {
			params.append(ios.getDeviceid_para()).append("=")
					.append(vo.getIdfa());
			signs.append(vo.getIdfa());
		} else {
			String newMac = StringUtil.formatMac(vo.getDeviceId());
			params.append(ios.getDeviceid_para()).append("=").append(newMac);
			signs.append(newMac);
		}
		if (StringUtils.isNotBlank(ios.getIdfa())) {
			params.append("&").append(ios.getIdfa()).append("=")
					.append(vo.getIdfa());
			callbackUrl.append("&IDFA=").append(vo.getIdfa());
			signs.append("&" + vo.getIdfa());
		}
		params.append("&").append(ios.getAdid_str()).append("=")
				.append(ios.getAd_key());
		
		params.append("&").append(ios.getSourse_str());
		if (StringUtils.isNotBlank(ios.getEventtime_para())) {
			params.append("&").append(ios.getEventtime_para()).append("=")
					.append(clickTime);
		}
		Map<String,String> paramsMap=getParamsMap(ios.getSourse_str());
		signs.append("&" + paramsMap.get("b"));
		signs.append("&" + paramsMap.get("y")); // 授权码
		signs.append("&" + key); // key
		// add by jief 2013-09-03 发送给广告主 openudid 或 idfa 或idfv
		// changed by jief 2013-11-07
		if (StringUtils.isNotBlank(ios.getOpenudid())) {
			params.append("&").append(ios.getOpenudid()).append("=")
					.append(vo.getOpenUDID());
			callbackUrl.append("&OPENUDID=").append(vo.getOpenUDID());
		}
		// changed by jief 2013-11-06

		if (StringUtils.isNotBlank(ios.getIdfv())
				&& StringUtils.isNotBlank(vo.getIdfv())) {
			params.append("&").append(ios.getIdfv()).append("=")
					.append(vo.getIdfv());
			callbackUrl.append("&IDFV=").append(vo.getIdfv());
		}

		if (ios.getClient_ip() != null && !ios.getClient_ip().equals("")) {
			params.append("&").append(ios.getClient_ip()).append("=")
					.append(vo.getClient_ip());
		}
		String sign=IOSUtil.md5s(signs.toString()).get("32").toString();
		params.append("&z=").append(sign);
		// add by jief callback_url 2013-11-20
		if (StringUtil.isNotBlank(ios.getCallback())) {
			params.append("&").append(ios.getCallback()).append("=")
					.append(StringUtil.encode(callbackUrl.toString()));
		}
		System.out.println("hahah"+params.toString());
		try {
			String k = IOSUtil.sendGet(params.toString());
			ChannelSendLogger logger = new ChannelSendLogger();
			logger.logInfo(params.toString(), k);
		} catch (Exception e) {
			log.error("params:" + params + "给中手游发送失败啦", e);
		}
	}
/**
 * 将source 字段转化成map对
 * @param source
 * @return
 */
	public static Map<String,String> getParamsMap(String source){
		Map<String,String> paraMap=new HashMap<String,String>();
		String paraPpairs[]=source.split("&");
		for(String pairs:paraPpairs){
			String param[]=pairs.split("=");
			if(param.length>1){
				paraMap.put(param[0], param[1]);
			}
		}
		return paraMap;
	}
}
