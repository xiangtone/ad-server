package cn.adwalker.ad.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.logger.InitBean;
import cn.adwalker.ad.picker.util.ParamHandler;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.util.VoValicate;
import cn.adwalker.ad.picker.vo.InitVo;
import cn.adwalker.ad.service.IScoreService;
import cn.adwalker.ad.service.InitService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.PublicUtil;
import cn.adwalker.ad.util.WallUtils;
import cn.adwalker.ad.vo.InitDataJson;

/**
* <p>Title:初始化</p>
* <p>Description:初始化请求服务</p>
* <p>Company: adwalker</p> 
* @author    caiqiang
* @date       2013-1-5
 */
@Controller
public class InitController {
	private static final Logger log = LoggerFactory.getLogger(InitController.class);
	/** 依赖注入 **/
	@Resource
	private InitService initService;
	@Resource
	private IScoreService scoreService;
	
	@Resource
	private	IUserInfoCache userInfoCache;
	
	/**
	 * 获取初始化参数
	 * @return
	 */
	@RequestMapping("/android/init.do")
	public void getInitData(HttpServletRequest request,HttpServletResponse response) {
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		InitVo iv = h.paramConvent(InitVo.class,"paramMap");
		if(!h.hasError() && VoValicate.validateInitVo(iv)){
			/*
			 * 1、初始化用户数据。
			 * 2、写入日志数据。
			 * 3、更新积分回调相关配置。
			 */
			iv.setImsi(StringUtil.dealNull(iv.getImsi()));//
			InitDataJson data = initService.collectUuidMsg(iv,  AppConstant.OS_ANDROID);
			savaInitLog(iv);
			PublicUtil.returnValue(response, AppConstant.STATUS_OK,data);
			
			if (!StringUtils.isEmpty(iv.devUserId) && !"null".equals(iv.devUserId)) {
				try {
					scoreService.updateConfig(iv.getUuid(), iv.devUserId, StringUtil.dealNull(iv.getAppId()),OS.android);
				} catch (Exception e) {
					e.printStackTrace();
					//PublicUtil.returnValue(response, AppConstant.STATUS_ERROR,PublicUtil.installErrorVO(ExceptionCode.SERVER_ERROR));
				}
			}
		}else{
			log.info("ILLEGAL PARAM!!");
		}
	}
	
	
	/**
	 * 获取IOS初始化参数
	 * @return
	 */
	@RequestMapping("/ios/init.do")
	public String getInitDataIos(HttpServletRequest request,HttpServletResponse response) {
		long a = System.currentTimeMillis();
		ParamHandler h = ParamHandler.createParamHandler(response, request);
		InitVo iv = h.paramConvent(InitVo.class,"paramsMap");
		log.debug((System.currentTimeMillis()-a)+" ms");
		if(!h.hasError() && VoValicate.validateInitVo(iv)){
			//ios7以openudid为设备标识
			if(WallUtils.isIos7(iv.mac,iv.getOs_version()) && iv.verCompareTo(AppConstant.IOSV122)>0){
				iv.setUuid(StringUtil.dealNull(iv.openudid,iv.mac));
			}else{
				iv.setUuid(StringUtil.dealNull(iv.uuid,iv.mac));
			}			
			InitDataJson data = initService.collectUuidMsg(iv, AppConstant.OS_IOS);
			savaInitLog(iv);
			PublicUtil.returnValue(response, AppConstant.STATUS_OK,data);
		}else{
			log.info("ILLEGAL PARAM!!");
		}
		return null;
	}
	

	/**
	 * 
	* <p>Title: 保存初始化日志</p>
	* <p>Description:保存初始化日志</p>
	* @param appId
	* @param channel
	* @param uuid
	* @param yifVersion
	* @author caiqiang
	* @date 2013-1-10
	* @return void
	* @version 1.0
	 */
	private void savaInitLog(InitVo vo){
		InitBean init = new InitBean();
		if(vo.appId!=null){
			init.setAppId(String.valueOf(vo.appId));
		}
		init.setChannel(vo.channel);
		init.setCreatTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		init.setSdkVersion(vo.version);
		init.setUuid(vo.uuid);
		init.setIp(vo.ip);
		if (!StringUtils.isEmpty(vo.uuid)) {
			init.setAreaCode(this.getUserArea(vo.uuid));
		}
		init.setSsid(vo.ssid);
		init.setBssid(vo.bssid);
		init.setPhoneName(vo.phoneName);
		init.setLatitude(vo.latitude);
		init.setLongitude(vo.longitude);
		init.setIdfa(vo.idfa);
		init.logInit();
	}
	
	
	/**
	 * 
	* <p>Title: getUserArea</p>
	* <p>Description:获取用户区域信息</p>
	* @param uuid
	* @return
	* @author cuidd
	* @date 2014年10月10日
	* @return String
	* @version 1.0
	* @throws
	 */
	private String getUserArea(String uuid) {
		String s="全国";
		if (!StringUtil.isEmpty(uuid)) {
			UserInfo userInfo = userInfoCache.getUserInfo(uuid);	// 终端用户信息实体
			if (userInfo != null&&!StringUtils.isEmpty(userInfo.getAreaCode())&&!userInfo.getAreaCode().equals("null")) {
				s=userInfo.getAreaCode();
			}
		}
		
		return s;
	}
}