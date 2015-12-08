/**
* <p>Title: IAppBlackCache.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-6-14
* @version 1.0
*/
package cn.adwalker.ad.cache;

import cn.adwalker.ad.bean.AppBlack;

/**
 * <p>Title: IAppBlackCache</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-6-14
 */
public interface IAppBlackCache {
   
	public AppBlack getAppBlack(long appId);
	
	public void replaceAppBlack(long appId);
}
