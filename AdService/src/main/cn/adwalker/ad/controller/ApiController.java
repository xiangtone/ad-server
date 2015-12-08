package cn.adwalker.ad.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.beans.AdApiData;
import cn.adwalker.ad.param.AdApiParam;
import cn.adwalker.ad.picker.util.BeanToJson;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IAdWallService;
import cn.adwalker.ad.service.IMediaCInfoService;
import cn.adwalker.ad.service.vo.CInfoVo;
import cn.adwalker.ad.util.IpUtil;
import cn.adwalker.ad.vo.V;
import cn.adwalker.core.utils.JacksonMapper;

@Controller
public class ApiController {
	
	
	@Resource
	private IAdWallService adWallService;
	
	
	@Resource
	private	IMediaCInfoService mediaCInfoService;
	
	
	@RequestMapping("/api/adList.do")
	public void list(HttpServletRequest request,HttpServletResponse response,
			AdApiParam param){
		
		/**
		 * 1、判断app_key信息。
		 * 2、选取广告。
		 */
		AdApiData data= adWallService.apiAdPicker(param);
		try {
			System.out.println(JacksonMapper.objectToJsonString(data));
			outPrint(request, response, BeanToJson.beanToJson(data,"placementId").toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	* <p>Title: cinfo</p>
	* <p>Description:获取媒体返回的点击信息发送到api</p>
	* @param request
	* @param response
	* @param param
	* @author cuidd
	* @date 2014年12月5日
	* @return void
	* @version 1.0
	 */
	@RequestMapping("/api/cinfo.do")
	public void cinfo(HttpServletRequest request,HttpServletResponse response,
			String ad_id,
			String device_id,
			String app_key,
			String openudid,
			String idfa,
			String idfv,
			String os_version,
			String dev_user_id,
			String phoneName,
			String go){
		String client_ip=IpUtil.getIpAddr(request);
		//用户初始化(系统用户、媒体用户)
		//发送点击数据
	    CInfoVo vo=new CInfoVo();
	    vo.setApp_key(app_key);
	    vo.setDev_user_id(dev_user_id);
	    vo.setClient_ip(client_ip);
	    vo.setIdfa(idfa);
	    vo.setIdfv(idfv);
	    vo.setAd_id(ad_id);
	    mediaCInfoService.doCinfo(vo,OS.ios);
	    if (!StringUtil.isEmpty(go)&&go.equals("0")) {
	    	String url=adWallService.getAPPStoreUrl(ad_id);
	    	try {
				response.sendRedirect(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			V<String> v=new V<String>();
			v.setData(null);
			v.setSuccess(true);
			try {
				outPrint(request, response, JacksonMapper.objectToJsonString(v));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
