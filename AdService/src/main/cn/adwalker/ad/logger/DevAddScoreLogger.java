package cn.adwalker.ad.logger;

import org.apache.log4j.Logger;

import cn.adwalker.ad.picker.monitor.ATTActionType;
import cn.adwalker.ad.picker.monitor.MonitorRunnable;

/**
 * 
* <p>Title: AdDownLogLogger</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-7
 */

public class DevAddScoreLogger {  
	Logger log;
	public DevAddScoreLogger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.DevAddScoreLogger.class);
	}
	
	public void log(String ...s) {
		if (s != null && s.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				sb.append("\t");
			}
			log.info(sb.toString());
			//luoyouhua 增加 2013-10-11 控量侵入口 广告展示
			String adId = s.length>=5?s[4]:"";
			String sign = s.length>=14?s[13]:"";
			if("0".equals(sign)){
				new Thread(new MonitorRunnable(adId, ATTActionType.STATUS_CPA.toString())).start();
			}
		    //luoyouhua 结束
		}
	}
}
