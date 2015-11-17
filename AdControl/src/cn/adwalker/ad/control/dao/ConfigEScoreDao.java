package cn.adwalker.ad.control.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.ConfigEScore;
import cn.adwalker.ad.control.exception.DatabaseException;

@Repository("configEScoreDao")
public class ConfigEScoreDao extends BaseDao<ConfigEScore> {
	
	public ConfigEScoreDao() {
		setClazz(ConfigEScore.class);
	}
	
	public ConfigEScore getPlatformConfig() throws DatabaseException {
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
		List<ConfigEScore> list = getAll(sql.toString());
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	
}
