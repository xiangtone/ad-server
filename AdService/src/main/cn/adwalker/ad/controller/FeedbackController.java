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


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.param.FeedParam;
import cn.adwalker.ad.picker.util.ParamHandler;
import cn.adwalker.ad.picker.vo.UserFeedbackVo;
import cn.adwalker.ad.service.IAdFeedbackService;
import cn.adwalker.ad.service.IHelpService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.vo.AdFeedBackNameJson;

/**
 * Title: FeedbackController
 * Description:广告积分墙反馈控制
 * Company: adwalker
 * @author www.adwalker.cn
 * @date 2013-1-4
 */
@Controller
public class FeedbackController {
	@Resource
	private IAdFeedbackService adFeedBackService;
	
	@Resource
	private IHelpService helpService;
	
	//android保存反馈信息
	@RequestMapping("/android/saveFeedback.do")
	public String androidSaveFeedback(HttpServletRequest request,HttpServletResponse response){
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		UserFeedbackVo pvo = h.paramConvent(UserFeedbackVo.class,"paramMap");		
		if(!h.hasError()){
			int result = helpService.addUserFeedback(pvo);
			if (result > 0) {
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, PublicUtil.installErrorJVO(ExceptionCode.SUCCESS));
			}else{			
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}	
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
		return null;
	}
	
	@RequestMapping("/android/getAdName.do")
	public void androidAdFeedback(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		FeedParam fvo = h.paramConvent(FeedParam.class,"paramMap");
	    if(!h.hasError()){
			AdFeedBackNameJson json = adFeedBackService.adBackFeedPicker(fvo, AppConstant.OS_ANDROID);
			if (json == null || json.getAdList() == null) {
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, json);
			}	
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	
	//ios保存反馈信息
	@RequestMapping("/ios/saveFeedback.do")
	public String iosSaveFeedback(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		UserFeedbackVo fvo = h.paramConvent(UserFeedbackVo.class,"paramsMap");	
		
		if(!h.hasError()){
			int result = helpService.addUserFeedback(fvo);
			if (result > 0) {
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, PublicUtil.installErrorJVO(ExceptionCode.SUCCESS));
			}else{			
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}	
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
		return null;
	}
	
	
	@RequestMapping("/ios/getAdName.do")
	public void iosAdFeedback(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		FeedParam fvo = h.paramConvent(FeedParam.class,"paramsMap"); 
		if(!h.hasError()){
	    	AdFeedBackNameJson json = adFeedBackService.adBackFeedPicker(fvo, AppConstant.OS_IOS);
			if (json == null || json.getAdList() == null || json.getAdList().size()==0) {
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.WALL_LOAD_EMPTY));
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, json);
			}	
	    }else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
}
