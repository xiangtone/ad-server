package cn.adwalker.IOSChannel.strategy.admaster;

import cn.adwalker.IOSChannel.picker.dao.SupportDao;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.xmemcached.PickerCache;
import cn.adwalker.ad.bean.DevApp;
import cn.adwalker.ad.util.ConfigUtil;
/**
 * 经纬度限制策略
 * @author adwalkerji
 *
 */
public class AdLatLonStrategy implements IAdMasterSendStrategy{
	private static AdLatLonStrategy latlonStrategy=new AdLatLonStrategy();
	public static AdLatLonStrategy getInstance(){
		if(latlonStrategy==null){
			synchronized (AdLatLonStrategy.class) {
				if(latlonStrategy==null){
					latlonStrategy=new AdLatLonStrategy();
				}
			}
		}
		return latlonStrategy;
	}
	@Override
	public boolean isSend(SupportDao supporDao,final AdSendConfig sendConfig,final IosClick iosClick,StringBuffer strbuf) {
		boolean b=true;
		if(!StringUtil.isEmpty(iosClick.getApp_key())){
			long appId=Long.parseLong(iosClick.getApp_key());
			DevApp app=PickerCache.getDevApp(appId);
			if(null!=app){
				Integer ic=app.getIs_coordinate();
				if(ic==1){
					if(!StringUtil.isEmpty(iosClick.getLatitude()) && !StringUtil.isEmpty(iosClick.getLongitude())){
						Integer ll=null;
						if(sendConfig==null || sendConfig.getAd_latlon_num()==null){
							String llstr=ConfigUtil.getString("ad_latlon_num");
							if(!StringUtil.isEmpty(llstr) && llstr.matches("[1-9]{1}[0-9]*")){
								ll=Integer.parseInt(llstr);
							}
						}else{
							ll=sendConfig.getAd_latlon_num();
						}
						if(null!=ll){
							int latlonnum=supporDao.getAdlatlonNum(iosClick.getLatitude(),iosClick.getLongitude(),iosClick.getAppid());
							if(latlonnum>=ll){
								b=false;
								strbuf.append("\t").append("经纬度"+iosClick.getLatitude()+","+iosClick.getLongitude()+"下激活数"+latlonnum+"大于设置数据"+ll+"因此不发送");
							}
						}
					}else{
						b=false;
						strbuf.append("\t").append("app设置要获取经纬度但是app没有获取到的经纬度，因此不想广告主发送点击");
					}
				}
			}
		}
		return b;
	}

}
