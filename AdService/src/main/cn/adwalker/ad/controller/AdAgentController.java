/**
 * <p>Title: AdController.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-1-4
 * @version 1.0
 */
package cn.adwalker.ad.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ParamSign;
import cn.adwalker.ad.param.WeixinUserParam;
import cn.adwalker.ad.picker.util.ParamHandler;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.vo.PortalVo;
import cn.adwalker.ad.service.IAdAgentService;
import cn.adwalker.ad.service.IAdWallService;
import cn.adwalker.ad.service.IAppScoreService;
import cn.adwalker.ad.service.IMediaUserService;
import cn.adwalker.ad.service.impl.PortalService;
import cn.adwalker.ad.service.vo.AInfoAndroid;
import cn.adwalker.ad.service.vo.DInfoAndroid;
import cn.adwalker.ad.util.IpUtil;
import cn.adwalker.ad.util.RequestUtils;
import cn.adwalker.ad.vo.AppVersion;
import cn.adwalker.ad.vo.OnlineAd;
import cn.adwalker.ad.vo.V;
import cn.adwalker.core.utils.JacksonMapper;

@Controller
public class AdAgentController {
	
	
	
	@Resource
	private IAdWallService adWallService;
	
	@Resource
	private	IMediaUserService mediaUserService;
	
	@Resource
	private PortalService portalService;
	
	
	@Resource
	private IAppScoreService appScoreService;
	
	@Resource
	private IAdAgentService adAgentService;
	
