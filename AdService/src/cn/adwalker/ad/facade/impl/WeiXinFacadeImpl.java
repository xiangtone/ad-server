package cn.adwalker.ad.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IDevAppCache;
import cn.adwalker.ad.cache.IMediaUserCache;
import cn.adwalker.ad.cache.IUserInfoCache;
import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.domain.MediaUser;
import cn.adwalker.ad.facade.IWeiXinFacade;
import cn.adwalker.ad.service.IMediaUserService;

@Service
public class WeiXinFacadeImpl implements IWeiXinFacade {
	
	@Resource
	private	IMediaUserCache mediaUserCache;
	
	@Resource
	private IUserInfoCache userInfoCache;
	
	
	@Resource
	private IDevAppCache devAppCache;
	
	@Resource
	private	IMediaUserService mediaUserService;

	@Override
	public UserInfo checkUser(String user_id,String appkey) {
		UserInfo u=null;
		DevApp devApp=devAppCache.getCache(appkey);
		MediaUser mediaUser=mediaUserCache.getMediaUser(user_id,devApp.getId());
		if (mediaUser!=null) {
			u=userInfoCache.getUserInfo(mediaUser.getUuid());
		}
		return u;
	}

	@Override
	public String convertAppKey(String appkey,String os) {
		if(appkey.length()=="AWRPAT8LXN7XRUSUASIQP7U6P6MOEBATKE".length()){
			//这个还是老的
			//不做处理
		}else {
			String string=new String(Base64.decodeBase64(appkey.getBytes()));
			String arr[]=string.split("\\*");
			String app_id=null;
			if (os.equals("ios")) {
				app_id=arr[0];
			}else {
				app_id=arr[1];
			}
			DevApp devapp=devAppCache.getDevApp(Long.valueOf(app_id));
			appkey=devapp.getAppkey();
		}
		return appkey;
	}

	@Override
	public void initMediaUser(String userId, String appkey,Integer userType,String userCode2) {
		mediaUserService.initMediaUser(userId, appkey,userType,userCode2);
	}

}
