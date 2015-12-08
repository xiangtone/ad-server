package cn.adwalker.ad.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.beans.PortalData;
import cn.adwalker.ad.cache.IDownloadTokenCache;
import cn.adwalker.ad.facade.IWeiXinFacade;
import cn.adwalker.ad.param.AdParam;
import cn.adwalker.ad.picker.util.BeanToJson;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IAdService;
import cn.adwalker.ad.service.IAdWallService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.CookieUtils;
import cn.adwalker.ad.vo.AdDetailIos;
import eu.bitwalker.useragentutils.UserAgent;

@Controller
public class WeiXinController {
	
	
	@Resource
	private IWeiXinFacade weiXinFacade;

	@Resource
	private IAdWallService adWallService;
	
	
	/*
	 *--------------------请求流程
	 * 1、用户监测界面手机微信串号用户信息。
	 * 2、app发送绑定信息的过程中做用户初始话。
	 * 
	 */
	@Resource
	private	IAdService adService;
	
	
	@Resource
	private IDownloadTokenCache downloadTokenCache;
	
	
	@RequestMapping("/weixin/get_token.do")
	public String token(Map<String, Object> model,HttpServletRequest request,HttpServletResponse response,String userId) {
		downloadTokenCache.addCache(userId,String.valueOf(System.currentTimeMillis()));
		return "weixin/index";
	}
	
	
	
	/**
	 * 
	* <p>Title: index</p>
	* <p>Description:TODO</p>
	* @param request
	* @param response
	* @param user_id
	* @param app_id
	* @param uuid
	* @return
	* @author cuidd
	* @date 2014年11月25日
	* @return String
	* @version 1.0
	 */
	@RequestMapping("/weixin/index.do")
	public String index(Map<String, Object> model,HttpServletRequest request,HttpServletResponse response,
			String userId,
			String appkey) {
		String os=getOs(request.getHeader("user-agent"));
		if (StringUtil.isEmpty(userId,appkey,os)) {
			return "500";
		}
		appkey=weiXinFacade.convertAppKey(appkey,os);
		
		Cookie cookie=CookieUtils.getCookieByName(request,"code");
		System.out.println("cuidd----shouye:"+cookie);
		try {
			if (cookie!=null) {
				weiXinFacade.initMediaUser(userId, appkey,2,cookie.getValue());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("cuidd----shouye_over:"+cookie);
		
		
		/*
		 * 1、串号。
		 * 2、cookie中的用户id。
		 * 3、appkey。
		 */
		if (weiXinFacade.checkUser(userId,appkey)!=null) {
			try {
				response.sendRedirect("list.do?userId="+userId+"&appkey="+appkey);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			weiXinFacade.initMediaUser(userId, appkey,1,null);
		}
		
		model.put("code", userId);
		model.put("appkey", appkey);
		model.put("t", System.currentTimeMillis());
		model.put("download_url", StringUtil.encode("itms-services://?action=download-manifest&url="+AppConstant.PLIST_URL));
		return "weixin/index";
	}
	
	
	@RequestMapping("/weixin/download.do")
	public String download(Map<String, Object> model,HttpServletRequest request,HttpServletResponse response,String userId,String appkey,String t,String download_url) {
		String	s=downloadTokenCache.getCache(userId);
		//判断一样的才会去下载，返回的再过来的时候让他不一样就可以了
		if (!StringUtil.isEmpty(s)&&s.equals(t)&&!StringUtil.isEmpty(download_url)) {
			//跳转到下载页面
			model.put("download_url",StringUtil.decode(download_url));
			return "weixin/download";
		}else {
			downloadTokenCache.addCache(userId, t);
			return "weixin/click";
		}
		
	}
	
	
	private String getOs(String ua){
		String os=OS.ios;
		UserAgent agent=new UserAgent(ua);
		if(agent.getOperatingSystem().getName().toLowerCase().contains("android")){
			os=OS.android;
		}
		return os;
		
	}
	
	@RequestMapping("/weixin/plist.do")
	public String plist(Map<String, Object> model,HttpServletRequest request,HttpServletResponse response,String userId,String appkey) {
		
		String os=getOs(request.getHeader("user-agent"));
		String download_url=null;
		
		if (os.equals("ios")) {
			download_url="itms-services://?action=download-manifest&url="+AppConstant.PLIST_URL;
		}else {
			download_url="http://res.adwalker.cn/adres/appxianfeng/AppXianFeng_1.0.1.apk";
		}
		model.put("download_url",download_url);
		return "weixin/plist";
	}
	
	
	@RequestMapping("/weixin/list.do")
	public String list(	Map<String, Object> model,HttpServletRequest request,HttpServletResponse response,String userId,String appkey) {
		
		Cookie code=CookieUtils.getCookieByName(request,"code");
		if (code!=null) {
			System.out.println("cuidd----"+code.getValue());
		}else {
			CookieUtils.addCookie(response, "code", userId, 3600);
		}
		model.put("userId", userId);
		model.put("appkey", appkey);
		model.put("t", System.currentTimeMillis());
		model.put("p_url", StringUtil.encode("http://itunes.apple.com/WebObjects/MZStore.woa/wa/search?clientApplication=Software&e=true&media=software&term="));
		return "weixin/list";
	}
	
	
	@RequestMapping("/agent/ad_picker.do")
	public void portalAdPicker(HttpServletRequest request,HttpServletResponse response,AdParam vo) {
		PortalData data= adWallService.portalAdPicker(vo);
		data.setCode("200");
		try {
			outPrint(request, response, BeanToJson.beanToJson(data,"placementId").toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/weixin/detail.do")
	public String detail(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model,	String adId,String userid) {
		AdDetailIos vo=null;
			try {
				vo = adService.getAdDetail(Long.valueOf(adId),OS.ios,userid);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		model.put("vo", vo);
		return "weixin/detail";
	}
	
	private void outPrint(HttpServletRequest request, HttpServletResponse response,String str) throws IOException{
		response.setContentType("text/html");
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
