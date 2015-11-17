package cn.adwalker.ad.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.bean.PickerConfig;
import cn.adwalker.ad.cache.ITypeCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.Type1;
import cn.adwalker.ad.picker.constants.T;
import cn.adwalker.ad.picker.memcached.PickerCache;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.util.Des3;


@Controller
@RequestMapping("/common")
public class ClientController {
	
	@Resource
	private ITypeCache typeCache;
	@Resource
	private IUserInfoCache userInfoCache;
	
	@RequestMapping(value="/test.do")
	public String client(String url,String param,String key,Model model,HttpServletRequest request){
		try {
			PickerCache.createblackIpMap();
			if("siasdfw203834ldf".equals(key)){
				String str = StringUtil.isEmpty(param)?requestStr(request):param;
				model.addAttribute("m", Des3.encode(str));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:"+url;
	}
	@RequestMapping(value="/typeId.do")
	public String cacheView(String key,String typeId,String os,String uuid,Model model,HttpServletRequest request){
		DevApp app = new  DevApp();
		app.setPlacement(0l);
		T type = T.fromValue(typeId);
		if(type!=null){
			Type1 t = typeCache.getType(StringUtil.dealNull(os,"android"), type.toInteger(), app);
			model.addAttribute("list",t.getAdvertiselist());
		}
		//userInfoCache.replaceUserInfo("406ade3d7772fb388b0100d441a75468");
		model.addAttribute("user", userInfoCache.getUserInfo(uuid));
		model.addAttribute("sign", PickerConfig.getInstance());
		return "adcache";
	}
	
	@SuppressWarnings("unchecked")
	private String requestStr(HttpServletRequest request){
		StringBuffer sbuf = new StringBuffer();
        Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			if(!"url".equals(name)){
				sbuf.append(t+name+"="+value);
				if(b) b=false;
			}
			
		}
		return sbuf.toString();
	}
	
	@RequestMapping(value="/adview.do")
	public String adView(String key,String typeId,String os,Model model,HttpServletRequest request){
		DevApp app = new  DevApp();
		app.setPlacement(0l);
		T type = T.fromValue(typeId);
		if(type!=null){
			Type1 t = typeCache.getType(os, type.toInteger(), app);
			model.addAttribute("list",t);
		}
		model.addAttribute("sign", PickerConfig.getInstance());
		return "/jsp/adcache.jsp";
	}
	@RequestMapping(value="/ptest.do")
	public String portal(Model model,String data){
		//System.out.println("ssssssssss");
		try {
			//System.out.println(data);
			model.addAttribute("map", Des3.encode(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/ios/ad_picker.do";
	}
}
