package cn.adwalker.IOSChannel.picker.call;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;
import cn.adwalker.ad.api.config.IParamConfig;
import cn.adwalker.ad.api.config.tmp.ParamConfig17173;
import cn.adwalker.ad.api.config.tmp.ParamConfigChaojiyingxiong;
import cn.adwalker.ad.api.config.tmp.ParamConfigChuangZhi;
import cn.adwalker.ad.api.config.tmp.ParamConfigClusterChukong;
import cn.adwalker.ad.api.config.tmp.ParamConfigClusterKuaiyou;
import cn.adwalker.ad.api.config.tmp.ParamConfigClusterYoumi;
import cn.adwalker.ad.api.config.tmp.ParamConfigJiuCheng;
import cn.adwalker.ad.api.config.tmp.ParamConfigMehtaCorp;
import cn.adwalker.ad.api.config.tmp.ParamConfigTuan800;

public class AdPartherMapper {
	public static ParamConfig createConfigMap(CampaignConfig config,IosClick iosClick,String service){
		IParamConfig paramConfig=null;
		if("jiucheng".equals(service)){
			paramConfig=new ParamConfigJiuCheng();
		}else if("17173".equals(service)){
			paramConfig=new ParamConfig17173();
		}else if("MEHTA_CORP".equals(service)){
			paramConfig=new ParamConfigMehtaCorp();
		}else if("chuangzhi".equals(service)){
			paramConfig=new	ParamConfigChuangZhi();
		}else if("chaojiyingxiong".equals(service)){
			paramConfig=new	ParamConfigChaojiyingxiong();
		}else if("tuan800".equals(service)){
			paramConfig=new	ParamConfigTuan800();
		}else if ("youmi_cluster".equals(service)) {
			paramConfig=new ParamConfigClusterYoumi();
		}else if ("chukong_cluster".equals(service)) {
			paramConfig=new ParamConfigClusterChukong();
		}else if ("kuaiyou_cluster".equals(service)) {
			paramConfig=new ParamConfigClusterKuaiyou();
		}
		return paramConfig.createConfigMap(config, iosClick);
	}
	
}
