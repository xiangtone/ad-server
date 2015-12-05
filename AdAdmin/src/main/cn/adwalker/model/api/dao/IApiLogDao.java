package cn.adwalker.model.api.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.api.domain.ApiLog;

/**
 * <p>
 * Title: IApiLogDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-16
 */
public interface IApiLogDao extends IBaseDao<ApiLog> {

	public void logSend(String res_data) throws Exception;

}
