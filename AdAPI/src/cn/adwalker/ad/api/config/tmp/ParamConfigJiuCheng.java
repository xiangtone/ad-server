package cn.adwalker.ad.api.config.tmp;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;
import cn.adwalker.ad.api.config.AbstractParamConfig;

public class ParamConfigJiuCheng extends AbstractParamConfig {

	@Override
	public ParamConfig createConfigMap(CampaignConfig config,
			IosClick iosClick) {
		/*
		 * 	http://ifenxiao.juzi.cn/channel/getad?appid=1000099&channel=2000077&imei=64C43BE6-339E-4E75-857A-50E39689661D&mac=64C43BE6-339E-4E75-857A-50E39689661D&pridata={callbackurl}&target=0
		 */
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("appid", iosClick.appid);
		 map.put("imei",  StringUtil.dealNull(iosClick.IDFA, iosClick.mac));
		 map.put("mac",StringUtil.dealNull(iosClick.IDFA, iosClick.mac));
		 String callback=CALL_BACK_URL+"?appid="+iosClick.appid+"&deviceid="+iosClick.deviceid+"&OPENUDID="+iosClick.OPENUDID+"&IDFA="+iosClick.IDFA+"&IDFV="+iosClick.IDFV;
	     map.put("pridata", StringUtil.encode(callback, "utf-8"));
		 return new ParamConfig(map);
	}

}
