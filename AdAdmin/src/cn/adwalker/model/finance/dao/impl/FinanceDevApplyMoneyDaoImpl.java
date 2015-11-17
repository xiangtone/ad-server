package cn.adwalker.model.finance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.domain.DevApplyMoney;
import cn.adwalker.model.finance.dao.IFinanceDevApplyMoneyDao;

/**
 * 
 * <p>
 * Title: FinanceDevApplyMoneyDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-1-20
 */
@SuppressWarnings("rawtypes")
@Repository("financeDevApplyMoneyDao")
public class FinanceDevApplyMoneyDaoImpl extends BaseDaoImpl implements IFinanceDevApplyMoneyDao {

	/**
	 * @see cn.adwalker.model.app.dao.IOperationDevApplyMoneyDao.escore.server.developer.dao.impl.IDevApplyMoneyDao#getById(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public DevApplyMoney getById(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM v_dev_apply_money WHERE ID = ?");
		return (DevApplyMoney) jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper(DevApplyMoney.class), id);
	}

	/**
	 * @see cn.adwalker.model.app.dao.IOperationDevApplyMoneyDao.escore.server.developer.dao.impl.IDevApplyMoneyDao#insert(cn.adwalker.model.app.domain.DevApplyMoney)
	 */
	public Integer insert(DevApplyMoney devApplyMoney) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO DEV_APPLY_MONEY (");
		sql.append("DEVELOPER_ID,");
		sql.append("DEV_EMAIL,");
		sql.append("CITY,");
		sql.append("APPLY_MONEY,");
		sql.append("STATUS,");
		sql.append("MANAGER_ID,");
		sql.append("MANAGER_MONEY,");
		sql.append("MANAGER_TIME,");
		sql.append("MANAGER_DESC,");
		sql.append("FINANCE_ID,");
		sql.append("FINANCE_MONEY,");
		sql.append("FINANCE_TIME,");
		// sql.append("FINANCE_DESC,");
		// sql.append("FORMER_MONEY,");
		sql.append("BANK_SUBBRANCH,");
		sql.append("BANK_NAME,");
		sql.append("TAX_STATUS,");
		sql.append("BANK_ACCOUNT,");
		sql.append("KAIHU_NAME,");
		sql.append("FINANCE_TAX,");
		sql.append("FINANCE_DUES,");
		// sql.append("REAL_NAME,");
		sql.append("CREATE_TIME");
		sql.append(")");
		sql.append(" VALUES (");
		sql.append(":developerId,");
		sql.append(":devEmail,");
		sql.append(":city,");
		sql.append(":applyMoney,");
		sql.append(":status,");
		sql.append(":managerId,");
		sql.append(":managerMoney,");
		sql.append(":managerTime,");
		sql.append(":managerDesc,");
		sql.append(":financeId,");
		sql.append(":financeMoney,");
		sql.append(":financeTime,");
		// sql.append(":financeDesc,");
		// sql.append(":formerMoney,");
		sql.append(":bank_subbranch,");
		sql.append(":bank_name,");
		sql.append(":tax_status,");
		sql.append(":bank_account,");
		sql.append(":kaihu_name,");
		sql.append(":finance_tax,");
		sql.append(":finance_dues,");
		// sql.append(":realName,");
		sql.append(":createTime");
		sql.append(")");

		return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(devApplyMoney));
	}

	/**
	 * @see cn.adwalker.model.app.dao.IOperationDevApplyMoneyDao.escore.server.developer.dao.impl.IDevApplyMoneyDao#update(cn.adwalker.model.app.domain.DevApplyMoney)
	 */
	public Integer update(DevApplyMoney devApplyMoney) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE USER_DEVELOPER SET ");
		if (ObjectUtils.isNotEmpty(devApplyMoney.getDeveloperId())) {
			sql.append("DEVELOPER_ID = :developerId,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getDevEmail())) {
			sql.append("DEV_EMAIL = :devEmail,");
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
		if (ObjectUtils.isNotEmpty(devApplyMoney.getFinanceDesc())) {
			sql.append("FINANCE_DESC = :financeDesc,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getFormerMoney())) {
			sql.append("FORMER_MONEY = :formerMoney,");
		}
		if (ObjectUtils.isNotEmpty(devApplyMoney.getRealName())) {
			sql.append("REAL_NAME = :realName,");
		}
		sql.append("CREATE_TIME=:createTIme");
		sql.append(" WHERE ID=:id");
		return namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(devApplyMoney));
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
		sql.append("UPDATE DEV_APPLY_MONEY SET ");
		sql.append("STATUS =:status,MANAGER_TIME=:MANAGER_TIME");
		sql.append(" WHERE ID=:id");
		return jdbcTemplate.update(sql.toString(), new Object[] { status, new java.util.Date(), id });
	}
	

	/**
	 * 统计 tag:区分运营(0)和财务(1)和财务下载(2)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DevApplyMoney> findReport(String con, int tag) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (tag == 0) {
			sql.append(" select de.*,de.balance_money as balance, m.real_name as operator from DEV_APPLY_MONEY de left join manage_user m on de.manager_id = m.id where 1=1 ");
		} else if (tag == 1 || tag == 2) {
			sql.append("select de.*,de.balance_money as balance, m.real_name as operator,u.Confirmed_Money as dev_balance from DEV_APPLY_MONEY de left join manage_user m on de.finance_id = m.id left join USER_DEVELOPER u on de.developer_id = u.id where 1=1");
		}
		if (!"".equals(con.trim())) {
			sql.append(con);
		}
		sql.append(" order by de.create_time desc ");
		return this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DevApplyMoney>(DevApplyMoney.class));
	}

	/**
	 * 
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
		// sql.append("BALANCE_MONEY = :balance, ");// 余额
		sql.append("PAY_TYPE = ?, ");
		sql.append(" STATUS = ? ");
		sql.append(" where ID = ?");// 条件
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (DevApplyMoney bean : devApplyMoneyList) {
			parameters.add(new Object[] { bean.getFinanceId(), bean.getFinanceMoney(), bean.getFinanceTime(), bean.getManagerDesc(), bean.getFinance_tax(), bean.getFinance_dues(), bean.getPay_type(), bean.getStatus(), bean.getId() });
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
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
	}

}
