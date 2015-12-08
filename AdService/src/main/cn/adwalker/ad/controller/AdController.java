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
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.param.AdDetailParam;
import cn.adwalker.ad.param.AdDetailParamIos;
import cn.adwalker.ad.picker.util.ParamHandler;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IAdService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.IpUtil;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.vo.AdDetailJson;



/**
 * <p>Title: AdController</p>
 * <p>Description:广告</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-4
 */
@Controller
public class AdController {
	private static final Logger log = LoggerFactory
			.getLogger(AdController.class);
	
	
	/** 依赖注入 **/
	@Resource
	private IAdService adService;
	
	@RequestMapping("/android/ad_detail.do")
	public void androidAdDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		AdDetailParam vo = h.paramConvent(AdDetailParam.class, "paramMap");
		if(!h.hasError()){
			if(!StringUtil.isEmpty(vo.getAdId())){
				try {
					AdDetailJson adDetail = adService.getAdDetail(vo, AppConstant.OS_ANDROID);
					//adService.getAdDetail(vo.adId, vo.version,vo.channel, vo.ip,vo.uuid,vo.appId,vo.terminalType,vo.imsi,AppConstant.OS_ANDROID);
					PublicUtil.returnValue(response, AppConstant.STATUS_OK, adDetail);
				} catch (Exception e) {
					PublicUtil.returnValue(response, AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.SERVER_ERROR));
				}
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
			}
		}else{
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	/**
	 * 
	* <p>Title: iosAdDetail</p>
	* <p>Description:IOS详情页</p>
	* @param request
	* @param response
	* @throws IOException
	* @author cuidd
	* @date 2014年10月16日
	* @return void
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/ios/ad_detail.do")
	public void iosAdDetail(HttpServletRequest request,HttpServletResponse response) throws IOException {
		long start = System.currentTimeMillis();
		
		// 1.接收并解析参数
		Map<String, String> paramsMap = (Map<String, String>) request.getAttribute("paramsMap");
		if (paramsMap==null||paramsMap.size()==0) {
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorVO(ExceptionCode.REQUEST_KEY_NOT_MATCH));
		}
		String ad_Id = paramsMap.get("adId");// 第几页
		String channel = paramsMap.get("channel");// 渠道
		String version = paramsMap.get("version");// 版本
		String ip = IpUtil.getIp(request);
		String uuid = paramsMap.get("uuid");// 终端用户ID
		//add by jief 2013-09-11 for ios7 start
		String os=paramsMap.get("os");
		String openUDID="";
		String idfa="";
		String idfv ="";
		if(version.compareTo(AppConstant.IOSV122)>=0){
			openUDID = paramsMap.get("openudid");
			if(StringUtils.isBlank(openUDID)) openUDID="";
			idfa = paramsMap.get("idfa");
			if(StringUtils.isBlank(idfa)) idfa="";
			idfv = paramsMap.get("idfv");
			if(StringUtils.isBlank(idfv)) idfv="";
		}
		if(StringUtils.isNotBlank(os) && os.compareTo("7")>0 && 
				   version.compareTo(AppConstant.IOSV122)>=0){
			   uuid=openUDID;
		   }else if(null==uuid){
			   uuid = paramsMap.get("mac");
		 }
	    //add by jief 2013-09-11 for ios7 end 
		String appId = paramsMap.get("appId");// 应用ID
		String terminalType = paramsMap.get("terminalType");// 终端类型PAD，MOBIE
		String imsi  =   paramsMap.get("imsi");
		if(imsi==null){
			imsi="";
		}
		//2.参数安全验证
		int adId = 0;
		try {
			adId = Integer.valueOf(ad_Id);
		} catch (Exception e) {
			log.error("参数类型站换失败.ad_Id=" + ad_Id);
			PublicUtil
					.returnValue(
							response,
							AppConstant.STATUS_ERROR,
							PublicUtil
									.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
			
		}
		
		// 3.调用广告墙组件接口
		AdDetailJson adDetail = null;
		try {
			AdDetailParamIos param=new AdDetailParamIos();
			param.setAdId(adId);
			param.setVersion(version);
			param.setChannel(channel);
			param.setIp(ip);
			param.setUuid(uuid);
			param.setAppId(Long.valueOf(appId));
			param.setTerminalType(terminalType);
			param.setImsi(imsi);
			param.setOs(AppConstant.OS_IOS);
			adDetail=adService.getAdDetail(param);
		} catch (Exception e) {
			e.printStackTrace();
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR,
					PublicUtil.installErrorJVO(ExceptionCode.SERVER_ERROR));
			
		}

		// 4.返回值
		PublicUtil.returnValue(response, AppConstant.STATUS_OK, adDetail);
		log.info("/Ad_Detail.do time:"
				+ (System.currentTimeMillis() - start));
	  
	}
}
