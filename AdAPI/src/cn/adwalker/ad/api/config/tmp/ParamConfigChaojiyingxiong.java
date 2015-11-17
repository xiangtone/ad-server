package cn.adwalker.ad.api.config.tmp;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;
import cn.adwalker.ad.api.config.AbstractParamConfig;

public class ParamConfigChaojiyingxiong extends AbstractParamConfig {

	@Override
	public ParamConfig createConfigMap(CampaignConfig config,
			IosClick iosClick) {
		 Map<String, String> map = new HashMap<String, String>();
		 //map.put(StringUtil.dealEmpty(config.getAdid_str(), "appid"), iosClick.appid);
		 map.put(StringUtil.dealEmpty(config.getAdid_str(), "appid"), config.getAd_key());
		 map.put(StringUtil.dealEmpty(config.getDeviceid_para(),"deviceid"), iosClick.deviceid);
		 map.put(StringUtil.dealEmpty(config.getUdid(), "mac"), StringUtil.dealNull(iosClick.mac).replaceAll(":", ""));
		 map.put(StringUtil.dealEmpty(config.getOpenudid(),"OPENUDID"), StringUtil.dealNull(iosClick.getOPENUDID()));
		 map.put(StringUtil.dealEmpty(config.getIdfa(),"IDFA"), StringUtil.dealNull(iosClick.getIDFA()));
		 //map.put(StringUtil.dealEmpty(config.getIdfv(),"IDFV"), StringUtil.dealNull(iosClick.getIDFV()));		 
		 map.put(StringUtil.dealEmpty(config.getSourse_str(),"source"), StringUtil.dealEmpty(config.getSource(),SOURCE));
		 map.put(StringUtil.dealEmpty(config.getClient_ip(), "ip"), StringUtil.dealNull(iosClick.client_ip));
		 String callback=CALL_BACK_URL+"?appid="+iosClick.appid+"&deviceid="+iosClick.deviceid+"&OPENUDID="+iosClick.OPENUDID+"&IDFA="+iosClick.IDFA+"&IDFV="+iosClick.IDFV;
	     map.put(StringUtil.dealEmpty(config.getCallback(),"callback"), StringUtil.encode(callback, "utf-8"));
		 return new ParamConfig(map);
	}

}
