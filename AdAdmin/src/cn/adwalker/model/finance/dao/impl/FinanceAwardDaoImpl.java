package cn.adwalker.model.finance.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.model.finance.dao.IFinanceAwardDao;
import cn.adwalker.model.finance.domain.DevFinanceAward;
import cn.adwalker.model.finance.domain.DevFinanceAwardVoLog;

@Repository("iFinanceAwardDao")
public class FinanceAwardDaoImpl implements IFinanceAwardDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public DevFinanceAward findByTime(String currentTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_manage_finance_award t where t.begin_day <='" + currentTime + " 00:00:00') and t.end_day >='" + currentTime + " 23:59:59')");
		List<DevFinanceAward> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DevFinanceAward>(DevFinanceAward.class));
		DevFinanceAward devFinanceAward = null;
		if (objects != null && objects.size() > 0) {
			devFinanceAward =objects.get(0);
			return devFinanceAward;
		}
		return null;
	}

	@Override
	public void insert(DevFinanceAwardVoLog awardVoLog) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_MANAGE_FINANCE_AWARD_LOG(");
		sql.append("dev_id,");
		sql.append("dev_name,");
		sql.append("app_id,");
		sql.append("app_name,");
		sql.append("award_money)");
		sql.append(" values ( ");
		sql.append(":dev_id,");
		sql.append(":dev_name,");
		sql.append(":app_id,");
		sql.append(":app_name,");
		sql.append(":award_money)");
		namedParameterJdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(awardVoLog));
	}

	@Override
	public DevFinanceAwardVoLog findByDevId(Long developerId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_MANAGE_FINANCE_AWARD_LOG t where dev_id = '" + developerId + "'");
		List<DevFinanceAwardVoLog> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DevFinanceAwardVoLog>(DevFinanceAwardVoLog.class));
		DevFinanceAwardVoLog devFinanceAwardVoLog = null;
		if (objects != null && objects.size() > 0) {
			devFinanceAwardVoLog = (DevFinanceAwardVoLog) objects.get(0);
			return devFinanceAwardVoLog;
		}
		return null;
	}

	@Override
	public List<DevFinanceAward> findByDate(String startTime, String endTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_manage_finance_award t where  (t.begin_day >='" + startTime + "' and t.begin_day <='" + endTime + "') or (t.begin_day <='" + startTime + "' and t.end_day >='" + startTime + "')");
		List<DevFinanceAward> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DevFinanceAward>(DevFinanceAward.class));
		List<DevFinanceAward> list = null;
		if (objects != null && objects.size() > 0) {
			list = objects;
			return list;
		}
		return null;
	}

	@Override
	public void insert(DevFinanceAward awardVo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into t_manage_finance_award(");
		sql.append("award_money,");
		sql.append("begin_day,");
		sql.append("end_day)");
		sql.append(" values ( ");
		sql.append(":award_money,");
		sql.append(":begin_day,");
		sql.append(":end_day)");
		jdbcTemplate.update(sql.toString(), new BeanPropertySqlParameterSource(awardVo));
	}

	@Override
	public List<DevFinanceAward> findAll() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_manage_finance_award");
		List<DevFinanceAward> objects = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DevFinanceAward>(DevFinanceAward.class));
		List<DevFinanceAward> list = null;
		if (objects != null && objects.size() > 0) {
			list = objects;
			return list;
		}
		return null;
	}

}
