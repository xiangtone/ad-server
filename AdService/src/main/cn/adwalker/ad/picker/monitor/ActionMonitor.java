package cn.adwalker.ad.picker.monitor;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.adwalker.ad.logger.LoggerManager;
import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.util.ConfigUtil;


/**
 * @author admin
 * 控量监控数据收集类, 收集一定时间的广告点击 展示 下载数 定时发送到控制终端机。
 */
public class ActionMonitor {
	public static final Logger logger = Logger.getLogger(ActionMonitor.class);
	
	/**
	 * 采用线程安全的hashtable 暂定 保存一段时间内adId的monitor数量。
	 * */
	private static Hashtable<String, Integer> table = new Hashtable<String, Integer>();
	/**
	 * 保存adInfo key (monitor_sq_0,  monitor_H(n/5)_adId)
	 * */
	private static Map<String, String> seqKeyMap=new HashMap<String, String>();
	/**
	 * 保存adInfo信息
	 * */
	private static Map<String, AdInfo> monitorMap=new HashMap<String, AdInfo>();
	/**
	 * 保存发送失败的请求参数
	 * */
	private static Map<String, String> failKeyList=new HashMap<String, String>();
	
	/**
	 * 线程池,调用终端控制机。
	 * */
	@SuppressWarnings("unused")
	private ExecutorService service;

	private static final String SEQ_KEy="monitor_sq_";
	
	private static final String MONITOR_KEY="monitor_";
	private static Integer MINUTE=NumberUtil.getInteger(ConfigUtil.getString("monitor.minute"), 5);//5;//ConfigUtil.getString("monitor.minute");//  NumberUtil.getInteger(Config.getValue("monitor.minute"), 5);

	private static ActionMonitor monitor;
	
	public synchronized static ActionMonitor getInstance(){
	    	if(null==monitor){
	            monitor=new ActionMonitor();    		
	    	}
	    	return monitor;
	}
	
	/**
	 *功能描述:加载一条数据进map中.
	 * 2013-9-26
	 * @param adId  广告标识
	 * @param actionType  动作类型 如cpc cpa cpm
	 * admin
	 */
	public synchronized void putActionInMap(Integer adId,Integer actionType){
		//logger.info("putActionInMap adId:"+adId+"  actionType:"+actionType);
		logger.debug("putActionInMap adId:"+adId+"  actionType:"+actionType);
		ATTActionType type = ATTActionType.fromValue(actionType);
		if(type!=null){
			String tableKey = createMapKey(new Date());
			String monitorKey=tableKey+"_"+adId;
			String seqKey = createSeqKey(tableKey,monitorKey);
			logger.debug("tableKey :"+tableKey+"  monitorKey : "+monitorKey+" seqKey :"+seqKey);
			seqKeyMap.put(monitorKey, seqKey);
			AdInfo ad = monitorMap.get(seqKey);
			if(null==ad){
				ad=new AdInfo();
			}
			ad.setAdId(adId);
			if(ATTActionType.STATUS_CPA==type){
				ad.setCpa(ad.getCpa()+1);
			}
			if(ATTActionType.STATUS_CPM==type){
				ad.setCpm(ad.getCpm()+1);
			}
			if(ATTActionType.STATUS_CPC==type){
				ad.setCpc(ad.getCpc()+1);
			}
			if(ATTActionType.STATUS_CPD==type){
				ad.setCpd(ad.getCpd()+1);
			}
			monitorMap.put(seqKey, ad);
		}else{
			logger.debug("actionType is null");
		}
	}
	
	/**
	 * 解析生成发布到控制机的字符串.
	 * @param tableKey
	 * @param monitorKey
	 * @return
	 */
	private String createSeqKey(String tableKey,String monitorKey){
		String seqKey = seqKeyMap.get(monitorKey);
		
		if(null==seqKey){
			Integer  count= table.get(tableKey);
			if(count==null){
				count=0;
			}
			seqKey=SEQ_KEy+count;
			table.put(tableKey, ++count);
			logger.debug("tableKey:"+tableKey+"   "+count);
		}
		return seqKey;
	}
	
