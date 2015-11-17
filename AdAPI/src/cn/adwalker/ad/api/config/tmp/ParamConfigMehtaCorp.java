package cn.adwalker.ad.api.config.tmp;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;
import cn.adwalker.ad.api.config.AbstractParamConfig;

public class ParamConfigMehtaCorp extends AbstractParamConfig {

	/*
	 * http://ad-x.co.uk/API/click/CAYMEN2011BEASLEY/web353a33eb0a23b7/NET/{clickid}/adwalker?idfa={IDFA}&subid={appid}&ma={mac}
	 */
	@Override
	public ParamConfig createConfigMap(CampaignConfig config,
			IosClick iosClick) {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("subid", iosClick.appid);
		 map.put("mac", StringUtil.dealNull(iosClick.mac));
		 map.put("idfa",StringUtil.dealNull(iosClick.IDFA));
		 return new ParamConfig(map);
	}

}
