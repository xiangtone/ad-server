/**
* <p>Title: IAdvertiseDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-14
* @version 1.0
*/
package cn.adwalker.ad.dao;

import java.util.List;

import cn.adwalker.ad.dao.domain.Ad;
import cn.adwalker.ad.dao.domain.AdList;

/**
 * <p>Title: IAdvertiseDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-14
 */
public interface IAdvertiseDao {

	/**
	* <p>Title: getAdvertiseList</p>
	* <p>Description:为了跟缓存解耦，从这个地方做了一次对象转换</p>
	* @return List<Advertise>
	* @throws
	 */
	public List<AdList> getAdvertiseList(String os,long typeid,long appPlacement);

	/**
	* <p>Title: getAdvertise</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @return Adv
	* @throws
	*/
	public Ad getAdvertise(long id);
}
