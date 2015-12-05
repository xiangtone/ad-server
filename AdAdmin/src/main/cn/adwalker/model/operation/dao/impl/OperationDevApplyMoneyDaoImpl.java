package cn.adwalker.model.operation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.admin.operation.bean.DevApplyMoneySearchBean;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.model.operation.dao.IOperationDevApplyMoneyDao;
import cn.adwalker.model.operation.domain.ConfigPushDelay;

/**
 * 功能概述：<br>
 * 开发者提现实现类
 * 
 * @author guoyongxiang
 */
@Repository("operationDevApplyMoneyDao")
public class OperationDevApplyMoneyDaoImpl extends BaseDaoImpl implements IOperationDevApplyMoneyDao {


	/**
	 * @see cn.adwalker.model.app.dao.IOperationDevApplyMoneyDao.escore.server.developer.dao.impl.IDevApplyMoneyDao#update(cn.adwalker.model.app.domain.DevApplyMoney)
	 */
	public Integer update(DevApplyMoney devApplyMoney) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE USER_DEVELOPER SET ");
		if (ObjectUtils.isNotEmpty(devApplyMoney.getDeveloperId())) {
			sql.append("DEV_ID = :developerId,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getBankId())) {
			sql.append("BANK_ID = :bankId,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getApplyMoney())) {
			sql.append("APPLY_MONEY = :applyMoney,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getStatus())) {
			sql.append("STATUS = :status,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getManagerId())) {
			sql.append("MANAGER_ID = :managerId,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getManagerMoney())) {
			sql.append("MANAGER_MONEY = :managerMoney,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getManagerTime())) {
			sql.append("MANAGER_TIME = :managerTime,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getManagerDesc())) {
			sql.append("MANAGER_DESC = :managerDesc,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getFinanceId())) {
			sql.append("FINANCE_ID = :financeId,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getFinanceMoney())) {
			sql.append("FINANCE_MONEY = :financeMoney,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getFinanceTime())) {
			sql.append("FINANCE_TIME = :financeTime,");
		}
		sql.append("CREATE_TIME=:createTIme");
		sql.append(" WHERE ID=:id");
		return super.update(sql.toString(), devApplyMoney);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateStatus
	 * </p>
	 * <p>
	 * Description:撤销申请处理
	 * </p>
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.app.dao.IDevApplyMoneyDao#updateStatus(java.lang.Long, java.lang.Integer)
	 */
	public Integer updateStatus(Long id, Integer status) throws Exception {
		if (id == null || ObjectUtils.isEmpty(status)) {
			return 0;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE T_DEV_APPLY_MONEY SET ");
		sql.append("STATUS =?,MANAGER_TIME=?");
		sql.append(" WHERE ID=?");
		return jdbcTemplate.update(sql.toString(), new Object[] { status, new java.util.Date(), id });
	}

	/**
	 * 统计 tag:区分运营(0)和财务(1)和财务下载(2)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DevApplyMoney> findReport(DevApplyMoneySearchBean bean, int tag) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (tag == 0) {
			sql.append(" select de.*,de.balance_money as balance,manager_name as operator from V_DEV_APPLY_MONEY de where 1=1");
		} else if (tag == 1 || tag == 2) {
			sql.append("select de.*,de.balance_money as balance, manager_name as operator,u.Confirmed_Money as dev_balance from V_DEV_APPLY_MONEY de  left join T_DEVELOPER u on de.dev_id = u.id where 1=1");
		}
		if (ObjectUtils.isNotEmpty((bean.getId()))) {
			sql.append(" and de.id ");
			sql.append(" like '%");
			sql.append(bean.getId());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty((bean.getOperator()))) {
			sql.append(" and de.manager_name  ");
			sql.append(" like '%");
			sql.append(bean.getOperator());
			sql.append("%'");
		}

		// 申请时间
		if (ObjectUtils.isNotEmpty(bean.getBegin())) {
			sql.append(" and date_format(de.CREATE_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getBegin());
			sql.append("'");
		}

		if (ObjectUtils.isNotEmpty(bean.getEnd())) {
			sql.append(" and date_format(de.CREATE_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getEnd());
			sql.append("'");
		}
		sql.append(" and de.status in (0,1,-1,2,-2,-3) ");
		if (ObjectUtils.isNotEmpty(bean.getKeyword()) && ObjectUtils.isNotEmpty(bean.getValue())) {
			if ("DEV_ID".equals(bean.getKeyword())) {
				sql.append(" and ");
				sql.append(bean.getKeyword());
				sql.append(" = ");
				sql.append(" " + bean.getValue() + " ");
			} else {
				sql.append(" and ");
				sql.append(bean.getKeyword());
				sql.append(" like '%");
				sql.append(bean.getValue());
				sql.append("%'");
			}
		}
		if (ObjectUtils.isNotEmpty(bean.getOperatorBegin())) {
			sql.append(" and date_format(de.MANAGER_TIME, '%Y-%m-%d') >= '");
			sql.append(bean.getOperatorBegin());
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getOperatorEnd())) {
			sql.append(" and date_format(de.MANAGER_TIME, '%Y-%m-%d') <= '");
			sql.append(bean.getOperatorEnd());
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty((bean.getStatus()))) {
			sql.append(" and de.status = '");
			sql.append(bean.getStatus());
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty((bean.getTax_status()))) {
			sql.append(" and de.tax_status = '");
			sql.append(bean.getTax_status());
			sql.append("'");
		}
		sql.append(" order by de.create_time desc ");
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DevApplyMoney>(DevApplyMoney.class));
	}

	/**
	 * @see cn.adwalker.model.app.dao.IDevApplyMoneyDao#getByAppUserId(java.lang.Long)
	 */
	public Double getApplyingByAppUserId(Long devUserId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(APPLY_MONEY)  from T_DEV_APPLY_MONEY where DEV_ID = ");
		sql.append(devUserId);
		sql.append(" and (STATUS = 0 or STATUS = 1)");
		Double applying = jdbcTemplate.queryForObject(sql.toString(), Double.class);
		if (applying == null) {
			applying = 0D;
		}
		return applying;

	}

	@Override
	public void batchAuditInFinance(List<DevApplyMoney> devApplyMoneyList) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_DEV_APPLY_MONEY set ");
		sql.append("FINANCE_ID = ?, ");// 审核人id
		sql.append("FINANCE_MONEY = ?, ");// 确认的钱数
		sql.append("FINANCE_TIME = ?, ");// 审核时间
		sql.append("MANAGER_DESC = ?, ");// 说明
		sql.append("FINANCE_TAX = ?, ");// 税率
		sql.append("FINANCE_DUES = ?, ");// 手续费
		sql.append("BALANCE_MONEY = ?, ");// 余额
		sql.append("PAY_TYPE = ?, ");
		sql.append(" STATUS = ? ");
		sql.append(" where ID = ? ");// 条件
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (DevApplyMoney bean : devApplyMoneyList) {
			parameters.add(new Object[] { bean.getFinanceId(), bean.getFinanceMoney(), bean.getFinanceTime(), bean.getManagerDesc(), bean.getFinance_tax(), bean.getFinance_dues(), bean.getBalance(), bean.getPay_type(), bean.getStatus(), bean.getId() });
		}
		jdbcTemplate.batchUpdate(sql.toString(), parameters);
	}

	@Override
	public void batchAuditInOper(List<DevApplyMoney> devApplyMoneyList) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_DEV_APPLY_MONEY set ");
		sql.append("MANAGER_ID = ?, ");
		sql.append("FINANCE_TAX = ?, ");
		sql.append("MANAGER_MONEY = ?, ");
		sql.append("MANAGER_TIME = ?, ");
		sql.append("MANAGER_DESC = ?, ");
		sql.append(" STATUS = ?");
		sql.append(" where ID = ? ");
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (DevApplyMoney bean : devApplyMoneyList) {
			parameters.add(new Object[] { bean.getManagerId(),bean.getFinance_tax(),bean.getManagerMoney(), bean.getManagerTime(), bean.getManagerDesc(), bean.getStatus(), bean.getId() });
		}
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
	}

	@Override
	public void batchFinance_TaxAndDues(List<DevApplyMoney> updateList) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_DEV_APPLY_MONEY set ");
		sql.append("FINANCE_TAX = ?, ");// 税率
		sql.append("FINANCE_DUES = ? ");// 手续费
		sql.append(" where ID = ? ");// 条件
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (DevApplyMoney bean : updateList) {
			parameters.add(new Object[] { bean.getFinance_tax(), bean.getFinance_dues(), bean.getId() });
		}
		jdbcTemplate.batchUpdate(sql.toString(), parameters);
	}
	
	@Override
	public Double getTax(Double preTax) {
		Double tax = 0d;
		Double taxRate = 1d;
		String sql = ("select ID as id,CONFIG_TYPE as configType,CONFIG_VALUE as configValue from T_SYS_CONFIG  where CONFIG_TYPE=" + "'FINANCETAX'");
		List<ConfigPushDelay> list = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<ConfigPushDelay>(ConfigPushDelay.class));
			taxRate = Double.parseDouble(list.get(0).getConfigValue());
 			taxRate = taxRate / 100;
			if (preTax <= 800) {
				tax = 0d;
			} else if (preTax > 800 ) {
				tax = (preTax - 800) *taxRate;
			} 
		
		return tax;
	}
}
