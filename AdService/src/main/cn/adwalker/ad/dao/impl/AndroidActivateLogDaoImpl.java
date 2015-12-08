/**
* <p>Title: CampaginDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-11
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IAndroidActivateLogDao;
import cn.adwalker.ad.dao.domain.AndroidActivateLog;

/**
 * <p>Title: CampaginDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-11
 */
@Repository
public class AndroidActivateLogDaoImpl extends CommonDao implements IAndroidActivateLogDao {
	
	
	@Override
	public int insert(AndroidActivateLog entity) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into t_log_a_android");
		sql.append(" (mac_address,android_id,adid,bundleid,isroot,imei,userid,imsi,phonenum,create_time) ");
		sql.append(" values (:mac_address,:android_id,:adid,:bundleid,:isroot,:imei,:userid,:imsi,:phonenum,:create_time) ");
		return simpleJdbcTemplate.update(sql.toString(),new BeanPropertySqlParameterSource(entity));
	}
}
