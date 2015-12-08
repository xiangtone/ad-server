/**
* <p>Title: MaterielScoreDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-30
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IMaterielScoreDao;
import cn.adwalker.ad.dao.domain.MaterielScore;

/**
 * <p>Title: MaterielScoreDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-30
 */
@Repository("materielScoreDao")
public class MaterielScoreDaoImpl extends CommonDao implements IMaterielScoreDao {
	/**  (non-Javadoc)
	* <p>Title: getMaterielScore</p>
	* <p>Description:TODO</p>
	* @param placement_id
	* @return
	* @see cn.adwalker.ad.dao.IMaterielScoreDao#getMaterielScore(java.lang.Long)
	*/
	@Override
	public MaterielScore getMaterielScore(Long placement_id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from T_PLACEMENT_RES_WALL_SCORE t where t.placement_id = ? ");
		List<MaterielScore> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<MaterielScore>(MaterielScore.class),new Object[]{placement_id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
