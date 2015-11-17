/**
* <p>Title: MaterielBannerDaoImpl.java</p>
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
import cn.adwalker.ad.dao.IMaterielBannerDao;
import cn.adwalker.ad.dao.domain.MaterielBanner;

/**
 * <p>Title: MaterielBannerDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-16
 */
@Repository("materielBannerDao")
public class MaterielBannerDaoImpl extends CommonDao implements IMaterielBannerDao {

	
	/** 
	* <p>Title: getMaterielBanner</p>
	* <p>Description:TODO</p>
	* @param campaign_id
	* @return
	* @see cn.adwalker.ad.dao.IMaterielBannerDao#getMaterielBanner(java.lang.Long)
	*/
	@Override
	public MaterielBanner getMaterielBanner(Long placement_id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from T_PLACEMENT_RES_BANNER t where t.placement_id = ?");
		List<MaterielBanner> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<MaterielBanner>(MaterielBanner.class),new Object[]{placement_id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
		
	}
}
