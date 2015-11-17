/**
* <p>Title: IAdvertisementService.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-3-1
* @version 1.0
*/
package cn.adwalker.IOSChannel.service;

import java.util.Map;

import cn.adwalker.IOSChannel.vo.ChannelRequestResult;

/**
 * <p>Title: IAdvertisementService</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-3-1
 */
public interface IAdvertisementService {

	/**
	* <p>Title: sendComfirmDate</p>
	* <p>Description:TODO</p>
	* @param vo
	* @return void
	* @throws
	*/
	public void sendComfirmDate(ChannelRequestResult vo);
	
	/**
	* <p>Title: callbackActivateCheck</p>
	* <p>Description:激活回调审核</p>
	* @param vo
	* @return
	* @return Map
	* @throws
	*/
	public Map<String, String> callbackActivateCheck(ChannelRequestResult result);

}
