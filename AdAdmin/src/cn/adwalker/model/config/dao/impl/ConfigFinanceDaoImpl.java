/**
 * 
 */
package cn.adwalker.model.config.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.config.dao.IConfigFinanceDao;

/**
 * 功能概述：<br>
 * 开发者SDK应用实现类    
 * 
 * @author guoyongxiang
 */
@Repository("configFinanceDao")
public class ConfigFinanceDaoImpl implements IConfigFinanceDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public Integer findMoneyScoreRate() {
		String sql = "select t.money_score_rate from T_CONFIG_FINANCE t where t.id=1";
		return jdbcTemplate.queryForInt(sql.toString());
	}

}
