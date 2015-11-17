/**
* <p>Title: IChannelService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-3-1
* @version 1.0
*/
package cn.adwalker.IOSChannel.service;

import cn.adwalker.IOSChannel.vo.ChannelRequestResult;

/**
 * <p>Title: IChannelService</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-3-1
 */
public interface IChannelService {


	/**
	* <p>Title: saveList</p>
	* <p>Description:TODO</p>
	* @param result
	* @return void
	* @throws
	*/
	public void saveList(ChannelRequestResult result);

	/**
	* <p>Title: sendDate</p>
	* <p>Description:TODO</p>
	* @param result
	* @return void
	* @throws
	*/
	public void sendDate(ChannelRequestResult result);

	/**
	* <p>Title: AdRequestOpenJson</p>
	* <p>Description:TODO</p>
	* @param requestJson
	* @return
	* @return ChannelRequestResult
	* @throws
	*/
	public ChannelRequestResult AdRequestOpenJson(String requestJson);
}
