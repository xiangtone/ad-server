/*
 * UserDeveloperDAOImpl.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 30-Nov-2011
 */
package cn.adwalker.model.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IDevApplyMoneyDao;
import cn.adwalker.model.app.domain.DevApplyMoney;

/**
 * 功能概述：<br>
 * 开发者提现实现类
 * 
 * @author guoyongxiang
 */
@Repository("devApplyMoneyDao")
public class DevApplyMoneyDaoImpl extends BaseDaoImpl<DevApplyMoney> implements IDevApplyMoneyDao {

	public DevApplyMoneyDaoImpl() {
		setTableName("T_DEV_APPLY_MONEY");
	}
	
	/**
	 * @see cn.adwalker.model.app.dao.IOperationDevApplyMoneyDao.escore.server.developer.dao.impl.IDevApplyMoneyDao#getById(java.lang.Long)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DevApplyMoney getById(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM V_DEV_APPLY_MONEY WHERE ID = ?");
		return (DevApplyMoney) jdbcTemplate.queryForObject(sql.toString(),
				new BeanPropertyRowMapper(DevApplyMoney.class), id);
	}


	/**
	 * @see cn.adwalker.model.app.dao.IOperationDevApplyMoneyDao.escore.server.developer.dao.impl.IDevApplyMoneyDao#insert(cn.adwalker.model.app.domain.DevApplyMoney)
	 */
	@Override
	public long insert(DevApplyMoney devApplyMoney) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO T_DEV_APPLY_MONEY (");
		sql.append("DEV_ID,");
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
		sql.append("BANK_SUBBRANCH,");
		sql.append("BANK_NAME,");
		sql.append("TAX_STATUS,");
		sql.append("BANK_ACCOUNT,");
		sql.append("FINANCE_TAX,");
		sql.append("FINANCE_DUES,");
		sql.append("CREATE_TIME");
		sql.append(")");
		sql.append(" VALUES (");
		sql.append(":developerId,");
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
		sql.append(":bank_subbranch,");
		sql.append(":bank_name,");
		sql.append(":tax_status,");
		sql.append(":bank_account,");
		sql.append(":finance_tax,");
		sql.append(":finance_dues,");
		sql.append(":createTime");
		sql.append(")");
		return insert(sql.toString(), devApplyMoney);
	}

	/**
	 * @see cn.adwalker.model.app.dao.IOperationDevApplyMoneyDao.escore.server.developer.dao.impl.IDevApplyMoneyDao#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws Exception {
		String sql = "DELETE * FROM T_DEV_APPLY_MONEY WHERE ID = ?";
		jdbcTemplate.update(sql.toString(), id);
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
	@Override
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
	@Override
	public List<DevApplyMoney> findReport(String con, int tag) throws Exception {
		StringBuffer sql = new StringBuffer();
		if (tag == 0) {
			sql.append(" select de.*,de.balance_money as balance,manager_name as operator from V_DEV_APPLY_MONEY de where 1=1");
		} else if (tag == 1 || tag == 2) {
			sql.append("select de.*,de.balance_money as balance, manager_name as operator,u.Confirmed_Money as dev_balance from V_DEV_APPLY_MONEY de  left join T_DEVELOPER u on de.dev_id = u.id where 1=1");

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
	@Override
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
		this.jdbcTemplate.batchUpdate(sql.toString(), parameters);
	}

	@Override
	public void batchAuditInOper(List<DevApplyMoney> devApplyMoneyList) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_DEV_APPLY_MONEY set ");
		sql.append("MANAGER_ID = ?, ");
		sql.append("MANAGER_MONEY = ?, ");
		sql.append("MANAGER_TIME = ?, ");
		sql.append("MANAGER_DESC = ?, ");
		sql.append(" STATUS = ? ");
		sql.append(" where ID = ? ");
		List<Object[]> parameters = new ArrayList<Object[]>();
		for (DevApplyMoney bean : devApplyMoneyList) {
			parameters.add(new Object[] { bean.getManagerId(), bean.getManagerMoney(), bean.getManagerTime(), bean.getManagerDesc(), bean.getStatus(), bean.getId() });
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

	@Override
	public List<DevApplyMoney> findAll(String con) {
		StringBuffer sql = new StringBuffer();
		sql.append("select de.*,de.balance_money as balance, manager_name as operator,u.Confirmed_Money as dev_balance from V_DEV_APPLY_MONEY de  left join UT_DEVELOPER u on de.dev_id = u.id where 1=1 ");
		if (!"".equals(con.trim())) {
			sql.append(con);
		}
		sql.append(" order by de.create_time desc ");
		return this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DevApplyMoney>(DevApplyMoney.class));
	}
}
