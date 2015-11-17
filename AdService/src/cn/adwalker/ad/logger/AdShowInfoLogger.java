/**
* <p>Title: AdShowInfoLogger.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-9
* @version 1.0
*/
package cn.adwalker.ad.logger;

import org.apache.log4j.Logger;

/**
 * <p>Title: AdShowInfoLogger</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-9
 */
public class AdShowInfoLogger {
	Logger log;
	public AdShowInfoLogger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.AdShowInfoLogger.class);
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
