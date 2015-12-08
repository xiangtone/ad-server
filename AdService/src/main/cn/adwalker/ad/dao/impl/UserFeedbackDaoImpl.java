/**
* <p>Title: UserFeedbackDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-15
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IUserFeedbackDao;
import cn.adwalker.ad.dao.domain.UserFeedback;

/**
 * <p>Title: UserFeedbackDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-5-15
 */
@Repository("userFeedbackDao")
public class UserFeedbackDaoImpl extends CommonDao implements IUserFeedbackDao {

	
	/**  
	* <p>Title: addData</p>
	* <p>Description:TODO</p>
	* @param bean
	* @see cn.adwalker.ad.dao.IUserFeedbackDao#addData(cn.adwalker.ad.dao.domain.UserFeedback)
	*/
	@Override
	public int addData(UserFeedback userFeedback) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into t_user_feed_back (ad_id, uuid, version, content, email,type )")
		   .append(" values (:adId,:uuid,:version,:desc,:email,:type) ");

		return simpleJdbcTemplate.update(sql.toString(),new BeanPropertySqlParameterSource(userFeedback));
	}

}
