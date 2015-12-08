/**
 * <p>Title: AdWallServiceImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2014-03-14
 * @version 1.0
 */
package cn.adwalker.ad.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.bean.AppBlack;
import cn.adwalker.ad.beans.AdApiData;
import cn.adwalker.ad.beans.ApiAdInfo;
import cn.adwalker.ad.beans.Page;
import cn.adwalker.ad.beans.PortalAdInfo;
import cn.adwalker.ad.beans.PortalData;
import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.IAppBlackCache;
import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.IMediaUserCache;
import cn.adwalker.ad.cache.ISystemConfigCache;
import cn.adwalker.ad.cache.ITypeCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.ServiceConfig;
import cn.adwalker.ad.cache.element.Type1;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.cache.element.UserScore;
import cn.adwalker.ad.dao.domain.AdActivateNum;
import cn.adwalker.ad.dao.domain.MaterielScore;
import cn.adwalker.ad.dao.domain.MediaUser;
import cn.adwalker.ad.dto.InitUser;
import cn.adwalker.ad.logger.LoggerManager;
import cn.adwalker.ad.param.AdApiParam;
import cn.adwalker.ad.param.AdParam;
import cn.adwalker.ad.param.PageParam;
import cn.adwalker.ad.param.WallParam;
import cn.adwalker.ad.param.WallParam2;
import cn.adwalker.ad.picker.constants.C;
import cn.adwalker.ad.picker.constants.T;
import cn.adwalker.ad.picker.form.AdForm;
import cn.adwalker.ad.picker.form.UserForm;
import cn.adwalker.ad.picker.memcached.AdCache;
import cn.adwalker.ad.picker.portal.PortalEngine;
import cn.adwalker.ad.picker.portal.PortalMatcher;
import cn.adwalker.ad.picker.system.servlet.UuidFireMan;
import cn.adwalker.ad.picker.util.PickerUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.rules.PageEngine;
import cn.adwalker.ad.rules.RulesUtil;
import cn.adwalker.ad.rules.config.ISDKConfig;
import cn.adwalker.ad.rules.config.SignConfig;
import cn.adwalker.ad.rules.template.SDKTemplateFactory;
import cn.adwalker.ad.service.IAdWallService;
import cn.adwalker.ad.util.BeanUtils;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.util.WallUtils;
import cn.adwalker.ad.vo.AdJson;
import cn.adwalker.ad.vo.AdWallPage;
import cn.adwalker.ad.vo.AdverJson;
import cn.adwalker.ad.vo.GeneralJson;
import cn.adwalker.ad.vo.OnlineAd;
import cn.adwalker.ad.vo.SignBean;
import cn.adwalker.ad.vo.V;
import cn.adwalker.core.utils.JacksonMapper;

/**
 * <p>
 * Title: AdWallServiceImpl
 * </p>
 * <p>
 * Description:广告墙服务实现
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-5-27
 */
@Service("adWallService")
public class AdWallServiceImpl implements IAdWallService {

	private static final Logger log = LoggerFactory
			.getLogger(AdWallServiceImpl.class);

	@Resource
	private ISystemConfigCache systemConfigCache;
	@Resource
	private IUserInfoCache userInfoCache;
	@Resource
	private IDevAppCache devAppCache;
	@Resource
	private ITypeCache typeCache;
	@Resource
	private IAppBlackCache appBlackCache;
	@Resource
	private IAdCache adCache;
	
	@Resource
	private	IMediaUserCache mediaUserCache;
	
	

