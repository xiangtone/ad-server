package cn.adwalker.ad.picker.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.HttpClientUtils;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.util.ConfigUtil;

public class CallApiForScore implements Runnable {

	private static final String url = ConfigUtil.getString("escore_interface_addScore");
	private String userId;
	private Integer score;
	private String plat;
	private String uuid;
	private Long appId;
	private String idfa;
	private String ad_id;
	private String ad_name;
	private String fast_task;
	private String icon_url;
	
	public CallApiForScore(String ad_id,String ad_name,String fast_task,String icon_url,String userId,Integer score,String plat,String uuid,Long appId,String idfa){
		this.userId=userId;
		this.score=score;
		this.plat=plat;
		this.uuid=uuid;
		this.appId=appId;
		this.idfa=idfa;
		this.ad_id=ad_id;
		this.ad_name=ad_name;
		this.fast_task=fast_task;
		this.icon_url=icon_url;
		
		
	}
	
	@Override
	public void run() {
		HttpClientUtils.postFromUrl(url, createMap());
	}

	public Map<String, String> createMap(){
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("uuid", StringUtil.dealNull(uuid));//uuid
		 map.put("userID", userId); //userId
		 map.put("score", StringUtil.dealNull(score));//score
		 map.put("exchangetime",DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
		 map.put("adId", StringUtil.dealNull(ad_id));
		 map.put("appId", StringUtil.dealNull(appId));
		 String appName =(ad_name!=null)?ad_name:"";
		 map.put("appName", appName);
		 map.put("idfa", StringUtil.dealNull(idfa));
		 map.put("plat", plat);
		 map.put("icon_url",icon_url);
		 if (!StringUtil.isEmpty(fast_task)&&fast_task.equals("1")) {
			 map.put("fastTask", "1");//快速任务标识
		}
		 return map;
	}
	
	
	
	
	public static void main(String args[]){
		
//		cInfo.do?&adid=7317&bundleid=com.tudou.tudouiphone&idfa=C3A558B1-D6BB-407E-B371-6ABF86579EE4&userid=pmlr4447
		
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("uuid", "2d68ef14870a9df6702bc1f380773a98");//uuid
		 map.put("userID", "pmlr4447"); //userId
		 map.put("score", StringUtil.dealNull(200));//score
		 map.put("exchangetime",DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));  //时间
		 map.put("adId", "7239");
		 map.put("appId", "6006");
		 String appName = "大众点评";
		 map.put("appName", StringUtil.encode(appName, "utf-8"));
		 map.put("idfa", StringUtil.dealNull("C3A558B1-D6BB-407E-B371-6ABF86579EE4"));
		 map.put("plat", "1");
		 System.out.println(HttpClientUtils.postFromUrl("http://106.120.153.236/AdAPI/common/addScore.do", map));	;
		
	}
	

}
