package cn.adwalker.ad.logger;

import org.apache.log4j.Logger;

/***
* <p>Title: 消耗积分记录日志</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    caiqiang
* @date       2013-1-7
 */
public class InitDataLogger {
	Logger log;
	public InitDataLogger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.InitDataLogger.class);
	}
	
	public void log(String s[]) {
		if (s != null && s.length > 0) {
			StringBuffer sb = new StringBuffer();
//			sb.append("\t");
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				sb.append("\t");
			}
			log.info(sb.toString());
		}
	}
}
