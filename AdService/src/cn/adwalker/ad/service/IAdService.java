/**
* <p>Title: AdService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-4
* @version 1.0
*/
package cn.adwalker.ad.service;

import java.io.IOException;

import cn.adwalker.ad.param.AdDetailParam;
import cn.adwalker.ad.param.AdDetailParamIos;
import cn.adwalker.ad.vo.AdDetailIos;
import cn.adwalker.ad.vo.AdDetailJson;

/**
 * <p>Title: AdService</p>
 * <p>Description:</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-4
 */
public interface IAdService {

	/**
	 * @throws Exception 
	* <p>Title: getADWallList</p>
	* <p>Description:TODO</p>
	* @param page
	* @param appId
	* @param wallType
	* @param typeId
	* @param uiType
	* @param os  add by jief 
	* @return
	* @return AdverJson
	* @throws
	*/
	AdDetailJson getAdDetail(AdDetailParamIos param) throws Exception;

	public AdDetailJson getAdDetail(AdDetailParam vo,String os) throws Exception ;

	AdDetailIos getAdDetail(Long adId,String os,String mediaUserId) throws IOException;
}
