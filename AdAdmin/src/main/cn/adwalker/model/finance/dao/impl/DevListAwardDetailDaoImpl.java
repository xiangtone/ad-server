package cn.adwalker.model.finance.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.finance.dao.IDevListAwardDetailDao;
import cn.adwalker.model.finance.domain.DevListAwardDetail;
import cn.adwalker.ad.admin.finance.bean.DevListAwardDetailbean;
import cn.adwalker.ad.admin.finance.vo.FinanceIncomeSumVo;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.core.util.lang.ObjectUtils;

@SuppressWarnings("rawtypes")
@Repository("devListAwardDetailDao")
public class DevListAwardDetailDaoImpl extends BaseDaoImpl implements IDevListAwardDetailDao {
	
	public DevListAwardDetailDaoImpl() {
		super();
		this.setTableName("T_FINANCE_DEV_AWARD_PUNISH");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceIncomeSumVo> findSummaryByCondition(DevListAwardDetailbean bean) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select  sum(ROUND(money,2)) as sum_sumMoney  from V_FINANCE_DEV_AWARD_PUNISH where 1=1 and TYPE=0 ");
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
		if (ObjectUtils.isNotEmpty((bean.getActivity_name()))) {
			sql.append(" and NOTE ");
			sql.append(" like '%");
			sql.append(bean.getActivity_name());
			sql.append("%'");
		}

		if (ObjectUtils.isNotEmpty(bean.getActivity_begin())) {
			sql.append(" and ACTIVITY_BEGIN >= '");
			sql.append(bean.getActivity_begin());
			sql.append("'");
		}
		if (ObjectUtils.isNotEmpty(bean.getActivity_end())) {
			sql.append(" and ACTIVITY_END <= '");
			sql.append(bean.getActivity_end());
			sql.append("'");
		}
		sql.append(" order by CREATE_TIME DESC");
		return (List<FinanceIncomeSumVo>) this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(FinanceIncomeSumVo.class));
	}

	@Override
	public int insert(DevListAwardDetail devListAwardDetail) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_FINANCE_DEV_AWARD_PUNISH (DEV_ID,TYPE,MONEY,NOTE,ACTIVITY_BEGIN,ACTIVITY_END,FINANCE_ID,FINANCE_TYPE) ");
		sql.append(" values (,:dev_id,0,:award_money,:activity_name,:activity_begin,:activity_end,:finance_id,:finance_type) ");
		return super.update(sql.toString(), devListAwardDetail);
	}
}
