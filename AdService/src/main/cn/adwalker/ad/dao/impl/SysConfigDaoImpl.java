/**
* <p>Title: SysConfigDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-14
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;


import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.ISysConfigDao;
import cn.adwalker.ad.dao.domain.SysConfig;

/**
 * <p>Title: SysConfigDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-14
 */
@Repository("sysConfigDao")
public class SysConfigDaoImpl extends CommonDao implements ISysConfigDao{

	
	
	/**
	* <p>Title: getSysConfig</p>
	* <p>Description:TODO</p>
	* @return
	* @see cn.adwalker.ad.dao.ISysConfigDao#getSysConfig()
	*/
	@Override
	public List<SysConfig> getSysConfig() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_sys_config t ");
		List<SysConfig> list = simpleJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<SysConfig>(SysConfig.class));		
		return list;
	}

}
