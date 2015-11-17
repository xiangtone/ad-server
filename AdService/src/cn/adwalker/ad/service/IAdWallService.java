/**
* <p>Title: IAdWallService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-27
* @version 1.0
*/
package cn.adwalker.ad.service;

import java.util.List;

import cn.adwalker.ad.beans.AdApiData;
import cn.adwalker.ad.beans.PortalData;
import cn.adwalker.ad.param.AdApiParam;
import cn.adwalker.ad.param.AdParam;
import cn.adwalker.ad.param.PageParam;
import cn.adwalker.ad.param.WallParam;
import cn.adwalker.ad.param.WallParam2;
import cn.adwalker.ad.vo.AdverJson;
import cn.adwalker.ad.vo.OnlineAd;
import cn.adwalker.ad.vo.V;

/**
 * <p>Title: IAdWallService</p>
 * <p>Description:广告墙服务组件</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-27
 */
public interface IAdWallService {

	
	/**
	 * 
	* <p>Title: adPicker</p>
	* <p>Description:获取广告</p>
	* @param vo
	* @param os
	* @return
	* @author 
	* @date 2014年10月16日
	* @return AdverJson
	* @version 1.0
	 */
	public AdverJson adPicker(WallParam vo,String os);
	
	
	
	public List<OnlineAd> adList(WallParam2 vo,String os);
	
	/**
	 * 
	* <p>Title: getNoticeInfo</p>
	* <p>Description:通知中心</p>
	* @param vo
	* @param os
	* @return
	* @author cuidd
	* @date 2014年10月16日
	* @return AdverJson
	* @version 1.0
	 */
	public AdverJson getNoticeInfo(PageParam vo,String os);
	
	/**
	 * 
	* <p>Title: hotTueijian</p>
	* <p>Description:热荐</p>
	* @param vo
	* @param os
	* @return
	* @author cuidd
	* @date 2014年10月16日
	* @return AdverJson
	* @version 1.0
	* @throws
	 */
	public AdverJson hotTueijian(PageParam vo,String os);
	
	public PortalData portalAdPicker(AdParam param);

	public V<List<OnlineAd>> onlineAd(String userid,String os);
	
	public AdApiData apiAdPicker(AdApiParam param);

	public String getAPPStoreUrl(String ad_id);
	
}