	/**
	 *功能描述:生成map key 生成规则 如5分钟执行一次则第n分钟生成的key为  monitor_H(n/5) 
	 *如第上午12时零7分钟 生成的key为 monitor_121.如程序每 5分钟发送一次则0-5分钟内生成的key是一样一样的.依次类推(暂未考虑跨时段的情况 )
	 * 2013-9-26
	 * @return
	 * admin
	 */
	private String createMapKey(Date d){
		Integer h = getHour(d);
		Integer m = getMinute(d);
		Integer seq = m/MINUTE;
		return MONITOR_KEY+h+seq;
	}
    
	/**
	 *功能描述:调用控制机，通知广告展示点击等情况. 
	 * 2013-9-26
	 * @return
	 * admin
	 */
	public String callCenter(){
		
		logger.info("tableSize:"+table.size()+"  seqSize:"+seqKeyMap.size()+"  monitorMap:"+monitorMap.size()+" failSize:"+failKeyList.size());
	    Date d = DateUtil.getAddMinByDate(new Date(),-MINUTE);
	    String tableKey = createMapKey(d);
		logger.debug("call center tableType"+tableKey);
	    new Thread(new SendCenterControll(tableKey,false,createMonitorStr(tableKey))).start();	
		failMapcallCenter();
		logger.info("tableSize:"+table.size()+"  seqSize:"+seqKeyMap.size()+"  monitorMap:"+monitorMap.size()+" failSize:"+failKeyList.size());
		//table.remove(tableKey);
		return null;
	}
	private String createMonitorStr(String tableKey){
		//JSONObject obj = new JSONObject();
		Integer count = table.get(tableKey);
		JSONArray r = new JSONArray();
		if(null!=count){
			for(int i=0;i<=count;i++){
				String seqKey=SEQ_KEy+i;
				//String monitorKey = seqKeyMap.get(seqKey);
				AdInfo info = monitorMap.get(seqKey);
				if(info!=null){
					JSONObject o = new JSONObject();
					o.accumulate("adId", info.getAdId());
					o.accumulate("clickAmount", info.getCpc());//click
					o.accumulate("impressionsAmount", info.getCpm());//show
					o.accumulate("actionAmount", info.getCpa());//actionAmount
					o.accumulate("downloadAmount",info.getCpd() );
					r.add(o);
					seqKeyMap.remove(tableKey+"_"+info.getAdId());
					LoggerManager.loggerMonitor(info);
				}
				monitorMap.remove(seqKey);
			}
			table.remove(tableKey);
		}
		return r.toString();
	}
	
	
	public void removeMonitorKey(String key){
		failKeyList.remove(key);
	}
	public void putMonitorStrInFailMap(String key,String monitorStr){
		failKeyList.put(key, monitorStr);
	}
	/**
	 *功能描述:上次调用失败的情况重新调用控制机，通知广告展示点击等情况. (暂未开启新线程)
	 * 2013-9-26
	 * @return
	 * admin
	 */
	private void failMapcallCenter(){
		for(Entry<String, String> en:failKeyList.entrySet()){
			logger.debug("call center failKey :"+en.getKey());
			new Thread(new SendCenterControll(en.getKey(),true,en.getValue())).start();	
		}
	}
	public int getHour(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
	    return cal.get(Calendar.HOUR_OF_DAY);
	}
	public int getMinute(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
	    return cal.get(Calendar.MINUTE);
	}
    	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		
		final Integer adId=0;
		final Integer actionType =0;
		
		ActionMonitor.getInstance().putActionInMap(220, 5);
		//ActionMonitor.getInstance().putActionInMap(221, 5);
		//ActionMonitor.getInstance().putActionInMap(222, 5);
		//ActionMonitor.getInstance().putActionInMap(223, 5);
		//ActionMonitor.getInstance().putActionInMap(224, 5);
		//ActionMonitor.getInstance().putActionInMap(225, 5);
		//ActionMonitor.getInstance().putActionInMap(225, 5);

		ActionMonitor.getInstance().callCenter();
		
		
	}
	
	
}
