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

import org.apache.log4j.Logger;

/**
 * <p>Title: AdReceiveLogger</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-7-25
 */
public class AdReceiveLogger {
	private Logger log;
	public AdReceiveLogger() {
		log = Logger.getLogger(cn.adwalker.IOSChannel.logger.AdReceiveLogger.class);
	}
	/**
	 * 
	 * @param adkey
	 * @param deviceid
	 * @param OpenUDID  add by jief 2013-09-11
	 * @param IDFA		add by jief	2013-09-11
	 * @param IDFV		add by jief	2013-09-11
	 */
	public void logInfo(String adkey,String deviceid,String OpenUDID,String IDFA,String IDFV){
	  String s[] ={adkey, deviceid,OpenUDID,IDFA,IDFV};
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
