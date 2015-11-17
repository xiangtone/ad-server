package cn.adwalker.ad.api.config.tmp;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;
import cn.adwalker.ad.api.config.AbstractParamConfig;

public class ParamConfig17173 extends AbstractParamConfig {

	@Override
	public ParamConfig createConfigMap(CampaignConfig config,
			IosClick iosClick) {
		/*
		 * http://api.wooboo.com.cn/services/cpa/notify?appid=17173&channel=xingzhe&mac={:$MAC}&idfa={$idfa}&phoneip={$phoneip}&param1=iphone&param2={clicktime}&version={$version}
		 */
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", iosClick.appid);
		 map.put("mac", StringUtil.dealNull(iosClick.mac));
		 map.put("idfa",StringUtil.dealNull(iosClick.IDFA));
		 map.put("phoneip", StringUtil.dealNull(iosClick.client_ip));
		 map.put("version", StringUtil.dealNull(iosClick.os));
		 return new ParamConfig(map);
	}

}
