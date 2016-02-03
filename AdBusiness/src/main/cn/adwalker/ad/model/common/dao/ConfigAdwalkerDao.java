package cn.adwalker.ad.model.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.model.common.domain.ConfigEScore;
import cn.adwalker.ad.model.common.domain.ConfigFinance;

/**
 * @author wjp 行云广告设置
 */
@Repository("configEScoreDao")
public class ConfigAdwalkerDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ConfigEScore getPlatformConfig() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append("  ID as id ");
		sql.append(" ,AD_WALL_SIZE as adWallSize ");
		sql.append(" ,dev_apply_min_money as devApplyMinMoney ");
		sql.append(" ,dev_min_draw_money ");
		sql.append(" ,default_activate_period as defaultActivatePeriod ");
		sql.append(" ,default_ad_index as defaultAdIndex ");
		sql.append(" ,adwall_cam_lowest_price as adwallCamLowestPrice ");
		sql.append(" ,adwall_cam_lowest_day_budget as adwallCamLowestDayBudget ");
		sql.append(" from T_SYS_CONFIG_ESCORE ");
		List<ConfigEScore> list = namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ConfigEScore>(ConfigEScore.class));
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	public ConfigFinance getFinanceConfig(int status) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append("  ID as id ");
		sql.append(" ,uuid_rate as uuidRate ");
		sql.append(" ,platform_rate as platformRate ");
		sql.append(" ,dev_rate as devRate ");
		sql.append(" ,MONEY_SCORE_RATE as moneyScoreRate ");
		sql.append(" ,adv_platform_percentage as advPlatformPercentage ");
		sql.append(" ,uuid_consume_dev_percentage as uuidConsumeDevPercentage ");
		sql.append(" ,status as status ");
		sql.append(" ,create_time as createTime");
		sql.append(" from T_SYS_CONFIG_FINANCE ");
		sql.append(" where status =").append(status);
		List<ConfigFinance> list = namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<ConfigFinance>(ConfigFinance.class));
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

}
