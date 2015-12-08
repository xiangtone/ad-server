/**
* <p>Title: IMaterielScoreDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-30
* @version 1.0
*/
package cn.adwalker.ad.dao;

import cn.adwalker.ad.dao.domain.MaterielScore;

/**
 * <p>Title: IMaterielScoreDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-30
 */
public interface IMaterielScoreDao {
	
	public MaterielScore getMaterielScore(Long placement_id);
}
