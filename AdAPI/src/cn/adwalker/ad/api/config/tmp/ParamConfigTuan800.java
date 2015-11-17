package cn.adwalker.ad.api.config.tmp;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.constant.BaseAttribute;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;
import cn.adwalker.ad.api.config.AbstractParamConfig;
import cn.adwalker.ad.util.MD5;

public class ParamConfigTuan800 extends AbstractParamConfig {

	@Override
	public ParamConfig createConfigMap(CampaignConfig config,
			IosClick iosClick) {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("source","xingyunzhe800");
		 map.put(StringUtil.dealEmpty(config.getAdid_str(), "appid"), config.getAd_key());
		 map.put("udid", iosClick.mac);
		 map.put("idfa", iosClick.IDFA);
		 map.put("deviceid", StringUtil.isEmpty(iosClick.mac) || BaseAttribute.IOS7_MAC_MAO.equals(iosClick.mac)?iosClick.IDFA:iosClick.deviceid);
		 String appId = config.getAd_key();
		 String udid = iosClick.getMac();
		 String key = "s80pxbriznu4p6yb";
		 String sign = MD5.encode(appId+udid+key);
		 map.put("sign", sign);
		 return new ParamConfig(map);
	}

}
