/**
 * 
 */
package cn.adwalker.model.common.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.log.domain.SysLog;

/**
 * @author wjp
 *
 */
public interface ISysLogDao extends IBaseDao<SysLog> {

	/**
	 * 添加行为信息
	 * @param log
	 * @return
	 */
	public int save(SysLog log);
	
}
