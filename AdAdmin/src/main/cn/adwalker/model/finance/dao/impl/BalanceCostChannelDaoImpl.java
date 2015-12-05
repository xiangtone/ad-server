/**
 * <p>Title: BalanceCostChannelDaoImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-10-31
 * @version 1.0
 */
package cn.adwalker.model.finance.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.IBalanceCostChannelDao;
import cn.adwalker.model.finance.domain.BalanceCostChannel;

/**
 * <p>
 * Title: BalanceCostChannelDaoImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-31
 */
@Repository
public class BalanceCostChannelDaoImpl extends BaseDaoImpl<BalanceCostChannel> implements
		IBalanceCostChannelDao {
	public BalanceCostChannelDaoImpl() {
		setTableName("T_FINANCE_BALANCE_COST_CHANNEL");
	}

}
