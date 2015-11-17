package cn.adwalker.ad.logger;

import org.apache.log4j.Logger;

import cn.adwalker.ad.picker.monitor.ATTActionType;
import cn.adwalker.ad.picker.monitor.MonitorRunnable;

/**
 * 
* <p>Title: AdDownLogLogger</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-7
 */

public class AdDownLogLogger {  
	Logger log;
	public AdDownLogLogger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.AdDownLogLogger.class);
	}
	
	public void log(String s[]) {
		if (s != null && s.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				sb.append("\t");
			}
			log.info(sb.toString());
			//luoyouhua 增加 2013-10-11 控量侵入口 广告展示
			String adId = s.length>=5?s[4]:"";
			new Thread(new MonitorRunnable(adId, ATTActionType.STATUS_CPD.toString())).start();
		    //luoyouhua 结束
		}
	}

	/**
	* <p>Title: logInfo</p>
	* <p>Description:TODO</p>
	* @param create_time
	* @param valueOf
	* @param page_type
	* @param channel
	* @param valueOf2
	* @param categoryid
	* @param pay_type
	* @param string
	* @param uuid
	* @param area
	* @param ip
	* @param sdkversion
	* @return void
	* @throws
	*/
	public void logInfo(String create_time, String app_id, String page_type,
			String channel, String ad_id, String categoryid,
			String pay_type, String price, String uuid, String area,
			String ip, String sdkversion,String bannerTag,String imsi) {
		String s[] ={create_time,app_id,page_type,channel,ad_id,categoryid,pay_type,price,uuid,area,ip,sdkversion,bannerTag,imsi};
		log(s);		
	}
}
