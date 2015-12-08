/**
* <p>Title: EscoreCategoryeDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-28
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.bean.SysCategorye;
import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IEscoreCategoryeDao;

/**
 * <p>Title: EscoreCategoryeDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-28
 */
@Repository("escoreCategoryeDao")
public class EscoreCategoryeDaoImpl extends CommonDao implements IEscoreCategoryeDao {

	
	/**  
	* <p>Title: getEscoreCategorye</p>
	* <p>Description:TODO</p>
	* @param id
	* @return
	* @see cn.adwalker.ad.dao.IEscoreCategoryeDao#getEscoreCategorye(long)
	*/
	@Override
	public SysCategorye getEscoreCategorye(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from T_ESCORE_SORT t where t.id=?");
		List<SysCategorye> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<SysCategorye>(SysCategorye.class),new Object[]{id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
