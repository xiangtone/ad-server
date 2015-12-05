/**
 * 
 */
package cn.adwalker.model.config.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.config.dao.IConfigEScoreDao;
import cn.adwalker.model.config.domain.ConfigEScore;
import cn.adwalker.model.config.domain.ConfigFinance;
import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.model.config.domain.ConfigQuickLyTaskFinance;

/**
 * @author wjp 平台设置
 */
@Repository("configEScoreDao")
public class ConfigEScoreDaoImpl extends BaseDaoImpl<ConfigEScore> implements IConfigEScoreDao {

	public ConfigEScoreDaoImpl() {
		setTableName("T_SYS_CONFIG_ESCORE");
	}

	@Override
	public int deleteById(Long id) throws Exception {
		String sql = " delete from  T_SYS_CONFIG_ESCORE where id = ? ";
		return this.jdbcTemplate.update(sql, id);
	}

	@Override
	public long insert(ConfigEScore configEScore) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_SYS_CONFIG_ESCORE (dev_Apply_Min_Money,DEV_MIN_DRAW_MONEY) ");
		sql.append(" values (:devApplyMinMoney,:dev_min_draw_money) ");
		return insert(sql.toString(), configEScore);
	}

	@Override
	public int updateById(ConfigEScore configEScore) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_SYS_CONFIG_ESCORE set dev_Apply_Min_Money = :devApplyMinMoney ,DEV_MIN_DRAW_MONEY=:dev_min_draw_money  ");
		sql.append(" where id=:id ");
		return super.update(sql.toString(), configEScore);
	}

	@Override
	public ConfigEScore getPlatformConfig() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append("  ID as id ");
		sql.append(" ,AD_WALL_SIZE as adWallSize ");
		sql.append(" ,dev_apply_min_money as devApplyMinMoney ");
		sql.append(" ,default_activate_period as defaultActivatePeriod ");
		sql.append(" ,default_ad_index as defaultAdIndex ");
		sql.append(" ,adwall_cam_lowest_price as adwallCamLowestPrice ");
		sql.append(" ,adwall_cam_lowest_day_budget as adwallCamLowestDayBudget ");
		sql.append(" ,dev_min_draw_money");
		sql.append(" ,AD_WALL_SCORE_DELAY as adwallScoreDelay");
		sql.append(" from T_SYS_CONFIG_ESCORE ");
		List<ConfigEScore> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ConfigEScore>(ConfigEScore.class));
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ConfigFinance getFinanceConfig(int status) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append("  ID as id ");
		// sql.append(" ,uuid_rate as uuidRate ");
		sql.append(" ,platform_rate as platformRate ");
		sql.append(" ,dev_rate as devRate ");
		sql.append(" ,MONEY_SCORE_RATE as moneyScoreRate ");
		sql.append(" ,quickly_task as quickly_task ");
		sql.append(" ,adv_platform_percentage as advPlatformPercentage ");
		sql.append(" ,uuid_consume_dev_percentage as uuidConsumeDevPercentage ");
		sql.append(" ,status as status ");
		sql.append(" ,create_time as createTime");
		sql.append(" from T_SYS_CONFIG_FINANCE ");
		sql.append(" where status =").append(status);
		List<ConfigFinance> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ConfigFinance>(ConfigFinance.class));
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateById(ConfigFinance configFinance) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update t_sys_config_finance set ");
		sql.append(" MONEY_SCORE_RATE = ? ");
		sql.append(" , CREATE_TIME = ? ");
		sql.append(" where ID = ?");
		return jdbcTemplate.update(sql.toString(),configFinance.getMoneyScoreRate(),new Date(),configFinance.getId());
	}

	@Override
	public ConfigPushDelay getPushDelayConfigTax() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select ID as id,CONFIG_TYPE as configType,CONFIG_VALUE as configValue from T_SYS_CONFIG  where CONFIG_TYPE=" + "'FINANCETAX'");
		List<ConfigPushDelay> list = this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ConfigPushDelay>(ConfigPushDelay.class));
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int updatePushDelayTax(ConfigPushDelay configPushDelay) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_SYS_CONFIG set ");
		sql.append(" CONFIG_VALUE = ");
		sql.append("'" + configPushDelay.getConfigValue() + "'");
		sql.append(" where CONFIG_TYPE = ");
		sql.append("'" + configPushDelay.getConfigType() + "'");
		return this.jdbcTemplate.update(sql.toString());
	}

	@Override
	public ConfigQuickLyTaskFinance getQuickLyTask(int status) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append("  ID as id ");
		sql.append(" ,quickLy_task ");
		sql.append(" from T_SYS_CONFIG_FINANCE ");
		sql.append(" where status =").append(status);
		List<ConfigQuickLyTaskFinance> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ConfigQuickLyTaskFinance>(ConfigQuickLyTaskFinance.class));
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateQuickLyTask(ConfigQuickLyTaskFinance qt)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_SYS_CONFIG_FINANCE set ");
		sql.append(" quickly_task = ");
		sql.append(qt.getQuickly_task());
		sql.append(" where id = ");
		sql.append(qt.getId());
		return this.jdbcTemplate.update(sql.toString());
		
		
	}
}
