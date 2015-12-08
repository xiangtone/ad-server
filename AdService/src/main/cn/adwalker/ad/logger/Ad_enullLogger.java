package cn.adwalker.ad.logger;

import org.apache.log4j.Logger;

/**
 * 
* <p>Title: ClickLogger</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-4
 */
public class Ad_enullLogger {
	Logger log;
	public Ad_enullLogger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.Ad_enullLogger.class);
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
