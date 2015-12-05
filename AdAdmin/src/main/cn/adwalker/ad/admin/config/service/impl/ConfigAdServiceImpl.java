package cn.adwalker.ad.admin.config.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.model.config.dao.IConfigAlarmDao;
import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.ad.admin.config.service.IConfigAdService;

/**
 * 
 * <p>
 * Title: ConfigAdServiceImpl
 * </p>
 * <p>
 * Description:预警参数设置接口实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author jiangwei
 * @date 2012-9-20
 */
@Service
@Transactional
public class ConfigAdServiceImpl implements IConfigAdService {

	@Resource
	private IConfigAlarmDao configAlarmDao;

	@Override
	public int updateAlarmConfig(List<ConfigPushDelay> list) throws Exception {
		return this.configAlarmDao.updateAlarmConfig(list);
	}

	@Override
	public Map<String, ConfigPushDelay> getAlarmConfig() throws Exception {
		return this.configAlarmDao.getAlarmConfig();
	}
}