	//通知中心
	public AdverJson getNoticeInfo(PageParam vo,String os){
		/*
		 * 1、获取应用、应用黑名单相关信息。
		 * 2、获取用户信息。
		 * 3、获取SDK版本信息。
		 * 4、获取系统配置信息
		 */
		AdverJson json = new AdverJson();
		//应用
		DevApp app = devAppCache.getDevApp(vo.getAppId());
		AppBlack appBlack = appBlackCache.getAppBlack(app.getId());
		app.setMatchBack(appBlack);// 黑名单
		UserInfo userInfo = userInfoCache.getUserInfo(vo.getUuid(),vo.getAppId());
		if(userInfo==null){
			userInfo=new UserInfo();
		}
		ISDKConfig sdkTemplate = SDKTemplateFactory.getSDKTemplate(vo.version, os,vo.getPage_type(),userInfo.getSignAdRels(),null);
		RulesUtil.createMatchInfo(userInfo, app.getCategory_id(),vo.getTelModel());
		json.setNotice(PickerUtil.getNoticle(userInfo,vo.getAppId()));
		json.setSign(sdkTemplate.getSign());
		ServiceConfig cf=systemConfigCache.getSystemConfig();
		//足迹
		if(!StringUtil.equals(C.hot, vo.getNoticeType())){
			Type1 jifenType = AdCache.newInstance().getType(os, T.jifen.toInteger(), app.getPlacement());
			List<Advertise> jifenList = RulesUtil.getAdListByTrack(jifenType.getAdvertiselist(), userInfo, T.jifen);
			AdForm form=new AdForm();
			form.setList(jifenList);
			form.setPageNo(vo.getPageNo());
			form.setPageSize(vo.getPageSize());
			form.setPage_type(vo.getPage_type());
			form.setConfig(sdkTemplate);
			form.setApp(app);
			form.setVersion(vo.getVersion());
			form.setImage_type(vo.getImage_type());
			form.setQuickly_task_config(cf.getQuickly_task());
			form.setQuickly_task(vo.getQuickly_task());
			PageEngine page=sdkTemplate.initPageEngine(form);
			jifenList = page.getList();
			json.setWallPage(page.getWallPage());
			json.setRecodeList(getAdJsonList(jifenList, T.jifen.toString(),vo.getAppId(),null));
		}else if(!StringUtil.equals(C.track, vo.getNoticeType())){
			//热门推荐
			Type1 tueijianType = typeCache.getType(os, T.tueijian.toInteger(), app);
			vo.setIsSign(0);
			Map<String,String> ruleMap=RulesUtil.createRuleMap(app.getMatchBlack(), app.getPlacement(), vo.getIsSign(), C.LOGIC_TRUE, userInfo);
			List<Advertise> tueijianList = sdkTemplate.matchAdListByRules(tueijianType.getAdvertiselist(),ruleMap,vo.getMac());
			tueijianList=sdkTemplate.adSorter(tueijianList,vo.getIsSign());
			AdForm form=new AdForm();
			form.setList(tueijianList);
			form.setPageNo(vo.getPageNo());
			form.setPageSize(vo.getPageSize());
			form.setPage_type(vo.getPage_type());
			form.setConfig(sdkTemplate);
			form.setApp(app);
			form.setVersion(vo.getVersion());
			form.setImage_type(vo.getImage_type());
			form.setQuickly_task_config(cf.getQuickly_task());
			form.setQuickly_task(vo.getQuickly_task());
			PageEngine pagetj=sdkTemplate.initPageEngine(form);
			tueijianList = pagetj.getList();
			json.setWallPage(pagetj.getWallPage());
			json.setAdList(getAdJsonList(tueijianList, T.tueijian.toString(),vo.getAppId(),null));
		
			//logger
			String areaCode = userInfoCache.getAreaCode(vo.getUuid());
			tueijianType.setId(T.hot.toInteger());
			LoggerManager.loggerTypeInfo(tueijianType.getId(), vo.getAppId(), vo.version, vo.getChannel(), vo.getUuid(), areaCode, vo.ip,vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa());
			for (Advertise ad : tueijianList) {  
				LoggerManager.logAdPv(ad, vo.getAppId(), T.hot.toString(), vo.getChannel(), vo.getUuid(), areaCode,vo.getIp(), vo.version,vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa());
			}
		}
		return json;
	}
	
	
	
