package cn.adwalker.ad.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.param.PayScoreParam;
import cn.adwalker.ad.param.ScoreQueryParam;
import cn.adwalker.ad.picker.param.ActivateIosParam;
import cn.adwalker.ad.picker.util.ParamHandler;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.util.VoValicate;
import cn.adwalker.ad.service.IAppScoreService;
import cn.adwalker.ad.service.IScoreService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.vo.Score;

/**
 *  积分处理接口
 *  caiq
 */
@Controller
public class ScoreController {

	private static final Logger log = LoggerFactory.getLogger(ScoreController.class);
	/** 依赖注入 **/
	@Resource
	private IScoreService scoreService;
	@Resource
	private IAppScoreService appScoreService;
	// 获取积分
	@RequestMapping("/android/get_score.do")
	public void getUuidScore(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		ScoreQueryParam vo = h.paramConvent(ScoreQueryParam.class, "paramMap");
		if(!h.hasError() ){
			if(!StringUtil.isEmpty(vo.getUuid(),vo.getAppId())){
				try {
					Score data  = scoreService.getScore(vo.getUuid(),vo.getAppId(),vo.version);
					PublicUtil.returnValue(response, AppConstant.STATUS_OK, data);
				} catch (AdwalkerException e) {
					PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
					e.printStackTrace();
				}
			}else{
				PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
			}
		}else{
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	// 消耗积分
	@RequestMapping("/android/pay_score.do")
	public void androidPayScore(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		PayScoreParam pvo = h.paramConvent(PayScoreParam.class, "paramMap");
		if(!h.hasError()){
			if(!StringUtil.isEmpty(pvo.getConsumeScore())){
				DevApp vo = (DevApp) request.getAttribute("AppVO");
				Long developerId = vo.getDev_id();// 开发者ID
				try {
					Score data = appScoreService.consumeScore(developerId,pvo.getAppId(),pvo.getUuid(),pvo.getConsumeScore(),null,pvo.getChannel(),pvo.version,pvo.getIp());
					PublicUtil.returnValue(response, AppConstant.STATUS_OK, data);
				} catch (AdwalkerException e) {
					e.printStackTrace();
					PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
				}
			}
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
		
	
	}
	
	// 获取积分
	@RequestMapping("/ios/get_score.do")
	public void iosGetScore(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		ScoreQueryParam vo = h.paramConvent(ScoreQueryParam.class, "paramsMap");
		if(!h.hasError() && VoValicate.validatePageVoForGetScore(vo) ){
			try {
				Score data = appScoreService.getScore(vo.getUuid(), vo.getAppId(), vo.version);
				PublicUtil.returnValue(response, AppConstant.STATUS_OK, data);
			} catch (AdwalkerException e) {
				PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
				e.printStackTrace();
			}
		}else{
			PublicUtil.returnValue(response, AppConstant.STATUS_ERROR, PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}		
    // 消耗积分
	@RequestMapping("/ios/pay_score.do")
	public void iosPayScore(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		PayScoreParam pvo = h.paramConvent(PayScoreParam.class, "paramsMap");
		if(!h.hasError()){
			VoValicate.changeUuid(pvo);
			if(!StringUtil.isEmpty(pvo.getUuid(),pvo.getConsumeScore())){
				DevApp vo = (DevApp) request.getAttribute("AppVO");
				Long developerId = vo.getDev_id();// 开发者ID
				try {
					Score data=appScoreService.consumeScore(developerId, pvo.getAppId(), pvo.getUuid(), pvo.getConsumeScore(), null, pvo.getChannel(), pvo.getChannel(), pvo.getIp());
					PublicUtil.returnValue(response, AppConstant.STATUS_OK, data);
				} catch (AdwalkerException e) {
					PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
					e.printStackTrace();
				}
			}else{
				PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
			}
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	// ios增加积分
	@RequestMapping("/common/activate.do")
	public void addIosScore(HttpServletRequest request,HttpServletResponse response) throws IOException {
		loggerParams(request);
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		ActivateIosParam vo = h.paramConvent(ActivateIosParam.class);
		vo.setUuid(StringUtil.dealNull(vo.uuid,vo.deveiceId));
		if(!StringUtil.isEmpty(vo.appId) && !StringUtil.isEmpty(vo.uuid) && !StringUtil.isEmpty(vo.adId)){
			try {
				appScoreService.iosAddScore(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			PublicUtil.returnValue(response,AppConstant.STATUS_ERROR,PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loggerParams(HttpServletRequest request){
		StringBuffer sbuf = new StringBuffer();
        Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"?":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			sbuf.append(t+name+"="+value);
			if(b) b=false;
		}
		log.info(sbuf.toString());
	}
}
