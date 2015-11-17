package cn.adwalker.IOSChannel.strategy.admaster;

import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.ad.util.ConfigUtil;
/**
 * bssid限制策略
 * @author adwalkerji
 *
 */
public class AdBssidStrategy implements IAdMasterSendStrategy{
	private static AdBssidStrategy bssidStrategy=new AdBssidStrategy();
	public static AdBssidStrategy getInstance(){
		if(bssidStrategy==null){
			synchronized (AdBssidStrategy.class) {
				bssidStrategy=new AdBssidStrategy();
			}
		}
		return bssidStrategy;
	}
	@Override
	public boolean isSend(SupportDao supportDao,final AdSendConfig sendConfig,final IosClick iosClick,StringBuffer strbuf) {
		boolean b=true;
		Integer bssidNum=null;
		if(sendConfig ==null || StringUtil.isEmpty(sendConfig.getAd_bssid_num())){
			String bssidNumstr=ConfigUtil.getString("ad_bssid_num");
			if(!StringUtil.isEmpty(bssidNumstr) && bssidNumstr.matches("[1-9]{1}[0-9]*")){
				bssidNum=Integer.parseInt(bssidNumstr);
			}
		}else{
			bssidNum =sendConfig.getAd_bssid_num();
		}
		if(bssidNum!=null && !StringUtil.isEmpty(iosClick.getBssid())){
			int aba=supportDao.getAdBssidActNum(iosClick.getBssid(), iosClick.getAppid());
			if(aba >= bssidNum){
				b=false;
				strbuf.append("\t").append("根据 bssid="+iosClick.getBssid()+"统计数为"+aba+"超过 "+bssidNum);
			}
		}
		return b;
	}

}
	
