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


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.param.PageParam;
import cn.adwalker.ad.param.WallParam;
import cn.adwalker.ad.param.WallParam2;
import cn.adwalker.ad.picker.util.ParamHandler;
import cn.adwalker.ad.picker.util.VoValicate;
import cn.adwalker.ad.service.IAdWallService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.vo.AdverJson;
import cn.adwalker.ad.vo.OlineAdJson;
import cn.adwalker.ad.vo.OnlineAd;

/**
 * <p>
 * Title: AdController
 * </p>
 * <p>
 * Description:广告
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-1-4
 */
@Controller
public class PageController {
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(PageController.class);
	@Resource
	private IAdWallService adWallService;
	//android 通知中心
	@RequestMapping("/android/notice.do")
	public void androidNotice(HttpServletRequest request,HttpServletResponse response){
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		PageParam pvo = h.paramConvent(PageParam.class,"paramMap");
		if(!h.hasError()&&VoValicate.validatePageVoForAndroid(pvo)){
			AdverJson json = adWallService.getNoticeInfo(pvo,  AppConstant.OS_ANDROID);
			if (json == null) {
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, json);
			}	
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	@RequestMapping("/android/ad_picker.do")
	public void androidAdPicker(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		WallParam pvo = h.paramConvent(WallParam.class,"paramMap");
		if(!h.hasError()&&VoValicate.validatePageVoForAndroid(pvo)){
			AdverJson json = adWallService.adPicker(pvo, AppConstant.OS_ANDROID);
			if (json == null || json.getAdList() == null) {
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, json);
			}	
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	
	//ios通知中心
	@RequestMapping("/ios/notice.do")
	public void iosNotice(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		PageParam pvo = h.paramConvent(PageParam.class,AppConstant.P_PARAM_MAP_KEY);
	    if(!h.hasError() && VoValicate.validatePageVoForIos(pvo) ){
	    	AdverJson json = adWallService.getNoticeInfo(pvo, AppConstant.OS_IOS);
			if (json == null) {
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, json);
			}	
	    }else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	
	//热门推荐墙
	@RequestMapping("/ios/hot.do")
	public void hotTueijian(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		PageParam pvo = h.paramConvent(PageParam.class,AppConstant.P_PARAM_MAP_KEY);
	    if(!h.hasError() && VoValicate.validatePageVoForIos(pvo) ){
	    	AdverJson json = adWallService.hotTueijian(pvo, AppConstant.OS_IOS);
			if (json == null) {
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, json);
			}	
	    }else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	/**
	 * 
	* <p>Title: iosAdPicker</p>
	* <p>Description:IOS广告接口</p>
	* @param request
	* @param response
	* @author cuidd
	* @date 2014年10月30日
	* @return void
	* @version 1.0
	 */
	@RequestMapping("/ios/ad_picker.do")
	public void iosAdPicker(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		WallParam pvo = h.paramConvent(WallParam.class,AppConstant.P_PARAM_MAP_KEY);
	    if(!h.hasError() && VoValicate.validatePageVoForIos(pvo) ){
	    	AdverJson json = adWallService.adPicker(pvo, AppConstant.OS_IOS);
			if (json == null || json.getAdList() == null || json.getAdList().size()==0) {
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, json);
			}	
	    }else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	
	
	@RequestMapping("/ios/ad_list.do")
	public void iosAdList(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		WallParam2 pvo = h.paramConvent(WallParam2.class,AppConstant.P_PARAM_MAP_KEY);
	    if(!h.hasError() && VoValicate.validatePageVoForIos2(pvo) ){
	    	List<OnlineAd>  list = adWallService.adList(pvo, AppConstant.OS_IOS);
	    	OlineAdJson json=new OlineAdJson();
	    	json.setRecodeList(list);
			if (list == null ||list.size()==0) {
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, json);
			}	
	    }else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
}
