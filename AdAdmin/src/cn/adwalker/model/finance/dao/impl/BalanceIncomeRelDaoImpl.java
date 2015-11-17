/**
 * <p>Title: BalanceIncomeRelDaoImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-8
 * @version 1.0
 */
package cn.adwalker.model.finance.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.bean.SimpleBean;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.finance.dao.IBalanceIncomeRelDao;
import cn.adwalker.model.finance.domain.BalanceIncomeRel;

/**
 * <p>
 * Title: BalanceIncomeRelDaoImpl
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
@Repository
public class BalanceIncomeRelDaoImpl extends BaseDaoImpl<BalanceIncomeRel>
		implements IBalanceIncomeRelDao {
	public BalanceIncomeRelDaoImpl() {
		setTableName("T_FINANCE_BALANCE_INCOME_REL");
	}

	/**  (non-Javadoc)
	* <p>Title: vaildate</p>
	* <p>Description:TODO</p>
	* @param id
	* @param campaign_id
	* @param start_date
	* @param month_end_date
	* @return
	* @throws Exception
	* @see cn.adwalker.model.finance.dao.IBalanceIncomeRelDao#vaildate(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	*/
	@SuppressWarnings("unchecked")
	@Override
	public Long vaildate(Long id, Long campaign_id, String start_date,
			String month_end_date) throws Exception {
		// 查询结算数据对应的日志
		List<SimpleBean<BigDecimal>> list = (List<SimpleBean<BigDecimal>>) this
				.findAll(
						"select t.id obj from T_PACKAGE_ACTIVATE t,V_PACKAGE_CAMPAIGN_REL v  " +
						"where  t.package_id=v.id  and t.status=? and v.campaign_id=? and t.static_date>=? and t.static_date<=?",
						new Object[]{1,campaign_id,start_date,month_end_date},
						SimpleBean.class);
		if (list != null && list.size() > 0) {
			for (SimpleBean<BigDecimal> bean : list) {
				BalanceIncomeRel entity = new BalanceIncomeRel();
				entity.setBalance_id(id);
				entity.setDetail_id(bean.getObj()!=null?bean.getObj().longValue():null);
				entity.setCreate_time(new Date());
				entity.setStatus(0);
				insert(entity);
			}
		}
		return campaign_id;
	}
}
