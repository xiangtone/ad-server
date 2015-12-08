/**
* <p>Title: MaterielListDaoImpl.java</p>
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
import cn.adwalker.ad.dao.IMaterielListDao;
import cn.adwalker.ad.dao.domain.MaterielList;

/**
 * <p>Title: MaterielListDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-30
 */
@Repository("materielListDao")
public class MaterielListDaoImpl extends CommonDao implements IMaterielListDao {

	/**  (non-Javadoc)
	* <p>Title: getMaterielList</p>
	* <p>Description:TODO</p>
	* @param placement_id
	* @return
	* @see cn.adwalker.ad.dao.IMaterielListDao#getMaterielList(java.lang.Long)
	*/
	@Override
	public MaterielList getMaterielList(Long placement_id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from T_PLACEMENT_RES_WALL_LIST t where t.placement_id = ? ");
		List<MaterielList> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<MaterielList>(MaterielList.class),new Object[]{placement_id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
