package cn.adwalker.ad.api.config.tmp;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;
import cn.adwalker.ad.api.config.AbstractParamConfig;

public class ParamConfigClusterYoumi extends AbstractParamConfig {

	@Override
	public ParamConfig createConfigMap(CampaignConfig config,
			IosClick iosClick) {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put(config.getDeviceid_para(),iosClick.mac);
		 map.put(config.getUdid(),  iosClick.OPENUDID);
		 map.put(config.getIdfa(), iosClick.IDFA);
		 map.put(config.getClient_ip(), iosClick.client_ip);
		 map.put("goto","0");
		 return new ParamConfig(map);
	}

}