	/**
	 * (non-Javadoc)
	* <p>Title: hotTueijian</p>
	* <p>Description:获取热荐广告</p>
	* @param vo
	* @param os
	* @return
	* @see cn.adwalker.ad.service.IAdWallService#hotTueijian(cn.adwalker.ad.param.PageParam, java.lang.String)
	 */
	@Override
	public AdverJson hotTueijian(PageParam vo,String os){
		AdverJson json = new AdverJson();
		DevApp app = devAppCache.getDevApp(vo.getAppId());
		AppBlack appBlack = appBlackCache.getAppBlack(app.getId());
		app.setMatchBack(appBlack);// 黑名单
		UserInfo userInfo = userInfoCache.getUserInfo(vo.getUuid(),vo.getAppId());
		if(userInfo==null){userInfo=new UserInfo();}
		ISDKConfig sdkTemplate = SDKTemplateFactory.getSDKTemplate(vo.version, os,vo.getPage_type(),userInfo.getSignAdRels(),null);
		RulesUtil.createMatchInfo(userInfo, app.getCategory_id(),vo.getTelModel());
		
		Type1 tueijianType = typeCache.getType(os, T.tueijian.toInteger(), app);
		vo.setIsSign(0);
		Map<String,String> ruleMap=RulesUtil.createRuleMap(app.getMatchBlack(), app.getPlacement(), vo.getIsSign(), C.LOGIC_TRUE, userInfo);
		List<Advertise> tueijianList = sdkTemplate.matchAdListByRules(tueijianType.getAdvertiselist(),ruleMap,vo.getMac());
		tueijianList=sdkTemplate.adSorter(tueijianList,vo.getIsSign());
		ServiceConfig cf=systemConfigCache.getSystemConfig();
		AdForm form=new AdForm();
		form.setList(tueijianList);
		form.setPageNo(vo.getPageNo());
		form.setPageSize(vo.getPageSize());
		form.setPage_type(vo.getPage_type());
		form.setConfig(sdkTemplate);
		form.setApp(app);
		form.setVersion(vo.getVersion());
		form.setImage_type(vo.getImage_type());
		form.setQuickly_task_config(cf.getQuickly_task());
		form.setQuickly_task(vo.getQuickly_task());
		PageEngine pagetj=sdkTemplate.initPageEngine(form);			
		//String page_type,String version,String image_type,
		tueijianList = pagetj.getList();
		this.getAdverJson(json,pagetj.getWallPage(),sdkTemplate.getSign(),T.tueijian.toString(),userInfo.getUserScore(vo.getAppId()),tueijianList,userInfo.getSignAdRels(),vo.getAppId(),vo.getQuickly_task());
	
		//logger
		String areaCode = userInfoCache.getAreaCode(vo.getUuid());
		tueijianType.setId(T.hot.toInteger());
		LoggerManager.loggerTypeInfo(tueijianType.getId(), vo.getAppId(), vo.version, vo.getChannel(), vo.getUuid(), areaCode, vo.ip,vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa());
		for (Advertise ad : tueijianList) {
			LoggerManager.logAdPv(ad, vo.getAppId(), T.hot.toString(), vo.getChannel(), vo.getUuid(), areaCode,vo.getIp(), vo.version,vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa());
	    }
		return json;
	}
	
