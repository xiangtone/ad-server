package cn.adwalker.model.finance.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.ICostChannelDao;
import cn.adwalker.model.finance.domain.CostChannel;

/**
 * <p>
 * Title: CostChannelDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-1
 */
@Repository
public class CostChannelDaoImpl extends BaseDaoImpl<CostChannel> implements ICostChannelDao {
	
	public CostChannelDaoImpl() {
		setTableName("T_FINANCE_COST_CHANNEL");
	}

}
