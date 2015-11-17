package cn.adwalker.model.finance.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.ICostPlatFormDao;
import cn.adwalker.model.finance.domain.CostPlatForm;

/**
 * <p>
 * Title: CostPlatFormDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-31
 */
@Repository
public class CostPlatFormDaoImpl extends BaseDaoImpl<CostPlatForm> implements ICostPlatFormDao {

	public CostPlatFormDaoImpl() {
		setTableName("T_FINANCE_COST_PLATFORM");
	}

}
