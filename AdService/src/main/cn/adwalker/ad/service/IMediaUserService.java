package cn.adwalker.ad.service;

import cn.adwalker.ad.param.WeixinUserParam;

public interface IMediaUserService {
	
	
	public void initMediaUser(String user_id,String app_key, Integer userType, String userCode2);
	
	
	public void initUser(WeixinUserParam param);
	
	public void initUser_android(String userid,String android_id,
			String mac_address,
			String phonenum,
			String imei,
			String imsi,
			String
			client_ip);
	
}
