/**
* <p>Title: TypeInfoLogger.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-21
* @version 1.0
*/
package cn.adwalker.ad.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * <p>Title: TypeInfoLogger</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-21
 */
public class TypeInfoLogger {
	Logger log;
	public TypeInfoLogger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.TypeInfoLogger.class);
	}
	
	
	public void typeInfo(Long appId,String version,String channel,String uuid,String area, String ip,String ssid,String bssid,String phoneName,String latitude,String longitude,String idfa,int id) {	
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String dateStr = formatter.format(new Date());
		String s[] = {dateStr,String.valueOf(appId),String.valueOf(id),channel,uuid,area,ip,version,ssid,bssid,phoneName,latitude,longitude,idfa};
		this.log(s);
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
		}
	}
}
