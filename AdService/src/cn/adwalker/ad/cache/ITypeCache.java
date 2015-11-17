/**
* <p>Title: ITypeCache.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.ad.cache;

import cn.adwalker.ad.cache.element.DevApp;
import cn.adwalker.ad.cache.element.Type1;

/**
 * <p>Title: ITypeCache</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
public interface ITypeCache {

	/**
	* <p>Title: getType</p>
	* <p>Description:TODO</p>
	* @param os
	* @param typeId
	* @return Type1
	 */
	public Type1 getType(String os,int typeId,DevApp app);
	
	public void replaceType(long adId,String os);
	/**
	 * 获取反馈相应广告
	 * @param os
	 * @param typeId
	 * @param app
	 * @return
	 */
	public Type1 getFeedBackType(String os,int typeId,DevApp app);
}
