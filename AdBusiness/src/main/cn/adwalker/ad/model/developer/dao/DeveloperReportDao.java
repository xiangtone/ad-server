package cn.adwalker.ad.model.developer.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.page.dao.BaseDao;
import cn.adwalker.ad.web.developer.vo.DevIncomeReportTemp;

@Repository("developerReportDao")
public class DeveloperReportDao extends BaseDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public DevIncomeReportTemp getSumDevIncomeReportTemp(String where) {
		StringBuilder sql = new StringBuilder("select sum(click) click,sum(cost) cost,sum(pospv) pospv,");
		sql.append(" sum(case when app.os = 'ios' then (case stat.type_id when 1 then click when 0 then ACTIVATE when 4 then click when 5 then  pv end) when app.os='android' then (case stat.type_id when 0 then ACTIVATE when 1 then download when 2 then download when 4 then click when 5 then pv end) end) effect");
		sql.append(" from T_STATIC_APP_BYDAY stat left join t_application app on stat.app_id=app.id");
		sql.append(where);
		return (DevIncomeReportTemp) namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql.toString(), new BeanPropertyRowMapper<DevIncomeReportTemp>(DevIncomeReportTemp.class));
	}

}
