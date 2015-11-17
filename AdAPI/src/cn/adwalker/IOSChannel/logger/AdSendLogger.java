/**
* <p>Title: AdSendLogger.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-3-5
* @version 1.0
*/
package cn.adwalker.IOSChannel.logger;

import org.apache.log4j.Logger;

/**
 * <p>Title: AdSendLogger</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-3-5
 */
public class AdSendLogger {
	private Logger log;
	public AdSendLogger() {
		log = Logger.getLogger(cn.adwalker.IOSChannel.logger.AdSendLogger.class);
	}
	
	
	/**
	* <p>Title: logInfo</p>
	* <p>Description:TODO</p>
	* @param params
	* @param k
	* @return void
	* @throws
	*/
	public void logInfo(String params, String k) {
		String s[] ={ params,k};
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