	//--------------------------------------------
	/**
	 * (non-Javadoc)
	* <p>Title: adPicker</p>
	* <p>Description:广告接口</p>
	* @param vo
	* @param os
	* @return
	* @see cn.adwalker.ad.service.IAdWallService#adPicker(cn.adwalker.ad.param.PageParam, java.lang.String)
	 */
	@Override
	public AdverJson adPicker(WallParam vo,String os){
		AdverJson json = new AdverJson();
		DevApp app = devAppCache.getDevApp(vo.getAppId());
		AppBlack appBlack = appBlackCache.getAppBlack(app.getId());
		app.setMatchBack(appBlack);// 黑名单
		if(!StringUtil.isEmpty(vo.getPage_type()) && !StringUtil.isEmpty(app.getTypeIds()) && vo.getPage_type().matches(app.getTypeIds())){
			UserInfo userInfo =this.initUser(new InitUser(vo.getOs(),
					app.getCategory_id(), 
					vo.getPage_type(),
					vo.getMac(),
					vo.getOpenudid(),
					vo.getAppId(),
					vo.getOs_version() ,
					vo.getChannel(),
					vo.getDevUserId(),
					vo.getIdfa(),
					vo.getIp(),
					vo.getTelModel(),vo.getOperator(),vo.getTerminalType(),vo.getVersion()));//初始化用户信息
			List<UserAdRel> userAdRels=userInfo.getSignAdRels();
			ISDKConfig config = SDKTemplateFactory.getSDKTemplate(vo.version, os,vo.getPage_type(),userAdRels,null);
			int page_type=Integer.parseInt(vo.getPage_type());
			//1:获取广告
			List<Advertise> list=this.getAllAd(os, page_type, app);
			
			Map<String,String> ruleMap=RulesUtil.createRuleMap(app.getMatchBlack(), app.getPlacement(), vo.getIsSign(), C.LOGIC_FALSE, userInfo);
			//2:匹配广告---广告过滤
			List<Advertise> advertiseList =config.matchAdListByRules(list,ruleMap,vo.getMac());	
			
			advertiseList=WallUtils.adFilter(advertiseList, systemConfigCache.getSystemConfig(),app.getAd_res(),vo.getQuickly_task());
			
			//广告排重
			advertiseList=config.removeRepeate(advertiseList);
			
			//广告排序
			advertiseList=config.adSorter(advertiseList, vo.getIsSign());
			//3:选取广告---广告分页
			ServiceConfig cf=systemConfigCache.getSystemConfig();
			AdForm form=new AdForm();
			form.setList(advertiseList);
			form.setPageNo(vo.getPageNo());
			form.setPageSize(vo.getPageSize());
			form.setPage_type(vo.getPage_type());
			form.setConfig(config);
			form.setApp(app);
			form.setVersion(vo.getVersion());
			form.setImage_type(vo.getImage_type());
			form.setQuickly_task_config(cf.getQuickly_task());
			form.setQuickly_task(vo.getQuickly_task());
			PageEngine page=config.initPageEngine(form);
			//String page_type,String version,String image_type,
			advertiseList = page.getList();
			this.getAdverJson(json,page.getWallPage(),config.getSign(),vo.getPage_type(),userInfo.getUserScore(vo.getAppId()),advertiseList,userAdRels,vo.getAppId(),vo.getQuickly_task());
			//4:广告展示日志logger
			StringBuilder b=new StringBuilder();
			for (Advertise a:advertiseList) {
				b.append(",").append(a.getId()+"--***"+a.getFast_task()+"-----"+a.getAd_name());
				
			}
			this.adLog(page_type, vo, advertiseList);
		}else{
			json.setAdList(null);
			log.error(vo.getPage_type()+" no match "+app.getTypeIds());
		}
		return json;
	}
	
	private AdverJson getAdverJson(AdverJson json,AdWallPage adWallPage, SignBean signBean,String page_type, UserScore userScore, List<Advertise> advertiseList, List<UserAdRel> userAdRels,Long app_id,String quickly_task){
		List<AdJson> adJsons=getAdJsonList(advertiseList, page_type,app_id,quickly_task);
		json.setWallPage(adWallPage);
		json.setAdList(adJsons);
		json.setSign(signBean);
		//积分墙
		T pageType = T.fromValue(page_type);
		if (pageType==T.jifen) {
			json.setTotalInteger(userScore!=null?userScore.getScore():0);
			json.setSign(SignConfig.getInstance().getSignBean());
			json.setNoticeNum(RulesUtil.getAdListByNotice(advertiseList,userAdRels));
		}
		return json;
	}
	
	
	/**
	 * 
	* <p>Title: getAllAd</p>
	* <p>Description:获取广告</p>
	* @param os
	* @param page_type
	* @param app
	* @return
	* @author cuidd
	* @date 2014年10月16日
	* @return List<Advertise>
	* @version 1.0
	 */
	private List<Advertise> getAllAd(String os,int page_type,DevApp app){
		List<Advertise> list=null;
		Type1 type = typeCache.getType(os, page_type, app);
		if (type!=null) {
			list=type.getAdvertiselist();
		}else {
			list=new ArrayList<Advertise>();
		}
		return list;
	}
	
	
	/**
	 * 
	* <p>Title: initUser</p>
	* <p>Description:获取用户信息，如果有没初始化重新初始化</p>
	* @param vo
	* @param os
	* @return
	* @author cuidd
	* @date 2014年10月16日
	* @return UserInfo
	* @version 1.0
	 */
	private UserInfo initUser(InitUser user){
		UserInfo userInfo=null;
		if (T.fromValue(user.getPage_type())==T.jifen) {
			userInfo=userInfoCache.getUserInfo(user.getUuid(),user.getAppId());
		}else {
			userInfo =userInfoCache.getUserInfo(user.getUuid());
		}
		if(userInfo==null){
			userInfo=new UserInfo();
			UuidFireMan.getInstance().submit(
					new UserForm(user.getAppId(), 
							user.getChannel(),
							user.getDevUserId(),
							user.getIdfa(),
							user.getIp(),
							user.getMac(),
							user.getUuid(),
							user.getTelModel(),
							user.getOs(),
							user.getOpenudid(),
							user.getOperator(),
							user.getTerminalType(),user.getVersion()));
		}
		RulesUtil.createMatchInfo(userInfo,user.getApp_catalog_id(),user.getTelModel());
		return userInfo;
	}
	
	
	
