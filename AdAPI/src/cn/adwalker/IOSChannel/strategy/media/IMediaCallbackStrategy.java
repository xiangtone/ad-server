package cn.adwalker.IOSChannel.strategy.media;

import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;
import cn.adwalker.IOSChannel.picker.dao.SupportDao;

/**
 * 媒体防作弊设置
 * @author adwalkerji
 *
 */
public interface IMediaCallbackStrategy {

	/**
	 * 根据媒体配置协议判断是否返回给媒体端
	 * @param activate
	 * @return
	 */
	public boolean isCallback(SupportDao supportDao,final MediaCallbackConfig callbackConfig,final IosActivateLog activate,StringBuffer sbuf);
}
