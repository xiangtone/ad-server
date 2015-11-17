package cn.adwalker.ad.api.config.tmp;

import java.util.HashMap;
import java.util.Map;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.util.StringUtil;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;
import cn.adwalker.ad.api.config.AbstractParamConfig;
import cn.adwalker.ad.util.MD5;

public class ParamConfigClusterChukong extends AbstractParamConfig {

	@Override
	public ParamConfig createConfigMap(CampaignConfig config,
			IosClick iosClick) {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("ver", "2.0");
		 map.put("adtype", "5");
		 map.put("pid", "838616047-CCED8E-685B-C8C4-98691B468");
		 String dvid=StringUtil.dealNull(iosClick.IDFA,iosClick.mac);
		 map.put("dvid", dvid);
		 map.put("idfa", iosClick.IDFA);
		 String version=iosClick.version;
		 String mac=iosClick.mac.replaceAll(":", "");
		 if (StringUtil.isEmpty(version)) {
			version=iosClick.isIos7(mac)?"7.0.1":"6.0.5";
		}
		 map.put("osv", version);
		 map.put("wmac", mac);
		 Long l=System.currentTimeMillis();
		 map.put("ctime", String.valueOf(l));
		 Map<String, String> headers=new HashMap<String, String>();
		 MD5 md5 = new MD5();
		 String sign = "pid=838616047-CCED8E-685B-C8C4-98691B468&ctime="+l+"&key=B1F6C7FE36708436B0A12F2A12281DA1";
		 headers.put("S2S-SIGNATURE", md5.getMD5ofStr(sign));
		return new ParamConfig(map,headers);
	}

}
