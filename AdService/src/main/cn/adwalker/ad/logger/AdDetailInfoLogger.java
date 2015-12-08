/**
* <p>Title: AdDetailInfoLogger.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-21
* @version 1.0
*/
package cn.adwalker.ad.logger;

import org.apache.log4j.Logger;

import cn.adwalker.ad.picker.monitor.ATTActionType;
import cn.adwalker.ad.picker.monitor.MonitorRunnable;

/**
 * <p>Title: AdDetailInfoLogger</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-21
 */
public class AdDetailInfoLogger {
	Logger log;
	public AdDetailInfoLogger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.AdDetailInfoLogger.class);
	}
	
	public void log(String s[]) {
		if (s != null && s.length > 0) {
			StringBuffer sb = new StringBuffer();
			//sb.append("\t");
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				sb.append("\t");
			}
			log.info(sb.toString());
			
			//luoyouhua 增加 2013-10-11 控量侵入口 广告展示
			String adId = s.length>=5?s[4]:"";
			new Thread(new MonitorRunnable(adId, ATTActionType.STATUS_CPC.toString())).start();
		    //luoyouhua 结束
		}
	}

	/**
	* <p>Title: logInfo</p>
	* <p>Description:TODO</p>
	* @param dateStr
	* @param valueOf
	* @param typeId
	* @param channel
	* @param valueOf2
	* @param valueOf3
	* @param charge_type
	* @param priceStr
	* @param uuid
	* @param area
	* @param ip
	* @param sdk
	* @param terminalType
	* @return void
	* @throws
	*/
	public void logInfo(String dateStr, String appId, String typeId,
			String channel, String adId, String category_id,
			String charge_type, String priceStr, String uuid, String area,
			String ip, String sdk, String terminalType,String imsi) {
		String s[] = {dateStr,appId,typeId,channel,adId,category_id,
				charge_type,priceStr,uuid,area,ip,sdk,terminalType,imsi};
		log(s);
	}
}
