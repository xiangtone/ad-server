/**
* <p>Title: IDevAddMoneyDao.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author hadoop
* @date 2013-12-2
* @version 1.0
*/
package cn.adwalker.model.log.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.log.domain.DevAddMoneyLog;

/**
 * <p>Title: IDevAddMoneyDao</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    cuidd
 * @date       2013-12-2
 */
public interface IDevAddMoneyDao extends IBaseDao<DevAddMoneyLog> {

	/**
	* <p>Title: batchInsert</p>
	* <p>Description:TODO</p>
	* @param logList
	* @author cuidd
	* @date 2013-12-2
	* @return void
	* @version 1.0
	*/
	
	void batchInsert(List<DevAddMoneyLog> logList);

}
