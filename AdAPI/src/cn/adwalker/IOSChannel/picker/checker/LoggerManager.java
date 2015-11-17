package cn.adwalker.IOSChannel.picker.checker;

import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.IOSChannel.picker.vo.IosClick;

/**
 * @author admin
 *  logger日志记录管理
 */
public class LoggerManager {
	private static final String TAB="\t";
	public static void clickLogger(IosClick iosClick){
		Logger.getLogger("iosClick").logInfo(loggerStr(iosClick.appid,iosClick.deviceid,iosClick.source,iosClick.IDFA,iosClick.OPENUDID,iosClick.IDFV));
	}
	public static void activateLogger(IosActivateLog a){
		Logger.getLogger("activate").logInfo(loggerStr(a.getAd_id(),a.getMac(),a.getChannel(),a.getApplication_key(),a.getOpenudid(),a.getArea_code(),a.getClient_ip()));
	}
	private static String loggerStr(Object ... objs){
		boolean b = true;
		StringBuffer sbuf = new StringBuffer();
		for(Object obj:objs){
		  sbuf.append(b?obj:(TAB+obj));
		  if(b=true){b=false;}
		}
		return sbuf.toString();
	}
}
