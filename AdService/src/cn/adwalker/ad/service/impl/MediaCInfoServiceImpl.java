package cn.adwalker.ad.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.IMediaUserDao;
import cn.adwalker.ad.dao.domain.MediaUser;
import cn.adwalker.ad.picker.thread.CallApi;
import cn.adwalker.ad.picker.thread.CallAreaCode;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IMediaCInfoService;
import cn.adwalker.ad.service.vo.CInfoVo;
import cn.adwalker.ad.util.PublicUtil;


@Service
public class MediaCInfoServiceImpl implements IMediaCInfoService {
	
	
	@Resource
	private IUserInfoCache userInfoCache;
	
	
	@Resource
	private IDevAppCache devAppCache;
	
	
	@Resource
	private IMediaUserDao mediaUserDao;
	
	
	@Resource
	private IAdCache advCache;
	

	@Override
	public void doCinfo(CInfoVo vo,String os) {
		/**
		 * 1、初始化媒体用户
		 * 2、初始化系统用户
		 */
		String uuid="";
		DevApp devApp=devAppCache.getCache(vo.getApp_key());
		if(StringUtil.isEmpty(uuid)){
			uuid=PublicUtil.installUuid(vo.getDev_user_id(),String.valueOf(devApp.getId()));
		}
		MediaUser mediaUser=initMediaUser(uuid,vo.getApp_key(),vo.getDev_user_id(),os);
		UserInfo info = userInfoCache.getUserInfo(mediaUser.getUuid(),Long.valueOf(mediaUser.getApp_id()),vo.getIdfa(),vo.getIdfv());
		if (!StringUtils.isEmpty(vo.getClient_ip())&& (info == null || StringUtils.isEmpty(info.getAreaCode()))) {
			new Thread(new CallAreaCode(uuid, vo.getClient_ip(), info)).start();
		}
		//发送点击数据
		Map<String,String> paramMap=new HashMap<String, String>();
		paramMap.put("ip",vo.getClient_ip());
		paramMap.put("app_id", String.valueOf(devApp.getId()));
		paramMap.put("idfa",vo.getIdfa());
		paramMap.put("idfv",vo.getIdfv());
		paramMap.put("openudid", null);//udid
		paramMap.put("os",StringUtil.dealNull("ios"));//os
		paramMap.put("sdkVersion", StringUtil.dealNull("1.0.0"));
		paramMap.put("devUserId",vo.getDev_user_id());
		paramMap.put("page_type", "0");//类型
		paramMap.put("ssid",  null);
		paramMap.put("bssid",  null);
		paramMap.put("phoneName", null);
		paramMap.put("latitude", null);
		paramMap.put("longitude", null);
		paramMap.put("uuid",uuid);
		paramMap.put("version", null);
		paramMap.put("devuserid",vo.getDev_user_id());
		paramMap.put("mac",null);
		Advertise ad=advCache.getAdv(Long.valueOf(vo.getAd_id()));//广告
		new Thread(new CallApi(ad.getId(),ad.getConfig_id(),paramMap) ).start();
		
		
	}
	
	private MediaUser initMediaUser(String uuid,String app_id,String dev_user_id,String os){
		mediaUserDao.initMediaUser(uuid, dev_user_id, String.valueOf(app_id),os);
		MediaUser user=new MediaUser();
		user.setApp_id(String.valueOf(app_id));
		user.setUuid(uuid);
		user.setString(dev_user_id);
		return user;
	}
	
	
	
	

}
