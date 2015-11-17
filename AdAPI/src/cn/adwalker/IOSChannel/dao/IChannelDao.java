/**
* <p>Title: IChannerlDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-3-1
* @version 1.0
*/
package cn.adwalker.IOSChannel.dao;

import java.util.List;
import cn.adwalker.IOSChannel.vo.AdvertisementChannel;
import cn.adwalker.IOSChannel.vo.Advertisement_IOS;
import cn.adwalker.IOSChannel.vo.ChannelRequestResult;

/**
 * <p>Title: IChannerlDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-3-1
 */
public interface IChannelDao {
	/**
	*保存数据
	*/
	public void saveDate(ChannelRequestResult vo, String[] macAdress);
	
	/**
	 * <p>检查是否有点击</p>
	 * @param callback
	 * @param adios
	 * @return
	 */
	public ChannelRequestResult getClickLog(ChannelRequestResult callback,Advertisement_IOS adios);

	/**
	 * <p>防止重复插入数据</p>
	 * @param actionLog
	 */
	public void saveActiveLogIfNotExist(ChannelRequestResult actionLog);
	
	/**
	* <p>Title: getChannel</p>
	* <p>Description:TODO</p>
	* @param source
	* @return
	* @return AdvertisementChannel
	* @throws
	*/
	public AdvertisementChannel getChannel(String source);
	
	/**
	 * 
	* <p>Title: getISOList</p>
	* <p>Description:TODO</p>
	* @return
	* @return List<Advertisement_IOS>
	* @throws
	 */
	public List<AdvertisementChannel> getChannelList();
	
	/**
	* <p>Title: getIOS</p>
	* <p>Description:TODO</p>
	* @param appkey
	* @return
	* @return cn.adwalker.ios.service.Advertisement_IOS
	* @throws
	*/
	public Advertisement_IOS getIOS(String adkey); 
	
	/**
	 * 
	* <p>Title: getISOList</p>
	* <p>Description:TODO</p>
	* @return
	* @return List<Advertisement_IOS>
	* @throws
	 */
	public List<Advertisement_IOS> getISOList();
	
	/**
	 * 
	 * @param list
	 */
	public void saveDate(List<ChannelRequestResult> list);


	
	/**
	 * <p>根据方案id获取广告列表的主键</p>
	 * @param schemeid
	 * @return
	 */
	public List<Long> getAdsSchemeBySchemeId(String schemeId);
	
	/**
	 * <p>根据方案id获取媒体列表主键</p>
	 * @param schemeid
	 * @return
	 */
	public List<Long> getmiediasSchemeBySchemeId(String schemeId);
	
}
