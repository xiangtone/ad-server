/**
* <p>Title: PictureInterface.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-14
* @version 1.0
*/
package cn.adwalker.ad.rules.strategy;

import cn.adwalker.ad.cache.element.Advertise;

/**
 * <p>Title: PictureInterface</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-14
 */
public interface StrategyPic {
  
	/**
	* <p>Title: getPicture</p>
	* <p>Description:TODO</p>
	* @param wallType
	* @param imageType
	* @param ad
	* @return
	* @return Ad
	 */
	public  Advertise getPicture(String wallType,String imageType,Advertise ad,String os);
	
	public Advertise setPictureWh(String wallType, String imageType, Advertise ad,String os);
}
