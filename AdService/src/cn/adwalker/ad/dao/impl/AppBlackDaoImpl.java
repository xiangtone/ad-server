/**
* <p>Title: AppBlackDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-6-14
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.bean.AppBlack;
import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IAppBlackDao;
import cn.adwalker.ad.picker.statement.Statement;

/**
 * <p>Title: AppBlackDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-6-14
 */
@Repository("appBlackDao")
public class AppBlackDaoImpl extends CommonDao implements IAppBlackDao {

	
	@Override
	public AppBlack getAppBlack(long appId) {
		Statement stms = stmsFactory.createNativeStatement("SELECT application_id as app_id, GROUP_CONCAT(placement_id SEPARATOR  '|') as black FROM t_application_black t  WHERE t.application_id=?"); 
		          stms.addParam(appId);
		          return simpleJdbcTemplate.findObject(stms, AppBlack.class);
		/**
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from T_APP_BLACK t where t.APP_ID= ? ");
		List<AppBlack> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<AppBlack>(AppBlack.class),new Object[]{appId});
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}*/
	}

}
