package cn.adwalker.IOSChannel.logger;

import org.apache.log4j.Logger;

public class DevScoreSendLogger {
	private Logger log;
	public DevScoreSendLogger() {
		log = Logger.getLogger(cn.adwalker.IOSChannel.logger.DevScoreSendLogger.class);
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
}
