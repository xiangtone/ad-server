package cn.adwalker.model.finance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.IBalanceCampainDao;
import cn.adwalker.model.finance.domain.BalanceCampain;

/**
 * <p>
 * Title: BalanceCampainDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-1
 */
@Repository
public class BalanceCampainDaoImpl extends BaseDaoImpl<BalanceCampain> implements IBalanceCampainDao {

	public BalanceCampainDaoImpl() {
		setTableName("T_FINANCE_BALANCE_CAMPAIN");
	}

}
