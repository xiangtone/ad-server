/**
 * 
 */
package cn.adwalker.model.finance.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.finance.domain.AdvRechargeLog;

/**
 * @author wjp
 * 广告主支付
 */
@SuppressWarnings("rawtypes")
public interface IFinanceAdvRechargeDao extends IBaseDao{

	/**
	 * 插入记录
	 * @param advRechargeLog
	 * @return
	 * @throws Exception
	 */
	public long insert(AdvRechargeLog advRechargeLog) throws Exception;
}