	@RequestMapping("/agent/olineAdList.do")
	public void onlineAd(HttpServletRequest request,HttpServletResponse response,String userid,String os){
		V<List<OnlineAd>> v=null;
		boolean f=ParamSign.checkUrlSignature(request.getRequestURL().toString()+RequestUtils.getQueryString(request), "adwalker.123456.cn");
	if (!f) {
		v=new V<List<OnlineAd>>();
		v.setMessage("签名错误");
		v.setSuccess(false);
		try {
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		try {
			
			if (!StringUtil.isEmpty(userid)&&!StringUtil.isEmpty(os)) {
				v=adWallService.onlineAd(userid,os);
			}else {
				v=new V<List<OnlineAd>>();
				v.setMessage("参数错误");
				v.setSuccess(false);
			}
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/agent/userInit.do")
	public void userInit(HttpServletRequest request,HttpServletResponse response,WeixinUserParam param){
		V<String> v=new V<String>();
		boolean f=ParamSign.checkUrlSignature(request.getRequestURL().toString()+"?"+request.getQueryString(), "adwalker.123456.cn");
		if (!f) {
			v=new V<String>();
			v.setMessage("签名错误");
			v.setSuccess(false);
			try {
				outPrint(request, response,JacksonMapper.objectToJsonString(v));
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			/*
			 * 1、数据监测校验。
			 * 2、判断数据是否重复。
			 * 3、写入数据，或者更新用户值。
			 */
			if (StringUtil.isEmpty(param.getIdfa())||StringUtil.isEmpty(param.getUserid())) {
				v.setMessage("参数错误!");
				v.setSuccess(false);
			}else {
				mediaUserService.initUser(param);
				v.setMessage("初始化成功!!");
				v.setSuccess(true);
			}
			
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/agent/userInitAndroid.do")
	public void userinit_android(HttpServletRequest request,HttpServletResponse response,
			String userid,
			String android_id,
			String mac_address,
			String phonenum,
			String imei,
			String imsi){
		V<String> v=new V<String>();
		boolean f=ParamSign.checkUrlSignature(request.getRequestURL().toString()+RequestUtils.getQueryString(request), "adwalker.123456.cn");
		if (!f) {
			v=new V<String>();
			v.setMessage("签名错误");
			v.setSuccess(false);
			try {
				outPrint(request, response,JacksonMapper.objectToJsonString(v));
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			/*
			 * 1、数据监测校验。
			 * 2、判断数据是否重复。
			 * 3、写入数据，或者更新用户值。
			 */
			if (StringUtil.isAllEmpty(userid,android_id,mac_address, phonenum, imei,imsi)){
				v.setMessage("参数错误!");
				v.setSuccess(false);
			}else {
				mediaUserService.initUser_android(userid,android_id,mac_address, phonenum, imei,imsi,null);
				v.setMessage("初始化成功!!");
				v.setSuccess(true);
			}
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/agent/dInfoAndroid.do")
	public void d(HttpServletRequest request,HttpServletResponse response,DInfoAndroid log){
		V<String> v=new V<String>();
		boolean f=ParamSign.checkUrlSignature(request.getRequestURL().toString()+"?"+request.getQueryString(), "adwalker.123456.cn");
		if (!f) {
			v=new V<String>();
			v.setMessage("签名错误");
			v.setSuccess(false);
			try {
				outPrint(request, response,JacksonMapper.objectToJsonString(v));
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			adAgentService.logD(log);
			v.setMessage("激活成功!!");
			v.setSuccess(true);
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/agent/dInfo.do")
	public void d(HttpServletRequest request,HttpServletResponse response,
			String bundleid,
			String idfa,
			String userid,
			String adid,
			String udid,
			String openudid,
			String idfv,
			String phoneName){
		V<String> v=new V<String>();
		boolean f=ParamSign.checkUrlSignature(request.getRequestURL().toString()+"?"+request.getQueryString(), "adwalker.123456.cn");
		if (!f) {
			v=new V<String>();
			v.setMessage("签名错误");
			v.setSuccess(false);
			try {
				outPrint(request, response,JacksonMapper.objectToJsonString(v));
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			/*
			 * 1、数据监测校验。
			 * 2、激活信息判断。
			 * 3、发送点击给广告主。
			 * 4、增加积分。
			 */
			if (!StringUtil.isEmpty(bundleid)&&!StringUtil.isEmpty(idfa)&&!StringUtil.isEmpty(userid)&&!StringUtil.isEmpty(adid)) {
				
				/**
				 * 获取ad_id,获取app_id
				 */
				appScoreService.weixinAddScore(userid,Long.valueOf(adid), idfa,IpUtil.getIpAddr(request),udid,
						 openudid,
						 idfv,
						 phoneName);
				
				v.setMessage("接收成功!!");
				v.setSuccess(true);
			}else {
				v.setMessage("参数错误！！");
				v.setSuccess(false);
			}
			
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/agent/cInfo.do")
	public void c(HttpServletRequest request,HttpServletResponse response,String bundleid,String idfa,String  userid,String adid){
		V<String> v=new V<String>();
		boolean f=ParamSign.checkUrlSignature(request.getRequestURL().toString()+"?"+request.getQueryString(), "adwalker.123456.cn");
		if (!f) {
			v=new V<String>();
			v.setMessage("签名错误");
			v.setSuccess(false);
			try {
				outPrint(request, response,JacksonMapper.objectToJsonString(v));
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			/*
			 * 1、数据监测校验。
			 * 2、激活信息判断。
			 * 3、发送点击给广告主。
			 * 4、增加积分。
			 */
			if (!StringUtil.isEmpty(bundleid)&&!StringUtil.isEmpty(idfa)&&!StringUtil.isEmpty(userid)&&!StringUtil.isEmpty(adid)) {
				
				/**
				 * 获取ad_id,获取app_id
				 */
				appScoreService.weixinAddScore(userid,Long.valueOf(adid), idfa, "", null, null, null, null);
				
				v.setMessage("激活成功!!");
				v.setSuccess(true);
			}else {
				v.setMessage("参数错误！！");
				v.setSuccess(false);
			}
			
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@RequestMapping("/agent/appVersion.do")
	public void appversion(HttpServletRequest request,HttpServletResponse response,String userid){
		V<AppVersion> v=new V<AppVersion>();
		boolean f=ParamSign.checkUrlSignature(request.getRequestURL().toString()+RequestUtils.getQueryString(request), "adwalker.123456.cn");
		if (!f) {
			v=new V<AppVersion>();
			v.setMessage("签名错误");
			v.setSuccess(false);
			try {
				outPrint(request, response,JacksonMapper.objectToJsonString(v));
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
		
			v.setMessage("欢迎下载!!");
			v.setSuccess(true);
			v.setData(new AppVersion("1.0.1","http://res.adwalker.cn/adres/AppXianFeng.apk"));
			
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/agent/aInfoAndroid.do")
	public void c_android(HttpServletRequest request,HttpServletResponse response,AInfoAndroid log
			){
		V<String> v=new V<String>();
//		boolean f=ParamSign.checkUrlSignature(request.getRequestURL().toString()+RequestUtils.getQueryString(request), "adwalker.123456.cn");
//		if (!f) {
//			v=new V<String>();
//			v.setMessage("签名错误");
//			v.setSuccess(false);
//			try {
//				outPrint(request, response,JacksonMapper.objectToJsonString(v));
//				return;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		try {
			/*
			 * 1、数据监测校验。
			 * 2、激活信息判断。
			 * 3、增加积分。
			 */
			adAgentService.logA(log);
			v.setMessage("激活成功!!");
			v.setSuccess(true);
			outPrint(request, response,JacksonMapper.objectToJsonString(v));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/portal/motion.do")
	public void portDetail(HttpServletRequest request,HttpServletResponse response){
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		PortalVo vo = h.paramConvent(PortalVo.class,"paramMap");
		portalService.motion(vo);
		try {
			outPrint(request, response, "true");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void outPrint(HttpServletRequest request, HttpServletResponse response,String str) throws IOException{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(str);
		out.flush();
		out.close();
	}
}