	private UserInfo initUser(String uuid,Long appid,String page_type,Long app_catalog_id){
		UserInfo userInfo=null;
		if (T.fromValue(page_type)==T.jifen) {
			userInfo=userInfoCache.getUserInfo(uuid,appid);
		}else {
			userInfo =userInfoCache.getUserInfo(uuid);
		}
		if(userInfo!=null){
			RulesUtil.createMatchInfo(userInfo,app_catalog_id,userInfo.getTelModel());
		}
		return userInfo;
	}
	
	
	@Override
	public PortalData portalAdPicker(AdParam param){
		PortalData data = new PortalData();
		DevApp app = devAppCache.getCache(param.getAppkey());
		AppBlack appBlack = appBlackCache.getAppBlack(app.getId());
		app.setMatchBack(appBlack);// 黑名单
		Integer page_type=0;
		
		UserInfo userInfo = new UserInfo();
		MediaUser mediaUser=mediaUserCache.getMediaUser(param.getUserId(),app.getId());
		userInfo=userInfoCache.getUserInfo(mediaUser.getUuid(),Long.valueOf(mediaUser.getApp_id()));
		String os = StringUtil.equals(OS.android, StringUtil.dealNull(app.getOs()).toLowerCase())?OS.android:OS.ios;
		RulesUtil.createMatchInfo(userInfo, app.getCategory_id(),null);
		//获取广告
		Type1 t = typeCache.getType(os,page_type, app);
		ISDKConfig config = SDKTemplateFactory.getSDKTemplate("", os,String.valueOf(page_type),userInfo.getSignAdRels(),null);
		List<Advertise> list=t.getAdvertiselist();
		WallUtils.adFilter(list, systemConfigCache.getSystemConfig(), app.getAd_res());
		
		data.setPage(Page.createPage(0, t.getAdvertiselist().size()));
        //匹配广告
		list =PortalMatcher.matcherAdvertise(t.getAdvertiselist(), userInfo, app);
		//选取广告
		PortalEngine pe=PortalEngine.createPage(list, param.getPageNo(), param.getPageSize(), String.valueOf(page_type));
		List<Advertise> showList =pe.fatchPage();
		
		showList=config.fatch(showList, app.getCurrency(), app.getScale());
		data.setList(convertAdInfo(showList,userInfo,null,app.getOs()));
		
		//记录日志
		//LoggerManager.loggerTypeInfo(t, param.appId, param.version, param.channel, param.uuid, param.areaCode, param.ip);
		LoggerManager.loggerTypeInfo(t.getId(), app.getId(), param.getVersion(), "weixin", mediaUser.getUuid(), "全国", param.ip, null,null,"PhoneName", null, null,userInfo.getIdfa());
		for (Advertise ad : showList) {//latitude,longitude,ssid,bssid 
			LoggerManager.logAdPv(ad, app.getId(),String.valueOf(page_type),"weixin", userInfo.getUuid(),"全国",param.getIp(), param.getVersion(),null,null,null,null,null,userInfo.getIdfa());
		}
		return data;
	}
	

