package cn.adwalker.ad.control.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.config.AlarmConfigConstants;
import cn.adwalker.ad.control.domain.ConfigPushDelay;
import cn.adwalker.ad.control.domain.ConfigSDK;
import cn.adwalker.ad.control.exception.DatabaseException;

@Repository("configAlarmDao")
public class ConfigAlarmDao extends BaseDao<ConfigPushDelay> {
	
	public ConfigAlarmDao() {
		setClazz(ConfigPushDelay.class);
	} 

	public int updateAlarmConfig(List<ConfigPushDelay> list) throws DatabaseException {
		if (list != null && list.size() > 0) {
			for (ConfigPushDelay configPushDelay : list) {
				if (configPushDelay.getConfigValue() != null) {
					StringBuffer sql = new StringBuffer();
					sql.append(" update T_SYS_CONFIG set ");
					sql.append(" CONFIG_VALUE = ");
					sql.append(configPushDelay.getConfigValue());
					sql.append(" where CONFIG_TYPE = ");
					sql.append("'" + configPushDelay.getConfigType() + "'");
					super.namedParameterJdbcTemplate.getJdbcOperations().update(sql.toString());
				}
			}
		}
		return 1;
	}

	public Map<String, ConfigPushDelay> getAlarmConfig() throws DatabaseException {
		Map<String, ConfigPushDelay> map = null;
		String arr[] = AlarmConfigConstants.getColumns();
		StringBuilder sb = new StringBuilder();
		sb.append("select ID as id,CONFIG_TYPE as configType,CONFIG_VALUE as configValue from T_SYS_CONFIG  where CONFIG_TYPE in(");
		for (int i = 0; i < arr.length; i++) {
			sb.append("'" + arr[i] + "',");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		List<ConfigPushDelay> list = getAll(sb.toString());
		if (list != null && list.size() > 0) {
			map = new HashMap<String, ConfigPushDelay>();
			for (ConfigPushDelay configPushDelay : list) {
				map.put(configPushDelay.getConfigType(), configPushDelay);
			}
		}
		return map;

	}

	/**
	* <p>Title: getConfig</p>
	* <p>Description:TODO</p>
	* @return
	* @throws Exception
	* @author cuidd
	* @date 2014年6月18日
	* @return ConfigSDK
	* @version 1.0
	 */
	public ConfigSDK getConfig() throws DatabaseException {
		ConfigSDK entity = null;
		Map<String, ConfigPushDelay> map = getAlarmConfig();
		if (map != null) {
			entity = new ConfigSDK();
			entity.setScore_delay_time(Integer.valueOf(map.get("score_delay_time").getConfigValue()));
		}
		return entity;
	}
	
	
}
