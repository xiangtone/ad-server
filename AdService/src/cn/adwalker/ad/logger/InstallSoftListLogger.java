package cn.adwalker.ad.logger;

import org.apache.log4j.Logger;

/**
 * 
* <p>Title: ClickLogger</p>
* <p>Description:用户安装应用列表日志</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-4
 */
public class InstallSoftListLogger {
	Logger log;
	public InstallSoftListLogger() {
		log = Logger.getLogger(InstallSoftListLogger.class);
	}
	public void log(String s[]) {
		//43a8ecdf5ba4167a43039823ec8b681d        com.wowotuan    窝窝团  2014-10-10 14:59:42     AWSPIE8V7N0DSKUVLXXHPL11B66IJ3R4NE      androidV2.1.0
		if (s != null && s.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				sb.append("\t");
			}
			log.info(sb.toString());
		}
	}
}