	private List<PortalAdInfo> convertAdInfo(List<Advertise> l,UserInfo u,String version,String os){
		try {
			System.out.println(JacksonMapper.objectToJsonString(l));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     List<PortalAdInfo> list = new ArrayList<PortalAdInfo>();
	     PortalAdInfo pad =null;
	     for(Advertise ad:l){
	    	 pad =new PortalAdInfo();
	    	 pad.setAd_id(ad.getId());
	    	 pad.setIcon_url(ad.getIcon_url());
	    	 pad.setCreate_date(DateUtil.format(ad.getCreate_time()));
	    	 pad.setAd_name(ad.getAd_name());
	    	 pad.setSlogan(ad.getSlogan());
	    	
	    	 if (os.equals(OS.ios)) {
	    		 pad.setClick_url(ad.getAd_url());
		    	 pad.setStore_url(ad.getApp_url());
			}else if (os.equals(OS.android)) {
				pad.setClick_url(ad.getPackageInfo().getRes_url());
				pad.setStore_url(ad.getPackageInfo().getRes_url());
			}
	    	 pad.setKeyword(ad.getKeyword());
	    	 MaterielScore materielScore=(MaterielScore) ad.getWall();
	    	 pad.setTask_desc(materielScore.getScore_desc());
	    	 pad.setScore(ad.getShow_score()!=null?ad.getShow_score():0);
	    	 pad.setScore_unit(ad.getScoreUnit());
	    	 pad.setResponse_type(materielScore.getResponse_type());
	    	 pad.setWeixin_desc(materielScore.getWeixin_desc());
	    	 list.add(pad);
	    
	     }
	     return list;
	}
	
	private List<ApiAdInfo> convertApiAdInfo(List<Advertise> l,UserInfo u,String os,String mac,String idfa,String udid){
		try {
			System.out.println(JacksonMapper.objectToJsonString(l));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     List<ApiAdInfo> list = new ArrayList<ApiAdInfo>();
	     ApiAdInfo pad =null;
	     for(Advertise ad:l){
	    	 pad =new ApiAdInfo();
	    	 pad.setAd_id(ad.getId());
	    	 pad.setIcon_url(ad.getIcon_url());
	    	 pad.setCreate_date(DateUtil.format(ad.getCreate_time()));
	    	 pad.setAd_name(ad.getAd_name());
	    	 pad.setSlogan(ad.getSlogan());
	    	
	    	 if (os.equals(OS.ios)) {
	    		 pad.setClick_url(getClickUrl(String.valueOf(ad.getId()), mac, idfa, udid));
		    	 pad.setStore_url(ad.getApp_url());
			}
	    	 
	    	 MaterielScore materielScore=(MaterielScore) ad.getWall();
	    	 pad.setTask_desc(materielScore.getScore_desc());
	    	 pad.setScore(ad.getShow_score()!=null?ad.getShow_score():0);
	    	 pad.setScore_unit(ad.getScoreUnit());
	    	 list.add(pad);
	    
	     }
	     return list;
	}
	
	
	/**
	 * 
	* <p>Title: adLog</p>
	* <p>Description:广告日志</p>
	* @param type
	* @param vo
	* @param advertiseList
	* @author cuidd
	* @date 2014年10月10日
	* @return void
	* @version 1.0
	* @throws
	 */
	private void adLog(int type_id,WallParam vo,List<Advertise> advertiseList){
		String areaCode = userInfoCache.getAreaCode(vo.getUuid());
		LoggerManager.loggerTypeInfo(type_id,vo.getAppId(), vo.version, vo.getChannel(), vo.getUuid(), areaCode, vo.ip, vo.getSsid(), vo.getBssid(), vo.getPhoneName(), vo.getLatitude(), vo.getLongitude(), vo.getIdfa());
		for (Advertise ad : advertiseList) { //ssid,bssid,phoneName,latitude,longitude,idfa
			try {
				LoggerManager.logAdPv(ad, vo.getAppId(), vo.getPage_type(), vo.getChannel(), vo.getUuid(), areaCode,vo.getIp(), vo.version,vo.getSsid(),vo.getBssid(),vo.getPhoneName(),vo.getLatitude(),vo.getLongitude(),vo.getIdfa());
			} catch (Exception e) {
				e.toString();
			}
			
		}
		
	}
	
	
	/**
	 * 
	* <p>Title: getAdJsonList</p>
	* <p>Description:对象转换</p>
	* @param adList
	* @param typeId
	* @param appId
	* @return
	* @author cuidd
	* @date 2014年10月31日
	* @return List<AdJson>
	* @version 1.0
	 * @param fast_task 
	 */
	private  List<AdJson> getAdJsonList(List<Advertise> adList,
			String typeId,Long appId, String fast_task){
		ServiceConfig config = systemConfigCache.getSystemConfig();
		List<AdJson> list=new ArrayList<AdJson>();
		AdJson json=null;
		for (Advertise ad:adList) {
			json=BeanUtils.getAdJsonList(ad, typeId, config.getBanner_interval(),appId,config.getScore_delay_time(), config.getScore_explain());
			if (ad.getOs().equals(OS.ios)&&ad.getType_id()==0&&!StringUtil.isEmpty(fast_task)&&fast_task.equals("1")) {
				AdActivateNum adActivateNum=adCache.getAdActivateNum(String.valueOf(ad.getId()));
				GeneralJson g=json.getGeneral();
				g.setAd_surplus((ad.getBudget_day()-adActivateNum.getActivate_num()>0)?(ad.getBudget_day()-adActivateNum.getActivate_num()):0);
				g.setLimit_time(""+DateUtil.distenceHour(ad.getEnd_time()!=null?ad.getEnd_time():DateUtil.getDayEnd(new Date()), new Date()));
			}
			list.add(json);
		}
		return	list;
	}



	@Override
	public V<List<OnlineAd>>  onlineAd(String userid,String os) {
		/*
		 * 1、根据用户找到app_id。
		 * 2、根据app获取广告
		 */
		V<List<OnlineAd>> v=new V<List<OnlineAd>>();
		MediaUser user=mediaUserCache.getMediaUser(userid,os);
		if (user==null) {
			v.setData(null);
			v.setMessage("用户不存在！！");
			v.setSuccess(false);
			return v;
		}
		
		
		UserInfo userInfo=userInfoCache.getUserInfo(user.getUuid(),Long.valueOf(user.getApp_id()));
		if (userInfo==null) {
			v.setData(null);
			v.setMessage("用户不存在，请先初始化用户！！");
			v.setSuccess(false);
			return v;
		}
		List<OnlineAd> list=new ArrayList<OnlineAd>();
		DevApp devApp=devAppCache.getDevApp(Long.valueOf(user.getApp_id()));
		Type1 type = typeCache.getType(os, T.jifen.toInteger(), devApp);
		List<Advertise> adList=type.getAdvertiselist();
		adList=WallUtils.adFilter(adList, systemConfigCache.getSystemConfig(),devApp.getAd_res(), null);
		adList=PortalMatcher.matcherAdvertise(adList, userInfo, devApp);
		OnlineAd vo =null;
		for(Advertise a:adList){
			vo=new OnlineAd();
			vo.setAd_name(a.getAd_name());
			MaterielScore wall=(MaterielScore) a.getWall();
			vo.setLimit_time(wall.getLimit_time());
			vo.setAd_id(String.valueOf(a.getId()));
			vo.setAd_mark(a.getAd_mark());
			list.add(vo);
		}
		v.setData(list);
		v.setSuccess(true);
		return v;
	}



	@Override
	public AdApiData apiAdPicker(AdApiParam param) {
		AdApiData data = new AdApiData();
		DevApp app = devAppCache.getCache(param.getApp_key());
		AppBlack appBlack = appBlackCache.getAppBlack(app.getId());
		app.setMatchBack(appBlack);// 黑名单
		Integer page_type=0;
		UserInfo userInfo = new UserInfo();
		
		String os = StringUtil.equals(OS.android, StringUtil.dealNull(app.getOs()).toLowerCase())?OS.android:OS.ios;
		RulesUtil.createMatchInfo(userInfo, app.getCategory_id(),null);
		//获取广告
		Type1 t = typeCache.getType(os,page_type, app);
		data.setPage(Page.createPage(0, t.getAdvertiselist().size()));
        //匹配广告
		List<Advertise> list =PortalMatcher.matcherAdvertise(t.getAdvertiselist(), app);
		//选取广告
		PortalEngine pe=PortalEngine.createPage(list, param.getPageNo(), param.getPageSize(), String.valueOf(page_type));
		List<Advertise> showList =pe.fatchPage();
		ISDKConfig config = SDKTemplateFactory.getSDKTemplate("", os,String.valueOf(page_type),userInfo.getSignAdRels(),null);
		showList=config.fatch(showList, app.getCurrency(), app.getScale());
		data.setData(convertApiAdInfo(showList,userInfo,app.getOs(),param.getMac(),	param.getIdfa(),param.getUdid()));
		
		//记录日志
		//LoggerManager.loggerTypeInfo(t, param.appId, param.version, param.channel, param.uuid, param.areaCode, param.ip);
		LoggerManager.loggerTypeInfo(t.getId(), app.getId(), param.getVersion(), "weixin",UUID.randomUUID().toString(), "全国", param.ip, null,null,"PhoneName", null, null,userInfo.getIdfa());
		for (Advertise ad : showList) {//latitude,longitude,ssid,bssid 
			LoggerManager.logAdPv(ad, app.getId(),String.valueOf(page_type),"weixin", userInfo.getUuid(),"全国",param.getIp(), param.getVersion(),null,null,null,null,null,userInfo.getIdfa());
		}
		return data;
	}
	
	private String getClickUrl(String ad_id,String mac,String idfa,String udid){
		StringBuilder sb=new StringBuilder("http://i.adwalker.cn/AdService/api/cinfo.do");
		if (!StringUtil.isEmpty(ad_id)) {
			sb.append("?ad_id="+ad_id);
			if (!StringUtil.isEmpty(mac)) {
				sb.append("&mac="+mac);
			}
			
			if (!StringUtil.isEmpty(idfa)) {
				sb.append("&idfa="+idfa);
			}
			
			if (!StringUtil.isEmpty(udid)) {
				sb.append("&udid="+udid);
			}
		}
		
		return sb.toString();
	}

	@Override
	public String getAPPStoreUrl(String ad_id) {
		Advertise ad=adCache.getAdv(Long.valueOf(ad_id));//广告
		return ad.getPackageInfo().getRes_url();
		
	}



	@Override
	public List<OnlineAd> adList(WallParam2 vo, String os) {
		Integer  isSign=0;
		List<OnlineAd> resultList=new ArrayList<OnlineAd>();
		DevApp app = devAppCache.getDevApp(vo.getAppId());
		AppBlack appBlack = appBlackCache.getAppBlack(app.getId());
		app.setMatchBack(appBlack);// 黑名单
		if(!StringUtil.isEmpty(vo.getPage_type()) && !StringUtil.isEmpty(app.getTypeIds()) && vo.getPage_type().matches(app.getTypeIds())){
			UserInfo userInfo =this.initUser(vo.getUuid(), vo.getAppId(), vo.getPage_type(), app.getCategory_id());
			List<UserAdRel> userAdRels=userInfo.getSignAdRels();
			ISDKConfig config = SDKTemplateFactory.getSDKTemplate(vo.version, os,vo.getPage_type(),userAdRels,null);
			int page_type=Integer.parseInt(vo.getPage_type());
			//1:获取广告
			List<Advertise> list=this.getAllAd(os, page_type, app);
			
			Map<String,String> ruleMap=RulesUtil.createRuleMap(app.getMatchBlack(), app.getPlacement(),isSign, C.LOGIC_FALSE, userInfo);
			//2:匹配广告---广告过滤
			List<Advertise> advertiseList =config.matchAdListByRules(list,ruleMap,vo.getMac());	
			
			advertiseList=WallUtils.adFilter(advertiseList, systemConfigCache.getSystemConfig(),app.getAd_res(),vo.getQuickly_task());
			
			//广告排重
			advertiseList=config.removeRepeate(advertiseList);
			
			//广告排序
			advertiseList=config.adSorter(advertiseList,isSign);
			//3:选取广告---广告分页
			ServiceConfig cf=systemConfigCache.getSystemConfig();
		
			config.fatch(advertiseList, vo.getVersion(), null,cf.getQuickly_task(), vo.getQuickly_task(),app.getCurrency(),app.getScale());
			OnlineAd ad =null;
			for(Advertise a:advertiseList){
				ad=new OnlineAd();
				ad.setAd_name(a.getAd_name());
				MaterielScore wall=(MaterielScore) a.getWall();
				ad.setLimit_time(wall.getLimit_time());
				ad.setAd_id(String.valueOf(a.getId()));
				ad.setAd_mark(a.getAd_mark());
				resultList.add(ad);
			}
			
			//4:广告展示日志logger
			StringBuilder b=new StringBuilder();
			for (Advertise a:advertiseList) {
				b.append(",").append(a.getId()+"--***"+a.getFast_task()+"-----"+a.getAd_name());
				
			}
		}else{
			log.error(vo.getPage_type()+" no match "+app.getTypeIds());
		}
		return resultList;
	}
}
