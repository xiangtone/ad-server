package cn.adwalker.ad.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.cache.IAdCache;
import cn.adwalker.ad.cache.element.Advertise;
import cn.adwalker.ad.logger.LoggerManager;
import cn.adwalker.ad.picker.constants.T;
import cn.adwalker.ad.picker.util.NumberUtil;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.picker.vo.PortalVo;

@Service("portalService")
public class PortalService {
	@Resource
	private IAdCache advCache;
	
	public void motion(PortalVo param){		
		if(!StringUtil.isEmpty(param.adIds)){
			String[] aid = param.adIds.split(",");
			for(String adId:aid){
				Advertise ad = advCache.getAdv(NumberUtil.getLong(adId, 0));
				if(ad!=null){
					 if(StringUtil.equals("show",param.ac )){
					     LoggerManager.logAdShowInfo(ad, param.appId, T.tueijian.toString(), param.channel, param.uuid, param.areaCode, param.ip, param.version, 0, null,param.getSsid(),param.getBssid(),param.getPhoneName(),param.getLatitude(),param.getLongitude(),param.getIdfa());	
					 }else if(StringUtil.equals("click", param.ac)){
		         		 LoggerManager.logAdDetailInfo(StringUtil.dealNull(param.appId), T.tueijian.toString(), param.channel, StringUtil.dealNull(ad.getId()),String.valueOf(ad.getCategory_id()),ad.getBlance_mode(), StringUtil.dealNull(ad.getBlance_price(),"0"), param.uuid, param.areaCode, param.ip, param.version, "0", null);
					 }else if(StringUtil.equals("down", param.ac)){
						 LoggerManager.logAdbannerAc4(StringUtil.dealNull(param.appId), T.tueijian.toString(), param.channel, StringUtil.dealNull(ad.getId()),String.valueOf(ad.getCategory_id()), ad.getBlance_mode(), StringUtil.dealNull(ad.getBlance_price(),"0"), param.uuid, param.areaCode, param.ip, param.version, "0", null, param.ssid, param.bssid, param.phoneName, param.latitude, param.longitude, param.idfa);
					 }
				}
			}
		}
	   
	    
	}
}
