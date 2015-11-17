/**
* <p>Title: ChannelReceiveLogger.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-7-25
* @version 1.0
*/
package cn.adwalker.IOSChannel.logger;

import org.apache.log4j.Logger;

/**
 * <p>Title: ChannelReceiveLogger</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-7-25
 */
public class ChannelReceiveLogger {
	private Logger log;
	public ChannelReceiveLogger() {
		log = Logger.getLogger(cn.adwalker.IOSChannel.logger.ChannelReceiveLogger.class);
	}
	
	
	public void logInfo(String appid,String channel,String deviceid,String client_ip,String statDate) {
		String s[] ={ appid, channel, deviceid, client_ip, statDate};
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
