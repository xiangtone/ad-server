package cn.adwalker.ad.logger;

import org.apache.log4j.Logger;

/**
 * 
 * <p>
 * Title: AdbannerAc4Logger
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-2-28
 */

public class AdbannerAc4Logger {
	Logger log;

	public AdbannerAc4Logger() {
		log = Logger.getLogger(cn.adwalker.ad.logger.AdbannerAc4Logger.class);
	}

	public void log(String s[]) {
		if (s != null && s.length > 0) {
			StringBuffer sb = new StringBuffer();
			// sb.append("\t");
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				sb.append("\t");
			}
			log.info(sb.toString());
		}
	}

	/**
	 * <p>
	 * Title: logInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param create_time
	 * @param valueOf
	 * @param page_type
	 * @param channel
	 * @param valueOf2
	 * @param categoryid
	 * @param pay_type
	 * @param priceStr
	 * @param uuid
	 * @param area
	 * @param ip
	 * @param sdkversion
	 * @param string
	 * @return void
	 * @throws
	 */
	public void logInfo(String create_time, String app_id, String page_type,
			String channel, String ad_id, String categoryid, String pay_type,
			String priceStr, String uuid, String area, String ip,
			String sdkversion, String bannerTag, String imsi,String ssid,String bssid,String phoneName,String latitude,String longitude,String idfa) {
		String s[] = { create_time, app_id, page_type, channel, ad_id,
				categoryid, pay_type, priceStr, uuid, area, ip, sdkversion,
				bannerTag, imsi,ssid,bssid,phoneName,latitude,longitude,idfa };
		log(s);

	}
}
