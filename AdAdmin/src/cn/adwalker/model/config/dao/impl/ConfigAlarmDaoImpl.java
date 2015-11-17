package cn.adwalker.model.config.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.config.AlarmConfigConstants;
import cn.adwalker.model.config.dao.IConfigAlarmDao;
import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.model.config.domain.ConfigSDK;

/**
 * 
 * <p>
 * Title: ConfigAlarmDaoImpl
 * </p>
 * <p>
 * Description:预警dao实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2012-9-20
 */
@Repository("configAlarmDao")
public class ConfigAlarmDaoImpl implements IConfigAlarmDao {

	/**
	 * jdbcTemplate
	 */
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public int updateAlarmConfig(List<ConfigPushDelay> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (ConfigPushDelay configPushDelay : list) {
				if (configPushDelay.getConfigValue() != null) {
					StringBuffer sql = new StringBuffer();
					sql.append(" update T_SYS_CONFIG set ");
					sql.append(" CONFIG_VALUE = ");
					sql.append(configPushDelay.getConfigValue());
					sql.append(" where CONFIG_TYPE = ");
					sql.append("'" + configPushDelay.getConfigType() + "'");
					this.jdbcTemplate.update(sql.toString());
				}
			}
		}
		return 1;
	}

	@Override
	public Map<String, ConfigPushDelay> getAlarmConfig() throws Exception {
		Map<String, ConfigPushDelay> map = null;
		String arr[] = AlarmConfigConstants.getColumns();
		StringBuilder sb = new StringBuilder();
		sb.append("select ID as id,CONFIG_TYPE as configType,CONFIG_VALUE as configValue from T_SYS_CONFIG  where CONFIG_TYPE in(");
		for (int i = 0; i < arr.length; i++) {
			sb.append("'" + arr[i] + "',");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		List<ConfigPushDelay> list = this.jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<ConfigPushDelay>(ConfigPushDelay.class));
		if (list != null && list.size() > 0) {
			map = new HashMap<String, ConfigPushDelay>();
			for (ConfigPushDelay configPushDelay : list) {
				map.put(configPushDelay.getConfigType(), configPushDelay);
			}
		}
		return map;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getConfig
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.config.dao.IConfigAlarmDao#getConfig()
	 */
	@Override
	public ConfigSDK getConfig() throws Exception {
		ConfigSDK entity = null;
		Map<String, ConfigPushDelay> map = getAlarmConfig();
		if (map != null) {
			entity = new ConfigSDK();
			entity.setScore_delay_time(Integer.valueOf(map.get(AlarmConfigConstants.COLUMN_SCORE_DELAY_TIME).getConfigValue()));
		}
		return entity;
	}
}
