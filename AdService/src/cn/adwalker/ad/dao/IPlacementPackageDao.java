/**
* <p>Title: ICampaginPackageDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-11
* @version 1.0
*/
package cn.adwalker.ad.dao;

import cn.adwalker.ad.dao.domain.PlacementPackage;



/**
 * <p>Title: ICampaginPackageDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-11
 */
public interface IPlacementPackageDao {

	/**
	* <p>Title: getCampaginPackage</p>
	* <p>Description:TODO</p>
	* @return
	* @return CampaginPackage
	* @throws
	 */
	public PlacementPackage getPlacementPackage(Long id);
}
