/**
* <p>Title: CampaginPackageDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-11
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IPlacementPackageDao;
import cn.adwalker.ad.dao.domain.PlacementPackage;


/**
 * <p>Title: CampaginPackageDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-11
 */
@Repository("placementPackageDao")
public class PlacementPackageDaoImpl extends CommonDao implements IPlacementPackageDao {

	
	/**
	* <p>Title: getCampaginPackage</p>
	* <p>Description:TODO</p>
	* @return
	* @see com.adwalker.escore.dao.ICampaginPackageDao#getCampaginPackage()
	*/
	@Override
	public PlacementPackage getPlacementPackage(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from T_PLACEMENT_PACKAGE t where t.ID = ? ");
		List<PlacementPackage> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<PlacementPackage>(PlacementPackage.class),new Object[]{id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
