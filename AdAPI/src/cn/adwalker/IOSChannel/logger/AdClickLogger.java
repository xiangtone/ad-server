/**
* <p>Title: AdReceiveLogger.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-7-25
* @version 1.0
*/
package cn.adwalker.IOSChannel.logger;

import java.util.Date;

import org.apache.log4j.Logger;

import cn.adwalker.ad.util.DateUtil;

/**
 * <p>Title: AdReceiveLogger</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-7-25
 */
public class AdClickLogger {
	private Logger log;
	public AdClickLogger() {
		log = Logger.getLogger(cn.adwalker.IOSChannel.logger.AdClickLogger.class);
	}

	public void logInfo(String res_code,String uuid,String mac,String udid,String idfa,Long ad_id,Long app_id){
	  String s[] ={DateUtil.format(new Date(), DateUtil.PARTTERN_DATE_TIME),res_code,uuid,mac,udid,idfa,String.valueOf(ad_id),String.valueOf(app_id)};
	  log(s);
	}
	
	public void log(String s[]) {
		if (s != null && s.length > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("\t");
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				sb.append("\t");
			}
			log.info(sb.toString());
		}
	}
}
