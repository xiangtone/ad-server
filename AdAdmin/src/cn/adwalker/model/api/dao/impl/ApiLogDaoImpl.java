package cn.adwalker.model.api.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.api.dao.IApiLogDao;
import cn.adwalker.model.api.domain.ApiLog;

/**
 * <p>
 * Title: ApiLogDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-16
 */
@Repository
public class ApiLogDaoImpl extends BaseDaoImpl<ApiLog> implements IApiLogDao {

	public ApiLogDaoImpl() {
		setTableName("T_API_LOG");
	}

	/**  (non-Javadoc)
	* <p>Title: logSend</p>
	* @param res_data
	 * @throws Exception 
	* @see cn.adwalker.model.api.dao.IApiLogDao#logSend(java.lang.String)
	*/
	@Override
	public void logSend(String res_data) throws Exception {
		ApiLog entity = new ApiLog(res_data, 0);
		insert(entity);
	}
}
