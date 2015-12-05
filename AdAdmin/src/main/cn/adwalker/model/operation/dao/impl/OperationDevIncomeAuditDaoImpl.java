package cn.adwalker.model.operation.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.model.operation.dao.IOperationDevIncomeAuditDao;
import cn.adwalker.model.operation.domain.IncomeAudit;

/**
 * <p>
 * Title: OperationDevIncomeAuditDaoImpl
 * </p>
 * <p>
 * Description:运营开发者收入审核dao实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-17
 */
@Repository("devIncomeAuditDao")
public class OperationDevIncomeAuditDaoImpl extends BaseDaoImpl implements IOperationDevIncomeAuditDao {

	/**
	 * \(non-Javadoc)
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.IOperationDevIncomeAuditDao.dao.IDevIncomeEffectConfirmDao#findById(java.lang.Long)
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public IncomeAudit findById(Long id) throws Exception {
		String sql = "select app.NAME AS app_name,app.dev_email,app.dev_id AS dev_id,app.dev_status,c.campaign_name,c.campaign_id,f.manager_name,f.manager_time,finance_name,f.id,f.app_id,f.manager_id,f.STATUS AS STATUS,f.create_time,f.finance_id,f.finance_time,f.finance_money,f.dev_cost AS confirm_money,f.effect_num,f.effect_time FROM t_finace_dev_incom_confirm f INNER JOIN v_app_dev app ON f.app_id=app.id INNER JOIN v_ad_campaign c ON f.ad_id=c.id where f.id=?";
		List<IncomeAudit> list = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(IncomeAudit.class), id);
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchDealAdStatus
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param map
	 * @param manageId
	 * @param description
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.IOperationDevIncomeAuditDao.dao.IDevIncomeEffectConfirmDao#batchDealAdStatus(java.util.Map,
	 *      java.lang.Long, java.lang.String)
	 */
	@Override
	public int batchDealAdStatus(List<Long> ids, Integer status, Long manageId, String manageName) throws Exception {
		String sql = "update T_FINACE_DEV_INCOM_CONFIRM set STATUS=?,MANAGER_ID=?,MANAGER_name=?,MANAGER_TIME=? where id=?";
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (Long id : ids) {
			parameters.add(new Object[] { status, manageId, manageName, DateUtil.getDateStringByPattern(new Date(), "yyyy-MM-dd"), id });
		}
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: batchDealAudit
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param ids
	 * @param status
	 * @param financeId
	 * @param string
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.operation.dao.IOperationDevIncomeAuditDao#batchDealAudit(java.util.List,
	 *      java.lang.Integer, java.lang.Long, java.lang.String)
	 */
	@Override
	public int batchDealAudit(List<Long> ids, Integer status, Long financeId, String financeName) throws Exception {
		String sql = "update T_FINACE_DEV_INCOM_CONFIRM set STATUS=?,FINANCE_ID=?,FINANCE_NAME=?,FINANCE_TIME=? where id=?";
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (Long id : ids) {
			parameters.add(new Object[] { status, financeId, financeName, DateUtil.getDateStringByPattern(new Date(), "yyyy-MM-dd"), id });
		}
		this.jdbcTemplate.batchUpdate(sql, parameters);
		return 0;
	}
}