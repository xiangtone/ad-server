package cn.adwalker.IOSChannel.strategy.admaster;

import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
/**
 * 验证是否重复激活 根据openudid
 * @author adwalkerji
 *
 */
public class AdHasActiviteStrategy implements IAdMasterSendStrategy{

	@Override
	public boolean isSend(SupportDao supportDao,final AdSendConfig sendConfig, final IosClick iosClick,StringBuffer strbuf) {
		Integer c = supportDao.getCountByOpenUdidAndAdId(iosClick.getAppid(), iosClick.getOPENUDID());
		return c<2;
	}

}
