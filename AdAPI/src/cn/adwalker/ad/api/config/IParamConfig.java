package cn.adwalker.ad.api.config;

import cn.adwalker.IOSChannel.picker.bean.CampaignConfig;
import cn.adwalker.IOSChannel.picker.vo.IosClick;
import cn.adwalker.IOSChannel.picker.vo.ParamConfig;

/**
 * 
* <p>Title: IParamConfig</p>
* <p>Description:广告主参数配置接口</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年11月6日
 */
public interface IParamConfig {
	
	public ParamConfig createConfigMap(CampaignConfig config,IosClick iosClick);
	
	

}
