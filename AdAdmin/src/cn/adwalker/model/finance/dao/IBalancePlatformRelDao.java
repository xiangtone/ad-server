/**
 * <p>Title: IBalancePlatformRelDao.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-6
 * @version 1.0
 */
package cn.adwalker.model.finance.dao;

import java.util.List;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.finance.domain.BalancePlatformRel;

/**
 * <p>
 * Title: IBalancePlatformRelDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-12-6
 */
public interface IBalancePlatformRelDao extends IBaseDao<BalancePlatformRel> {
	
	public void batchInsert(List<BalancePlatformRel> list);

}
