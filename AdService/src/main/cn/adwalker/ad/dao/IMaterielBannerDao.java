/**
* <p>Title: IMaterielBannerDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-16
* @version 1.0
*/
package cn.adwalker.ad.dao;

import cn.adwalker.ad.dao.domain.MaterielBanner;

/**
 * <p>Title: IMaterielBannerDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-16
 */
public interface IMaterielBannerDao {

	/**
	* <p>Title: getMateriel</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @return Materiel
	* @throws
	 */
	public MaterielBanner getMaterielBanner(Long placement_id);
}
