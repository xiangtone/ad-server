package cn.adwalker.IOSChannel.picker.util;

import java.util.Date;

import cn.adwalker.IOSChannel.picker.bean.IosActionLog;
import cn.adwalker.IOSChannel.picker.vo.IosClick;

public abstract class BeanHelper {
	
	public static IosActionLog iosClickConverToIosActionLog(IosClick click){
		IosActionLog action = new IosActionLog();
		action.setApplication_key(click.getApp_key());
		action.setCallback_url(click.getCallback());
		action.setAdId(click.getAppid());
		action.setMac(click.getDeviceid());
		action.setChannel(click.getSource());
		action.setClient_ip(click.getClient_ip());
		action.setAd_key(click.getAd_key());
		action.setPage_type(click.getPage_type());
		action.setAd_key(click.getAd_key());
		action.setCreateTime(new Date());
		action.setActivite_status(0);
		action.setStatus(0);
		action.setIdfa(click.getIDFA());
		action.setIdfv(click.getIDFV());
		action.setOs_version(click.getOs());
		action.setOpenudid(click.getOPENUDID());
		action.setArea_code(click.getAreaCode());
		action.setIn_price(click.getIn_price());
		action.setBssid(click.getBssid());
		action.setSsid(click.getSsid());
		action.setLatitude(click.getLatitude());
		action.setLongitude(click.getLongitude());
		action.setPhoneName(click.getPhoneName());
		return action;
	}
	

}
