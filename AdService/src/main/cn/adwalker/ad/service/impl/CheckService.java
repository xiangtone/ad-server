package cn.adwalker.ad.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.exception.ErrorInfo;
import cn.adwalker.ad.exception.ExceptionCode;
import cn.adwalker.ad.logger.Ad_enull;
import cn.adwalker.ad.picker.constants.AppStatus;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.ConfigUtil;
import cn.adwalker.ad.util.Des3;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.util.StringUtil;

@Service("checkService")
public class CheckService {

	private static final Logger log = LoggerFactory.getLogger(CheckService.class);
	public final static int CACHE_TIME = Integer.parseInt(ConfigUtil.getString("cache_time"));
	
	@Resource
	private IDevAppCache devAppCache;
	
	public ErrorInfo androidFilter(HttpServletRequest request){
		String servletPath = request.getServletPath();
		String paramDes=request.getParameter("m");
		if(StringUtil.isEmpty(paramDes)){
			return PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID);
		}else{
			try {
				String param = Des3.decode(paramDes);
				log.info("decode："+servletPath+"?"+ param);
				Map<String, String> paramMap = PublicUtil.parseParameterMap(param);
				String appKey = paramMap.get(AppConstant.P_NAME_APP_KEY);			
				DevApp vo=devAppCache.getCache(appKey);
				Long appId=vo.getId();
				paramMap.put(AppConstant.PARAM_KE_APP_ID, String.valueOf(vo.getId()));
				if(vo==null || StringUtil.isEmpty(vo.getOs())|| !vo.getOs().toUpperCase().equals("ANDROID")){//app应用判断
					log.error(servletPath + "app not exist,appId=" + appId + " appkey="+ appKey);
					return PublicUtil.installErrorVO(ExceptionCode.ERROR_APP_NOT_EXIST);
				}else  {//状态判断
					AppStatus app = AppStatus.fromValue(vo.getStatus());
					if(app==null || !app.adPass){
						log.error(servletPath + "app status error:" + appId + " status="+ vo.getStatus()+" : "+app.toName());
						return PublicUtil.installErrorVO(ExceptionCode.ERROR_APP_NOT_EXIST);
					}else{
						request.setAttribute("AppVO", vo);
						request.setAttribute("paramMap", paramMap);
						return PublicUtil.installErrorVO(ExceptionCode.SUCCESS); 
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Des3 error:"+e.getMessage());
				return PublicUtil.installErrorVO(ExceptionCode.REQUEST_KEY_NOT_MATCH);
			}
		}
	}
	
	public ErrorInfo iosFilter(HttpServletRequest request){
		String servletPath = request.getServletPath();
		String str = request.getParameter(AppConstant.P_NAME_MAP);
		if(StringUtil.isEmpty(str)){
			return PublicUtil.installErrorVO(ExceptionCode.WALL_LOAD_EMPTY);
		}else{
			try {
				str = Des3.decode(str);
				log.info("decode："+servletPath+"?"+ str);
				Map<String, String> paramMap = PublicUtil.parseParameterMap(str);
				//参数中的os，是IOS的系统版本(1.0.7)，在这个地方做一个转换--变成os_version跟平台使用的os跟区分开
				String os_version=paramMap.get("os");
				paramMap.put("os_version", os_version);
				paramMap.put("os","ios");
				String appKey = paramMap.get(AppConstant.P_NAME_APP_KEY);
				DevApp vo =null;
				if (!StringUtil.isEmpty(appKey)) {
					vo=devAppCache.getCache(appKey);
					paramMap.put(AppConstant.PARAM_KE_APP_ID, String.valueOf(vo.getId()));
				}else {
					log.error("appkey error"+appKey);
				}
				
				if(null==vo || vo.getOs() == null || !vo.getOs().toUpperCase().equals("IOS")){//app应用判断
					log.error(servletPath + "app not exist,appId=" +appKey);
					return PublicUtil.installErrorVO(ExceptionCode.ERROR_APP_NOT_EXIST);
				}else {//应用状态判断
					AppStatus app = AppStatus.fromValue(vo.getStatus());
					if(app==null || !app.adPass){//状态判断
						log.error(servletPath + "app status error:" + vo.getId() + " status="+ vo.getStatus()+"  :"+app.toName());
						return PublicUtil.installErrorVO(ExceptionCode.ERROR_APP_NOT_EXIST);
					}else{
						request.setAttribute("AppVO", vo);
						request.setAttribute("paramsMap", paramMap);
						return PublicUtil.installErrorVO(ExceptionCode.SUCCESS); 
					}
				}
			} catch (Exception e1) {
				return PublicUtil.installErrorVO(ExceptionCode.REQUEST_KEY_NOT_MATCH);
			}
		}
	}
	public ErrorInfo portalFilter(HttpServletRequest request){
		String servletPath = request.getServletPath();
		Map<String,String> map=new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> em= request.getParameterNames();
		while (em.hasMoreElements()) {
			String key = (String) em.nextElement();
			String temp[]=request.getParameterValues(key);
			map.put(key, temp[0]);
		}
		if(map==null||map.isEmpty()){
			return PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID);
		}else{
			try {
				
				String appKey = map.get(AppConstant.P_NAME_APP_KEY);
				DevApp vo=devAppCache.getCache(appKey);
				if(vo==null){
					log.error(servletPath + "portal not exist, appkey="+ appKey);
					return PublicUtil.installErrorVO(ExceptionCode.ERROR_APP_NOT_EXIST);
				}else  {
					map.put(AppConstant.PARAM_KE_APP_ID, String.valueOf(vo.getId()));
					AppStatus app = AppStatus.fromValue(vo.getStatus());
					if(app==null || !app.adPass){
						log.error(servletPath + "portal status error:" + appKey + " status="+ vo.getStatus()+" : "+app.toName());
						return PublicUtil.installErrorVO(ExceptionCode.ERROR_APP_NOT_EXIST);
					}else{
						request.setAttribute("AppVO", vo);
						request.setAttribute("paramMap", map);
						return PublicUtil.installErrorVO(ExceptionCode.SUCCESS); 
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Des3 error:"+e.getMessage());
				return PublicUtil.installErrorVO(ExceptionCode.REQUEST_KEY_NOT_MATCH);
			}
		}
	
	}
	
	
	
	
	@Deprecated
	public  ErrorInfo checkIos(HttpServletRequest request) {
		// 请求的url
		String servletPath = request.getServletPath();
		String str = request.getParameter(AppConstant.P_NAME_MAP);
		if (StringUtil.isEmpty(str)) {
			PublicUtil.installErrorVO(ExceptionCode.REQUEST_KEY_NOT_MATCH);
		}
		//log.info("解密前：" + str);
		try {
			str = Des3.decode(str);
			log.info("decode："+servletPath+"?"+ str);
		} catch (Exception e1) {
			PublicUtil.installErrorVO(ExceptionCode.REQUEST_KEY_NOT_MATCH);
			e1.printStackTrace();
			Ad_enull enull = new Ad_enull();
			enull.setTitle("iso-->:" + e1.toString());
			enull.softAd_enull();
			return null;
		}
		Map<String, String> paramMap = PublicUtil.parseParameterMap(str);
		String appId = paramMap.get("appId");
		
		
		// 1.参数非空验证
		if (StringUtil.isEmpty(appId)) {
			log.error(servletPath + "param empty:appId=["+ appId+"]");
			return PublicUtil.installErrorJVO(ExceptionCode.REQUEST_PARAM_INVALID);
		}
		
		
		// 2.参数类型站换验证
		long app_id = 0L;
		if (!NumberUtils.isNumber(appId.trim())) {
			log.error(servletPath + "参数类型站换失败" + " appId=" + appId);
			return PublicUtil.installErrorVO(ExceptionCode.REQUEST_PARAM_INVALID);
		} else {
			app_id = Long.valueOf(appId);
		}


		// 3.判断应用是否有效
		DevApp vo = null;
		try {
			vo=devAppCache.getDevApp(app_id);
		} catch (Exception e) {
			log.error(servletPath + "数据库异常Exception:", e);
			return PublicUtil.installErrorVO(ExceptionCode.SERVER_ERROR);
		}
		
		if (vo == null || vo.getOs() == null
				|| !vo.getOs().toUpperCase().equals("IOS")) {
			vo=new DevApp();
			vo.setId(AppConstant.SELF_DEV_APP_ID);
			vo.setAppkey(AppConstant.SELF_DEV_APP_KEY);
			vo.setDev_id(AppConstant.SELF_DEV_ID);
			log.error(servletPath + "应用不存在，appId=" + appId);
			return PublicUtil
					.installErrorVO(ExceptionCode.ERROR_APP_NOT_EXIST);
			// 判断应用是否被删除
		} else if (vo.getDel() == AppConstant.DELETE_TRUE) {
			log.error(servletPath + "应用状态不正常：" + vo.toString());
			return PublicUtil.installErrorVO(ExceptionCode.ERROR_APP_NOT_EXIST);
			// 应用存在，且没有被删除
		} else {
			// 判断用户状态是否已上线
			if (vo.getStatus() != AppConstant.STATUS_ONLINE) {
				log.error(servletPath + "应用状态不正常：" + vo.toString());
//				vo = new AppVO(RefreshLog4jConstant.SELF_DEV_APP_ID,
//						RefreshLog4jConstant.SELF_DEV_APP_KEY, RefreshLog4jConstant.SELF_DEV_ID);
				log.error(servletPath + "应用状态不正常，启动自己人!appId=" + appId+ " appkey=");
			}
			//log.info(servletPath + "请求验证成功：" + vo.toString());
		}
		request.setAttribute("AppVO", vo);
		request.setAttribute("paramsMap", paramMap);
		return PublicUtil.installErrorVO(ExceptionCode.SUCCESS);
	}

}
