/**
* <p>Title: AdInfoLogger.java</p>
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
 * <p>Title: AdInfoLogger</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-21
 */
public class AdInfoLogger {
	Logger log;
	public AdInfoLogger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.AdInfoLogger.class);
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
			new Thread(new MonitorRunnable(adId, ATTActionType.STATUS_CPM.toString())).start();
		    //luoyouhua 结束
		}
	}
}
