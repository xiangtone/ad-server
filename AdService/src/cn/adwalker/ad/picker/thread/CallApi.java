package cn.adwalker.ad.picker.thread;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.ad.picker.util.HttpClientUtils;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.util.ConfigUtil;

public class CallApi implements Runnable {
	private static final String url = ConfigUtil.getString("api.activate.url");
	private static final String callBackUrl = ConfigUtil.getString("add.source.url");
	private static final String AD_WALKER="adwalker";
	
	
	private Map<String,String> paramMap;
	
	private String config_id;
	
	private Long ad_id;
	
	public CallApi(Long ad_id,String config_id,Map<String,String> paramMap){
		this.paramMap=paramMap;
		this.config_id=config_id;
		this.ad_id=ad_id;
	}
	
	
	@Override
	public void run() {
		HttpClientUtils.postFromUrl(url, createMap());
	}
	public Map<String, String> createMap(){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", StringUtil.dealNull(config_id));//appid
		 map.put("source", AD_WALKER); //渠道名称
		 map.put("deviceid",paramMap.get("mac"));//uuid mac
		 map.put("client_ip", StringUtil.dealNull(paramMap.get("ip")));  //ip
		 map.put("app_key", StringUtil.dealNull(paramMap.get("app_id")));//appKeys
		 map.put("ad_key", StringUtil.dealNull(ad_id));//adKey
		 map.put("OPENUDID", StringUtil.dealNull(paramMap.get("openudid")));//udid
		 map.put("IDFA",paramMap.get("idfa"));//idfa
		 map.put("IDFV", StringUtil.dealNull(paramMap.get("idfv")));//idfv
		 map.put("os",StringUtil.dealNull(paramMap.get("os")));//os
		 map.put("sdkVersion", StringUtil.dealNull(	paramMap.get("sdkVersion")));
		 map.put("devUserId",paramMap.get("devUserId"));
         map.put("page_type", StringUtil.dealNull(paramMap.get("page_type")));//类型
         map.put("ssid",  StringUtil.dealNull(paramMap.get("ssid")));
         map.put("bssid",  StringUtil.dealNull(	paramMap.get("bssid")));
         map.put("phoneName",  StringUtil.dealNull(paramMap.get("phoneName")));
         map.put("latitude", StringUtil.dealNull(paramMap.get("latitude")));
         map.put("longitude", StringUtil.dealNull(paramMap.get("longitude")));
         String callback = callBackUrl+"?uuid="+paramMap.get("uuid")
        		 +"&appId="+map.get("app_key")
        		 +"&adId="+map.get("ad_key")
        		 +"&channel="+map.get("source")
        		 +"&page_type="+map.get("page_type")
        		 +"&version="+StringUtil.dealNull(paramMap.get("version"))
        		 +"&devUserId="+StringUtil.dealNull(paramMap.get("devuserid"))
        		 +"&idfa="+map.get("idfa");
         map.put("callback", callback); //callback
		 return map;
	}

}
