/**
 * <p>Title: IMemcache.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-1-16
 * @version 1.0
 */
package cn.adwalker.ad.service;

/**
 * <p>
 * Title: IMemcache
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-1-16
 */
public interface IMemcacheService {

	/**
	 * <p>
	 * Title: updateCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param adIds
	 * @return void
	 * @throws
	 */
	public void updateCache(String adId);

	/**
	 * <p>
	 * Title: updateDevCurrencyCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param appId
	 * @return void
	 * @throws
	 */
	public void updateDevCurrencyCache(String app_id);

	

	/**
	 * <p>
	 * Title: updateDevCurrencyCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return void
	 * @throws
	 */
	public void updateConfigFinance();

	



	


	/**
	 * <p>
	 * Title: clearUserCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param uuid
	 * @author cuidd
	 * @date 2013-2-24
	 * @return void
	 * @version 1.0
	 * @throws
	 */

	public void clearUserCache(String uuid);

	/**
	 * <p>
	 * Title: updateChannelCache
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return void
	 * @throws
	 */
	public void updateChannelCache();

	

	
	/**
	* <p>Title: updateSignCache</p>
	* <p>Description:TODO</p>
	* @return void
	* @throws
	*/
	public void updateSignCache();

	/**
	* <p>Title: updateBlackCache</p>
	* <p>Description:TODO</p>
	* @return void
	* @throws
	*/
	public void updateBlackCache(long appId);

	/**
	* <p>Title: updateSspCache</p>
	* <p>Description:TODO</p>
	* @param parseLong
	* @return void
	* @throws
	*/
	public void updateSspCache(long id);
}
