package cn.adwalker.IOSChannel.strategy.channel;

import cn.adwalker.IOSChannel.picker.bean.ChannelConfig;
import cn.adwalker.IOSChannel.picker.bean.IosActivateLog;

/**
 * 渠道防作弊设置
 * @author adwalkerji
 *
 */
public interface IChannelCallbackStrategy {
	
	public boolean isCallback(ChannelConfig config,IosActivateLog activate,StringBuffer sbuf);
}
