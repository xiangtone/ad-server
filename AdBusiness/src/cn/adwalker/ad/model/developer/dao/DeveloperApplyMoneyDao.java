package cn.adwalker.ad.model.developer.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.developer.domain.DevApplyMoney;
import cn.adwalker.ad.model.page.dao.BaseDao;
import cn.adwalker.ad.util.ObjectUtils;
import cn.adwalker.ad.web.common.bean.DevIncomeBean;
import cn.adwalker.ad.web.developer.vo.DevAccountInfoVo;

/**
 * 功能概述：<br>
 * 开发者提现实现类
 */
@Repository("developerApplyMoneyDao")
public class DeveloperApplyMoneyDao extends BaseDao  {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public DevApplyMoney getById(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM T_DEV_APPLY_MONEY WHERE ID = ?");
		return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql.toString(), new BeanPropertyRowMapper<DevApplyMoney>(DevApplyMoney.class), id);
	}

	public Long insert(DevApplyMoney devApplyMoney) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO T_DEV_APPLY_MONEY(");
		sql.append("DEV_ID,");
		sql.append("BANK_CITY,");
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
		sql.append("ACCOUNT_HODER,");
		sql.append("FINANCE_TAX,");
		sql.append("FINANCE_DUES,");
		sql.append("CREATE_TIME");
		sql.append(")");
		sql.append(" VALUES (");
		sql.append(":dev_id,");
		sql.append(":bank_city,");
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
		sql.append(":account_hoder,");
		sql.append(":finance_tax,");
		sql.append(":finance_dues,");
		sql.append(":createTime");
		sql.append(")");

		return super.insert(sql.toString(), devApplyMoney);
	}

	public Integer updateStatus(Long id, Integer status) throws Exception {
		if (id == null || ObjectUtils.isEmpty(status)) {
			return 0;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE T_DEV_APPLY_MONEY SET ");
		sql.append("STATUS=?,MANAGER_TIME=?");
		sql.append(" WHERE ID=?");
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), new Object[] { status, new java.util.Date(), id });
	}

	public Double getApplyingByAppUserId(Long devUserId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(APPLY_MONEY)  from T_DEV_APPLY_MONEY where DEV_ID = ");
		sql.append(devUserId);
		sql.append(" and (STATUS = 0 or STATUS = 1)");
		Double applying = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql.toString(), Double.class);
		if (applying == null) {
			applying = 0D;
		}
		return applying;
	}

	public DevAccountInfoVo findByIdInfo(Long dev_id) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t.TYPE,(SUM(IFNULL(n1.finance_dues,0))+SUM(IFNULL(n1.finance_tax,0))) dues,IFNULL(t.finance_income,0) finance_income,IFNULL(t.month_income,0) month_income,IFNULL(t.confirmed_total,0) total_income,");
		sb.append("IFNULL(t.AWARD_INCOME,0) AWARD_INCOME,SUM(IFNULL(n.apply_money,0)) apply_money,t.total_money,m.confirmMoney confirmMoney ");
		sb.append("FROM t_developer t,(SELECT SUM(apply_money) apply_money FROM T_DEV_APPLY_MONEY WHERE dev_id=? AND STATUS>=0 AND STATUS!=2) n,");
		sb.append("(SELECT SUM(finance_dues) finance_dues,SUM(finance_tax) finance_tax FROM T_DEV_APPLY_MONEY WHERE dev_id=? AND STATUS=2) n1,");
		sb.append("(SELECT dev_id,SUM(confirmMoney) confirmMoney FROM v_finace_dev_incom_confirm WHERE dev_id=? AND STATUS!=2 AND STATUS!=-1) m WHERE t.STATUS!=2 AND t.ID=?");
		
		return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sb.toString(), new BeanPropertyRowMapper<DevAccountInfoVo>(DevAccountInfoVo.class), dev_id, dev_id, dev_id, dev_id);
	}

	public int updateApplyAccount(DevApplyMoney devApplyMoney) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE t_dev_apply_money SET ");
		if(devApplyMoney.getInvoice()==0){
			sql.append("finance_tax = ?,");
		}
		sql.append("invoice = ?,");
		sql.append("status = ?");
		sql.append(" WHERE ID=?");
		if(devApplyMoney.getInvoice()==0){
			return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), new Object[] { devApplyMoney.getFinance_tax(),devApplyMoney.getInvoice(), devApplyMoney.getStatus(), devApplyMoney.getId() });
		}else{
			return namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString(), new Object[] {devApplyMoney.getInvoice(), devApplyMoney.getStatus(), devApplyMoney.getId() });
			
		}
	}

	public List<DevIncomeBean> queryIncomeByDate(Date beginDate, Long userId) throws Exception {
		String dateStr = DateUtil.formatDate(beginDate, "yyyy-MM-dd");
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("SELECT dt.static_date,IFNULL(SUM(t.activate),0) activate,IFNULL(SUM(t.cost_0),0)+IFNULL(SUM(t.cost_1),0)+IFNULL(SUM(t.cost_5),0) sumCost,IFNULL(SUM(t.cost_0),0) cost0,");
		sbuf.append("IFNULL(SUM(t.cost_1),0) cost1,IFNULL(SUM(t.cost_5),0) cost5 FROM (SELECT DISTINCT dt.static_date  FROM t_static_app_byday dt WHERE dt.static_date>=? ) dt LEFT JOIN (SELECT t.static_date,t.activate,t.cost,");
		sbuf.append("CASE t.type_id WHEN 0 THEN t.cost ELSE 0 END cost_0,CASE t.type_id WHEN 1 THEN t.cost ELSE 0 END cost_1,CASE t.type_id WHEN 5 THEN t.cost ELSE 0 END cost_5 ");
		sbuf.append("FROM t_static_app_byday t,t_developer u,t_application a WHERE t.app_id=a.id AND u.id=a.dev_id AND u.id=? AND t.static_date>=?) t ON dt.static_date=t.static_date GROUP BY dt.static_date ORDER BY dt.static_date DESC");
		return namedParameterJdbcTemplate.getJdbcOperations().query(sbuf.toString(), BeanPropertyRowMapper.newInstance(DevIncomeBean.class), new Object[] { dateStr, userId, dateStr });
	}

	public Integer queryAppCount(Long userId, Integer status) throws Exception {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("SELECT COUNT(*) FROM t_application t WHERE t.dev_id=? AND t.status=?");
		return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sbuf.toString(), Integer.class, new Object[] { userId, status });// .queryForObject(sbuf.toString(), arg1, arg2);
	}
}
