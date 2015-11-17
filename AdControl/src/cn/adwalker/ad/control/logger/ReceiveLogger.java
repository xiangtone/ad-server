package cn.adwalker.ad.control.logger;

import java.util.List;

import org.apache.log4j.Logger;

import cn.adwalker.ad.control.util.DateUtils;
import cn.adwalker.ad.control.vo.AdActualDayVo;

public class ReceiveLogger {
	
	private Logger log;
	
	public ReceiveLogger() {
		log = Logger.getLogger(cn.adwalker.ad.control.logger.ReceiveLogger.class);
	}

	public void log(List<AdActualDayVo> adActualDayVoList, String ip) {
		if (adActualDayVoList != null && !adActualDayVoList.isEmpty()) {
			String currentTime = DateUtils.getDateStringByPattern("yyyy-MM-dd HH:mm:ss");
			StringBuffer sb = new StringBuffer();
			for(AdActualDayVo adActualDayVo : adActualDayVoList) {
				sb.append(currentTime);
				sb.append("\t").append(adActualDayVo.getAdId());
				sb.append("\t").append(adActualDayVo.getImpressionsAmount());
				sb.append("\t").append(adActualDayVo.getClickAmount());
				sb.append("\t").append(adActualDayVo.getDownloadAmount());
				sb.append("\t").append(adActualDayVo.getActionAmount());
				sb.append("\t").append(ip);
				sb.append("\r\n");
			}
			if(sb.length() > 0) {
				log.info(sb.substring(0, sb.length() - 2));
			}
		}
	}
}
