package cn.adwalker.IOSChannel.picker.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.constant.UrlMapping;
import cn.adwalker.IOSChannel.picker.param.ChukongForm;
import cn.adwalker.IOSChannel.picker.param.KuaiYouForm;
import cn.adwalker.IOSChannel.picker.param.YoumiForm;
import cn.adwalker.IOSChannel.picker.service.SupportService;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosActivate;

@Controller
public class CallBackController {
	
	public static final Logger logger = Logger.getLogger(CallBackController.class);
	
	/*
	 * 回调接口单独，因为涉及到签名
	 */
	@Resource
	private SupportService supportService;
	
	@RequestMapping(UrlMapping.COMMON_AINFO_YOUMI)
	public void activateInfo(HttpServletRequest request,HttpServletResponse response,YoumiForm form){
		boolean haserror=false;
		if (form!=null&&!StringUtil.isEmpty(form.getDevice())) {
			IosActivate activate = new IosActivate();
			activate.setDeviceid(form.getDevice().replaceAll(":", ""));
			activate.setAppid("YOUMI_"+form.getAdid());
			if(checkActivate(activate)){
				supportService.saveIosActivate(activate.appid,activate.deviceid);
				outPrintString(response, jsonInfo("success",true).toString());
			}else{
				haserror=true;
			}
		}else {
			haserror=true;
		}
		if (haserror) {
			outPrintString(response, jsonInfo("param error",false).toString());
		}
	}
	
	@RequestMapping(UrlMapping.COMMON_AINFO_CHUKONG)
	public void activateInfo_chukong(HttpServletRequest request,HttpServletResponse response,ChukongForm form){
		boolean haserror=false;
		if (form!=null&&!(StringUtil.isEmpty(form.getMac())&&StringUtil.isEmpty(form.getIdfa()))) {
			IosActivate activate = new IosActivate();
			String mac=form.getMac();
			if (!StringUtil.isEmpty(mac)) {
				mac=mac.replaceAll(":", "");
			}
			activate.setDeviceid(StringUtil.dealNull(mac,form.getIdfa()));
			activate.setAppid("CHUKONG_"+form.getAdid());
			if(checkActivate(activate)){
				supportService.saveIosActivate(activate.appid,activate.deviceid);
				outPrintString(response, jsonInfo("success",true).toString());
			}else{
				haserror=true;
			}
		}else {
			haserror=true;
		}
		if (haserror) {
			outPrintString(response, jsonInfo("param error",false).toString());
		}
	}
	
	
	@RequestMapping(UrlMapping.COMMON_AINFO_KUAIYOU)
	public void activateInfo_kuaiyou(HttpServletRequest request,HttpServletResponse response,KuaiYouForm form){
		boolean haserror=false;
		if (form!=null&&!StringUtil.isEmpty(form.getDevice())&&!StringUtil.isEmpty(form.getAdi())) {
			supportService.saveIosActivate("KUAIYOU_"+form.getAdi(),form.getDevice());
			outPrintString(response, jsonInfo("success",true).toString());
		}else {
			haserror=true;
		}
		if (haserror) {
			outPrintString(response, jsonInfo("param error",false).toString());
		}
	}
	
	
	@RequestMapping(UrlMapping.COMMON_AINFO_WEIXIN)
	public void activateInfo_weixin(HttpServletRequest request,HttpServletResponse response,String idfv,String bundleid){
		boolean haserror=false;
		CampaignConfig c=supportService.findCampaignConfigBybundleid(bundleid);
		String ad_key=c.getAd_key();
		if (!StringUtil.isEmpty(idfv)&&!StringUtil.isEmpty(ad_key)) {
			supportService.saveIosActivate(ad_key,idfv);
			outPrintString(response, jsonInfo("success",true).toString());
		}else {
			haserror=true;
		}
		if (haserror) {
			outPrintString(response, jsonInfo("param error",false).toString());
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
	
	private void outPrintString(HttpServletResponse response,String str){
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
