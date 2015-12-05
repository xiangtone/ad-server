/**
 * <p>Title: IBalanceIncomeRelDao.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-8
 * @version 1.0
 */
package cn.adwalker.model.finance.dao;

import cn.adwalker.core.repository.IBaseDao;
import cn.adwalker.model.finance.domain.BalanceIncomeRel;

/**
 * <p>
 * Title: IBalanceIncomeRelDao
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-8
 */
public interface IBalanceIncomeRelDao extends IBaseDao<BalanceIncomeRel> {

	Long vaildate(Long id, Long campaign_id, String start_date,
			String month_end_date) throws Exception;

}
