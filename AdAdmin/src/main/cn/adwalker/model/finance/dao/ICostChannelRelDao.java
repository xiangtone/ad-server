/**
 * <p>Title: ICostChannelRelDao.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-27
 * @version 1.0
 */
package cn.adwalker.model.finance.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.finance.domain.BalanceChannel;
import cn.adwalker.model.finance.domain.CostChannelRel;

/**
 * <p>
 * Title: ICostChannelRelDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-27
 */
public interface ICostChannelRelDao extends IBaseDao<CostChannelRel> {

	/**
	* <p>Title: tranceDate</p>
	* <p>Description:TODO</p>
	* @param entity
	* @author cuidd
	* @date 2013-11-27
	* @return void
	* @version 1.0
	 * @throws Exception 
	*/
	void tranceDate(BalanceChannel entity) throws Exception;
	/**
	* <p>Title: tranceIosDate</p>
	* <p>Description:TODO</p>
	* @param entity
	* @throws Exception
	* @author cuidd
	* @date 2014年8月12日
	* @return void
	* @version 1.0
	 */
	void tranceIosDate(BalanceChannel entity)throws Exception ;

}
