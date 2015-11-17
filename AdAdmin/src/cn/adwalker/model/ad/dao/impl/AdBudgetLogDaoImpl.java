package cn.adwalker.model.ad.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IAdBudgetLogDao;
import cn.adwalker.model.ad.domain.AdSchedule;

/**
 * 
 * <p>
 * Title: AdDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
@Repository("adBudgetLogDao")
public class AdBudgetLogDaoImpl extends BaseDaoImpl<AdSchedule> implements IAdBudgetLogDao {

	public AdBudgetLogDaoImpl() {
		setTableName("T_LOG_AD_BUDGET");
	}
	
}
