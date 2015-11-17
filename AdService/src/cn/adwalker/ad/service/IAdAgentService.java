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

import cn.adwalker.ad.exception.AdwalkerException;
import cn.adwalker.ad.service.vo.AInfoAndroid;
import cn.adwalker.ad.service.vo.DInfoAndroid;

/**
 * <p>Title: IAdWallService</p>
 * <p>Description:广告墙服务组件</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-27
 */
public interface IAdAgentService {
	
	
	public void logD(DInfoAndroid log);
	
	public void logA(AInfoAndroid log) throws AdwalkerException;
	
}
