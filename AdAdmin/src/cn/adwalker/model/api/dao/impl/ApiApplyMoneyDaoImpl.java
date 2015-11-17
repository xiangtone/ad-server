package cn.adwalker.model.api.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.api.dao.IApiApplyMoneyDao;
import cn.adwalker.model.api.domain.ApiApplyMoney;

/**
 * <p>
 * Title: ApiLogDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-16
 */
@Repository
public class ApiApplyMoneyDaoImpl extends BaseDaoImpl<ApiApplyMoney> implements IApiApplyMoneyDao {

	public ApiApplyMoneyDaoImpl() {
		setTableName("T_API_APPLYMONEY");
	}
}
