/**
* <p>Title: MaterielChartboostDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-16
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import java.util.List;



import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IMaterielChartboostDao;
import cn.adwalker.ad.dao.domain.MaterielChartboost;

/**
 * <p>Title: MaterielChartboostDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-16
 */
@Repository("materielChartboostDao")
public class MaterielChartboostDaoImpl extends CommonDao implements IMaterielChartboostDao {
	
	/** 
	* <p>Title: getMaterielChartboost</p>
	* <p>Description:TODO</p>
	* @param campaign_id
	* @return
	* @see cn.adwalker.ad.dao.IMaterielChartboostDao#getMaterielChartboost(java.lang.Long)
	*/
	public MaterielChartboost getMaterielChartboost(Long placement_id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from T_PLACEMENT_RES_CHARTBOOST t where t.placement_id = ? ");
		List<MaterielChartboost> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<MaterielChartboost>(MaterielChartboost.class),new Object[]{placement_id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
		
	}
}
