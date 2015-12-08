/**
* <p>Title: SysConfigFinanceDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-22
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.ISysConfigFinanceDao;
import cn.adwalker.ad.dao.domain.ConfigFinance;

/**
 * <p>Title: SysConfigFinanceDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-22
 */
@Repository("sysConfigFinanceDao")
public class SysConfigFinanceDaoImpl extends CommonDao implements ISysConfigFinanceDao {
	
	/**  (non-Javadoc)
	* <p>Title: getConfigFinance</p>
	* <p>Description:TODO</p>
	* @return
	* @see cn.adwalker.ad.dao.ISysConfigFinanceDao#getConfigFinance()
	*/
	@Override
	public ConfigFinance getConfigFinance() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from T_SYS_CONFIG_FINANCE t where status=1");
		List<ConfigFinance> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<ConfigFinance>(ConfigFinance.class));
		
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
