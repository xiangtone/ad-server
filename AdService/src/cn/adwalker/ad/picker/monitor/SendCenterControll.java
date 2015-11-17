package cn.adwalker.ad.picker.monitor;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import cn.adwalker.ad.util.ConfigUtil;

/**
 * @author admin
 * 调用控制机,线程类
 */
public class SendCenterControll implements Runnable {

	private static final Logger logger = Logger.getLogger(SendCenterControll.class);
	private static final String CENTER_CONTROLL_URL= ConfigUtil.getString("controll.center.url");//"http://www.xxxx.com";
	//action monitor中的table key
	private String key;
	//是否是重发标识
	private boolean fail;
	//发送控制机的json字符串
	private String monitorStr;
	//失败后重发次数
	private Integer times=3;
	//失败后重发时间 (5秒后重新发一次)
	private Integer second=5000;
	
	SendCenterControll(){
		
	}
    SendCenterControll(String key,boolean fail,String monitorStr){
		this.key=key;
		this.fail=fail;
		this.monitorStr=monitorStr;
	}
	@Override
	public void run() {
		boolean result=false;
		if("[]".equals(monitorStr)){
			logger.debug("no data to post-------------------------");
		}else{
			do{
				logger.debug("call center"+fail+" monitorStr"+monitorStr);
				result=callCenterController();
				times--;
				if(!result&&times>0){
					logger.error("call center fail may try times:"+times);
					try {
						Thread.sleep(second);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}while(!result && times>0);
			//times次发送失败后暂停发送,并把 monitor str保存到failMap中
			if(!result && !fail){
				logger.error("call center fail,put monitor str in failMap");
				ActionMonitor.getInstance().putMonitorStrInFailMap(key, monitorStr);
			}
			//发送成功而且是重复发送的记录则从失败记录中remove
			if(result && fail){
				ActionMonitor.getInstance().removeMonitorKey(key);
			}
		}
	}
	public boolean callCenterController(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("json", monitorStr);
		String result= HttpHelp.postFromUrl(CENTER_CONTROLL_URL, map);
		try{
			JSONObject o = JSONObject.fromObject(result);
			return o.getBoolean("succeed");
		}catch(Exception e){
			return false;
		}
	}
	
	public static void main(String[] args){
		String s = "{\"errorCode\":null,\"succeed\":true,\"errorMsg\":null}";
		try{
			JSONObject o = JSONObject.fromObject(s);
			System.out.println(o.getBoolean("succeed"));
			if(o.getBoolean("succeed")){
				System.out.println("aaaaaaaaa");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
