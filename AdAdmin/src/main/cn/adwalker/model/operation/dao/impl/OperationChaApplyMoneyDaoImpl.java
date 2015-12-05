package cn.adwalker.model.operation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.app.domain.ChaApplyMoney;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.model.operation.dao.IOperationChaApplyMoneyDao;
import cn.adwalker.ad.admin.operation.bean.ChaApplyMoneySearchBean;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
* <p>Title: OperationChaApplyMoneyDaoImpl</p>
* <p>Description:渠道提现实现类</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-2-14
 */
@Repository("operationChaApplyMoneyDao")
public class OperationChaApplyMoneyDaoImpl extends BaseDaoImpl implements IOperationChaApplyMoneyDao {


	/**
	 * @see cn.adwalker.model.app.dao.IOperationDevApplyMoneyDao.escore.server.developer.dao.impl.IDevApplyMoneyDao#update(cn.adwalker.model.app.domain.DevApplyMoney)
	 */
	public Integer update(DevApplyMoney devApplyMoney) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE USER_DEVELOPER SET ");
		if (ObjectUtils.isNotEmpty(devApplyMoney.getDeveloperId())) {
			sql.append("DEV_ID = :developerId,");
		}
		// if (ObjectUtils.isNotEmpty(devApplyMoney.getDevEmail())) {
		// sql.append("DEV_EMAIL = :devEmail,");
		// }
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
	public List<ChaApplyMoney> findReport(ChaApplyMoneySearchBean bean, int tag) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (tag == 0) {
			sql.append(" select de.*,de.balance_money as balance,manager_name as operator from V_CHA_APPLY_MONEY de where 1=1");
		} else if (tag == 1 || tag == 2) {
			sql.append("select de.*,de.balance_money as balance, manager_name as operator,u.Confirmed_Money as cha_balance from V_CHA_APPLY_MONEY de  left join T_CHANNEL u on de.cha_id = u.id where 1=1");
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
			if ("CHA_ID".equals(bean.getKeyword())) {
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
		if (ObjectUtils.isNotEmpty((bean.getInvoice_status()))) {
			sql.append(" and de.invoice = '");
			sql.append(bean.getInvoice_status());
			sql.append("'");
		}
		sql.append(" order by de.create_time desc ");
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ChaApplyMoney>(ChaApplyMoney.class));
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
	public void batchAuditInOper(List<ChaApplyMoney> chaApplyMoneyList) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_CHA_APPLY_MONEY set ");
		sql.append("MANAGER_ID = ?, ");
		sql.append("MANAGER_MONEY = ?, ");
		sql.append("MANAGER_TIME = ?, ");
		sql.append("MANAGER_DESC = ?, ");
		sql.append("INVOICE_NAME = ?, ");
		sql.append("INVOICE = ?, ");
		sql.append(" STATUS = ?");
		sql.append(" where ID = ? ");
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (ChaApplyMoney bean : chaApplyMoneyList) {
			parameters.add(new Object[] { bean.getManagerId(), bean.getManagerMoney(), bean.getManagerTime(), bean.getManagerDesc(),bean.getInvoice_name(),bean.getInvoice(), bean.getStatus(), bean.getId() });
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

}
