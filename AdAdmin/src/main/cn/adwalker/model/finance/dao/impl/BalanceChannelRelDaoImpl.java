/**
 * <p>Title: BalancePlatformRelDaoImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-12-6
 * @version 1.0
 */
package cn.adwalker.model.finance.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.IBalanceChannelRelDao;
import cn.adwalker.model.finance.domain.BalancePlatformRel;

/**
 * <p>
 * Title: BalancePlatformRelDaoImpl
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
@Repository
public class BalanceChannelRelDaoImpl extends BaseDaoImpl<BalancePlatformRel>
		implements IBalanceChannelRelDao {
	public BalanceChannelRelDaoImpl() {
		setTableName("T_BALANCE_CHANNEL_REL");
	}

}
