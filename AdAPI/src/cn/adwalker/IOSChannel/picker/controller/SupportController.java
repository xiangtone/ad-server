package cn.adwalker.IOSChannel.picker.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.IOSChannel.picker.bean.ChannelBean;
import cn.adwalker.IOSChannel.picker.checker.CheckerExecute;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.constant.UrlMapping;
import cn.adwalker.IOSChannel.picker.service.SupportService;
import cn.adwalker.IOSChannel.picker.util.BeanRequestPicker;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.ParamHandler;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosActivate;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.ad.util.Des3;

@Controller
public class SupportController {
	
	public static final Logger logger = Logger.getLogger(SupportController.class);
	@Resource
	private SupportService supportService;
	
	private String getAction(HttpServletRequest request){
		return StringUtil.dealNull(request.getAttribute("action"));
	}
	/**
	 * @param request
	 * @param response
	 * 广告确认激活接口
	 */
	@RequestMapping(UrlMapping.COMMON_CINFO)
	public void clickInfo(HttpServletRequest request,HttpServletResponse response){
		String source = request.getParameter("source");
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		IosClick click = h.paramConventAlis(IosClick.class, source);
		if(checkIosClick(click)){	
			if(StringUtil.isMac(click.deviceid)){
				click.setMac(StringUtil.dealNull(click.deviceid).length()==12?StringUtil.formatMac(click.deviceid):click.deviceid);
			}else{
				click.setIDFA(click.deviceid);
			}
			if(!StringUtil.isEmpty(click.callback)){
				click.setCallback(StringUtil.decode(click.callback, "utf-8"));
			}			
			click.setDeviceid(click.getDeviceid().replaceAll(":", ""));
			supportService.iosClick(click);
			outPrintString(response, jsonInfo("success",true).toString());
		}else{
			outPrintString(response, jsonInfo("param error",false).toString());
		}
	}
	@RequestMapping(UrlMapping.COMMON_AINFO)
	public void activateInfo(HttpServletRequest request,HttpServletResponse response){
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		IosActivate activate = h.paramConvent(IosActivate.class);
		if(checkActivate(activate)){
			activate.setDeviceid(activate.getDeviceid().replaceAll(":", ""));
			supportService.saveIosActivate(activate.appid,activate.deviceid);
			outPrintString(response, jsonInfo("success",true).toString());
		}else{
			outPrintString(response, jsonInfo("param error",false).toString());
		}
	}
	
	@RequestMapping(UrlMapping.COMMON_MONITOR)
	public void monitor(HttpServletRequest request,HttpServletResponse response,String adId,String adIds,String appkey){
		try{
			if(!StringUtil.isEmpty(appkey,adId)){
				StringBuffer sbuf = new StringBuffer();
				sbuf.append("adId="+adId);
				sbuf.append("&appkey="+appkey);
				sbuf.append("&ac=1");
				sbuf.append("&adIds="+StringUtil.dealNull(adIds));			
				String param = Des3.encode(sbuf.toString()); 
				supportService.callService(param);
				outPrintString(response, "true");
			}else{
				outPrintString(response, "false");
			}
		}catch (Exception e) {
			outPrintString(response, "false");
		}
	}
	
	
	@RequestMapping(UrlMapping.HOUR_CHECK)
	public void hourCheck(HttpServletRequest request,HttpServletResponse response,String dateHour){
		try{
			String checkStr=supportService.hourCheck(dateHour);
			outPrintString(response, checkStr);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(UrlMapping.HOUR_INFO)
	public void hourInfo(HttpServletRequest request,HttpServletResponse response,String adId,String dateHour,String key){
		try{
			if(StringUtil.equals(key, "1qazxxsw23edc")){
				String checkStr=supportService.hourInfo(dateHour);
				outPrintString(response, checkStr);
			}else{
				outPrintString(response, "key wrong");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkActivate(IosActivate activate){
		activate.setDeviceid(StringUtil.dealNull(activate.getDeviceid(), activate.getDevice()));
		activate.setIDFA(StringUtil.dealNull(activate.getIDFA(), activate.getIdfa()));
		if(StringUtil.isEmpty(activate.deviceid) || BaseAttribute.IOS7_MAC.equals(activate.deviceid)){
			activate.setDeviceid(StringUtil.dealNull(activate.IDFA, activate.mac));
		}
		return !StringUtil.isEmpty(activate.appid,activate.deviceid);
	}
	private JSONObject jsonInfo(String message,boolean b){
		JSONObject o = new JSONObject();
		o.accumulate("message", message);
		o.accumulate("success", b);
		return o;
	}
	
	/**
	 * 
	* <p>Title: checkIosClick</p>
	* <p>Description:检查点击</p>
	* @param iosClick
	* @return
	* @author luoyouhua
	* @date 2014年10月22日
	* @return boolean
	* @version 1.0
	 */
	private boolean checkIosClick(IosClick iosClick){
		iosClick.setAppid(StringUtil.dealNull(iosClick.appid,iosClick.appId));
		if(StringUtil.isMac(iosClick.deviceid)){
			iosClick.setMac(StringUtil.dealNull(iosClick.deviceid).length()==12?StringUtil.formatMac(iosClick.deviceid):iosClick.deviceid);
		}
		
		if(StringUtil.isEmpty(iosClick.deviceid) || BaseAttribute.IOS7_MAC.equals(iosClick.deviceid) || BaseAttribute.IOS7_MAC_MAO.equals(iosClick.deviceid)){//ios6
		   	iosClick.setDeviceid(StringUtil.dealEmpty(iosClick.IDFA, iosClick.mac));
		}
		return !StringUtil.isEmpty(iosClick.appid,iosClick.deviceid,iosClick.source);
	}
	/**
	 * @param request
	 * @param response
	 * 渠道请求确认激活
	 */
	@SuppressWarnings("unused")
	@RequestMapping(UrlMapping.PICKER_CHANNEL_CHECK_GET)
	public void channelCheckControllerGet(HttpServletRequest request, HttpServletResponse response){
		long a = System.currentTimeMillis();
		BeanRequestPicker brp = new BeanRequestPicker(request);
	    ChannelBean data = brp.handleAlis(ChannelBean.class);
	    data.setAction(getAction(request));
	    JSONObject o = CheckerExecute.createChecker().excuteChannelParther(data);
	    outPrintString(response, o.toString());
	}
	/**
	 * @param request
	 * @param response
	 * 渠道请求确认接口
	 */
	@RequestMapping(UrlMapping.PICKER_CHANNEL_CHECK_POST)
	public void channelCheckControllerPost(HttpServletRequest request,HttpServletResponse response){
		
	}
	public void outPrintString(HttpServletResponse response,String str){
		try {
			logger.logInfo("responsed:"+str);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.println(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
