/**
* <p>Title: SDKTemplateInterface.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-13
* @version 1.0
*/
package cn.adwalker.ad.rules.template;

import java.util.List;

import cn.adwalker.ad.OS;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.picker.util.StringUtil;
import cn.adwalker.ad.rules.template.w.*;

/**
 * <p>Title: SDKTemplateInterface</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-13
 */
public class SDKTemplateFactory {
	
	
	/**
	 * 
	* <p>Title: getSDKTemplate</p>
	* <p>Description:临时增加一个type</p>
	* @param version
	* @param os
	* @param page_type
	* @param userAdRels
	* @return
	* @author cuidd
	* @date 2014年11月28日
	* @return SDKTemplate
	* @version 1.0
	 */
	public static SDKTemplate getSDKTemplate(String version,String os,String page_type,List<UserAdRel> userAdRels,String type){
		SDKTemplate sdkTemplate = null;
		
		if (!StringUtil.isEmpty(type)&&type.equals("weixin")) {
			sdkTemplate = new SDKWeixinTemplate();
			
		}else {
			//默认版本
			if(os.equals(OS.android)){
				sdkTemplate = new SDKTemplateCommon(OS.android);
			}else if(os.equals(AppConstant.OS_IOS)){
				sdkTemplate = new SDKIOSV120Template(AppConstant.OS_IOS);
			}
			
			if (version.equals(AppConstant.ANDROIDV210)) {
				sdkTemplate = new SDKAndroidV210Template(AppConstant.ANDROIDV210);
			} else if (version.equals(AppConstant.IOSV210)) {
				sdkTemplate = new SDKIOSV210Template(AppConstant.IOSV210);
			}
		}
		sdkTemplate.setOs(os);
		sdkTemplate.setPage_type(page_type);
		sdkTemplate.setUserAdRels(userAdRels);
		return sdkTemplate;
	}
}
