package cn.adwalker.model.finance.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.IFinanceAdvRechargeDao;
import cn.adwalker.model.finance.domain.AdvRechargeLog;

/**
 * @author wjp 广告主支付
 */
@SuppressWarnings("rawtypes")
@Repository("financeAdvRechargeDao")
public class FinanceAdvRechargeDaoImpl extends BaseDaoImpl implements IFinanceAdvRechargeDao {

	public FinanceAdvRechargeDaoImpl() {
		setTableName("T_ADV_RECHARGE_LOG");
	}

	@Override
	public long insert(AdvRechargeLog advRechargeLog) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into T_ADV_RECHARGE_LOG (" + "ID," + "ADV_ID," + "BANK_ID," + "RECHARGE_MONEY," + "TYPE,DESCRIPTION," + "MANAGER_ID," + "RECHARGE_DATE," + "CREATE_TIME)");
		sb.append(" values (:adv_Id,:bankId,:rechargeMoney,:type,:description,:managerId,:rechargeDate,:createTime ) ");
		return insert(sb.toString(), advRechargeLog);
	}
}
