package cn.adwalker.model.finance.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.finance.dao.IFinancePunishDetailDao;
import cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;

@SuppressWarnings("rawtypes")
@Repository("financePunishDetailDao")
public class FinancePunishDetailDaoImpl extends BaseDaoImpl implements IFinancePunishDetailDao {
	
	public FinancePunishDetailDaoImpl() {
		super();
		this.setTableName("T_FINANCE_DEV_AWARD_PUNISH");
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findBySummary
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.finance.dao.IFinancePunishDetailDao#findBySummary(cn.adwalker.ad.admin.finance.bean.DevPunishDetailbean)
	 */
	@Override
	public List<FinanceIncomeSumVo> findBySummary(DevPunishDetailbean bean) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select  sum(ROUND(money,2)) as sum_sumMoney  from V_FINANCE_DEV_AWARD_PUNISH where 1=1 and TYPE=-1 ");
		if (ObjectUtils.isNotEmpty((bean.getDev_id()))) {
			sql.append(" and DEV_ID ");
			sql.append(" = ");
			sql.append(bean.getDev_id());
		}
		if (ObjectUtils.isNotEmpty((bean.getDev_account()))) {
			sql.append(" and DEV_EMAIL ");
			sql.append(" like '%");
			sql.append(bean.getDev_account());
			sql.append("%'");
		}
		if (ObjectUtils.isNotEmpty((bean.getFinance_name()))) {
			sql.append(" and FINANCE_NAME ");
			sql.append(" like '%");
			sql.append(bean.getFinance_name());
			sql.append("%'");
		}
		// 效果发生时间
		if (ObjectUtils.isNotEmpty(bean.getStat_date_begin())) {
			sql.append(" and date_format(CREATE_TIME,'%Y-%m-%d') >='" + bean.getStat_date_begin() + "'");
		}
		if (ObjectUtils.isNotEmpty(bean.getStat_date_end())) {
			sql.append(" and date_format(CREATE_TIME,'%Y-%m-%d') <='" + bean.getStat_date_end() + "'");
		}
		return (List<FinanceIncomeSumVo>) this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<FinanceIncomeSumVo>(FinanceIncomeSumVo.class));
	}
}
