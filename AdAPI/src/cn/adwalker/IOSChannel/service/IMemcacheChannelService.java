/**
* <p>Title: IMemcacheChannelService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-3-6
* @version 1.0
*/
package cn.adwalker.IOSChannel.service;

import cn.adwalker.IOSChannel.vo.AdvertisementChannel;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;

/**
 * <p>Title: IMemcacheChannelService</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-3-6
 */
public interface IMemcacheChannelService {

	/**
	 * 
	* <p>Title: getChannel</p>
	* <p>Description:TODO</p>
	* @param channel
	* @return
	* @return AdvertisementChannel
	* @throws
	 */
	public AdvertisementChannel getChannel(String channel);
	
	/***
	 * 
	* <p>Title: getIOSByKey</p>
	* <p>Description:TODO</p>
	* @param adkey
	* @return
	* @return Advertisement_IOS
	* @throws
	 */
	public Advertisement_IOS getIOSByKey(String adkey);
	
	/***
	 * 
	* <p>Title: getIOSById</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @return Advertisement_IOS
	* @throws
	 */
	public Advertisement_IOS getIOSById(String id);

	/**
	* <p>Title: updateCache</p>
	* <p>Description:TODO</p>
	* @return void
	* @throws
	*/
	public void updateCache();
	/**
	 * add by jief 删除区域方案配置缓存
	 * @param schemeId  Scheme  主键
	 */
	public void deleteSchemeCache(String schemeId);
}
