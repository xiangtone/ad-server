/**
* <p>Title: SDK300Template.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-2-28
* @version 1.0
*/
package cn.adwalker.ad.rules.template.w;

import cn.adwalker.ad.rules.template.SDKTemplate;
import cn.adwalker.ad.util.AppConstant;

/**
 * <p>Title: SDKAndroidV300Template</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-2-28
 */
public class SDKAndroidV210Template extends SDKTemplate {

	
	public SDKAndroidV210Template(String sdkVersion){
		setSdkVersion(sdkVersion);
	}
	/***
	 * 根据SDK版本号返回广告类型
	* <p>Title: getAd_type</p>
	* <p>Description:TODO</p>
	* @param version
	* @param ad_type
	* @return
	* @return int
	* @throws
	 */
	public int getAd_type(String version,int ad_type){
		if(ad_type==AppConstant.AD_TYPE_DOWNLOAD){
			ad_type=AppConstant.AD_TYPE_DETAILS;
		}
		if(ad_type==AppConstant.AD_TYPE_DRIECT_DOWNLOAD){
			ad_type=AppConstant.AD_TYPE_DETAILS;
		}
		return ad_type;		
	}
}
