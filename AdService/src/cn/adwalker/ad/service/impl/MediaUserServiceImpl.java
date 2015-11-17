package cn.adwalker.ad.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.IMediaUserCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.IMediaUserDao;
import cn.adwalker.ad.dao.domain.MediaUser;
import cn.adwalker.ad.param.WeixinUserParam;
import cn.adwalker.ad.picker.thread.CallApiForScore;
import cn.adwalker.ad.picker.thread.CallAreaCode;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.service.IMediaUserService;
import cn.adwalker.ad.util.PublicUtil;

@Service
public class MediaUserServiceImpl  implements IMediaUserService{
	
	
	@Resource
	private IUserInfoCache userInfoCache;
	
	
	@Resource
	private IDevAppCache devAppCache;
	
	@Resource
	private	IMediaUserCache mediaUserCache;
	
	@Resource
	private IMediaUserDao mediaUserDao;
	
	@Resource
	private IAdCache adCache;

	@Override
	public void initUser(WeixinUserParam param) {
		
		/*
		 * 1、查询媒体用户表里边有没这个用户---条件（app_id,微信号）。
		 * 2、如果有用户基本信息放入缓存--做参数比对。
		 * 3、如果没有重新初始化设备。
		 */
		MediaUser mediaUser=mediaUserDao.findMediaUserByOs(param.getUserid(),"ios");
//		String uuid, Long appId,String idfa,String idfv
		String uuid=mediaUser.getUuid();
		UserInfo info = userInfoCache.getUserInfo(mediaUser.getUuid(),Long.valueOf(mediaUser.getApp_id()), param.getIdfa(), param.getIdfv());
		if (!StringUtils.isEmpty(param.getClinet_ip())&& (info == null || StringUtils.isEmpty(info.getAreaCode()))) {
			new Thread(new CallAreaCode(uuid, param.getClinet_ip(), info)).start();
		}
		
		DevApp devApp=	devAppCache.getDevApp(Long.valueOf(mediaUser.getApp_id()));
		//初始化成功发送新手任务
		 if(!StringUtil.isEmpty(mediaUser.getApp_id()) && !StringUtil.isEmpty(devApp.getResponse_url())){
        	new Thread(new CallApiForScore("0","试用先锋","0","http://res.adwalker.cn/ad/images/syxf120.png",param.getUserid(),100,"1",uuid,devApp.getId(),param.getIdfa())).start();
        }
		
	}

	@Override
	public void initMediaUser(String user_id,String app_key,Integer userType, String userCode2) {
		String uuid=null;
		DevApp devApp=devAppCache.getCache(app_key);
		if (userType==2) {
			MediaUser user=mediaUserDao.findMediaUser(userCode2,devApp.getId());
			uuid=user.getUuid();
		}else {
			if(StringUtil.isEmpty(uuid)){
				uuid=PublicUtil.installUuid(user_id,String.valueOf(devApp.getId()));
			}
		}
		
		mediaUserDao.initMediaUser(uuid, user_id, String.valueOf(devApp.getId()),devApp.getOs());
	}

	@Override
	public void initUser_android(String userid,
			String android_id,
			String mac_address,
			String phonenum,
			String imei,
			String imsi,
			String	client_ip) {
		/*
		 * 1、查询媒体用户表里边有没这个用户---条件（app_id,微信号）。
		 * 2、如果有用户基本信息放入缓存--做参数比对。
		 * 3、如果没有重新初始化设备。
		 */
		MediaUser mediaUser=mediaUserDao.findMediaUserByOs(userid, "android");
		String uuid=mediaUser.getUuid();
		UserInfo info = userInfoCache.getUserInfo(uuid, imei, 
				phonenum, "telModel","netEnv", "areaCode", "operator",
				"0","0","os","brand","imsi",Long.valueOf(mediaUser.getApp_id()),"terminalType","osName");
		if (!StringUtils.isEmpty(client_ip)&& (info == null || StringUtils.isEmpty(info.getAreaCode()))) {
			new Thread(new CallAreaCode(uuid, client_ip, info)).start();
		}
		
		DevApp devApp=	devAppCache.getDevApp(Long.valueOf(mediaUser.getApp_id()));
		//初始化成功发送新手任务
		 if(!StringUtil.isEmpty(mediaUser.getApp_id()) && !StringUtil.isEmpty(devApp.getResponse_url())){
        	new Thread(new CallApiForScore("0","试用先锋","0","http://res.adwalker.cn/ad/images/syxf120.png",userid,100,"1",uuid,devApp.getId(),null)).start();
        }
		
	}
}
