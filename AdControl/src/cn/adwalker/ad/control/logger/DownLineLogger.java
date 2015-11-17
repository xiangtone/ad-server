package cn.adwalker.ad.control.logger;

import java.util.List;

import org.apache.log4j.Logger;

import cn.adwalker.ad.control.domain.AdActualDay;
import cn.adwalker.ad.control.util.DateUtils;

public class DownLineLogger {

	private Logger log;
	
	public DownLineLogger() {
		log = Logger.getLogger(cn.adwalker.ad.control.logger.DownLineLogger.class);
	}

	public void log(List<AdActualDay> adActualDayList) {
		if (adActualDayList != null && !adActualDayList.isEmpty()) {
			String currentTime = DateUtils.getDateStringByPattern("yyyy-MM-dd HH:mm:ss");
			StringBuffer sb = new StringBuffer();
			for(AdActualDay adActualDay : adActualDayList) {
				sb.append(currentTime);
				sb.append("\t").append(adActualDay.getAdId());
				sb.append("\t").append(adActualDay.getImpressionsAmount());
				sb.append("\t").append(adActualDay.getClickAmount());
				sb.append("\t").append(adActualDay.getDownloadAmount());
				sb.append("\t").append(adActualDay.getActionAmount());
				sb.append("\r\n");
			}
			if(sb.length() > 0) {
				log.info(sb.substring(0, sb.length() - 2));
			}
		}
	}
}
