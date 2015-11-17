package cn.adwalker.IOSChannel.strategy.admaster;

import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
/**
 * 广告主防作弊设置
 * @author adwalkerji
 *
 */
public interface IAdMasterSendStrategy {

	public boolean isSend(SupportDao supporDao,final AdSendConfig sendConfig,final IosClick iosClick,StringBuffer strbuf);
}
