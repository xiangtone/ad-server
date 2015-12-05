/**
 * 
 */
package cn.adwalker.ad.admin.config.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.model.config.dao.IConfigEScoreDao;
import cn.adwalker.model.config.domain.ConfigEScore;
import cn.adwalker.model.config.domain.ConfigFinance;
import cn.adwalker.model.config.domain.ConfigPushDelay;
import cn.adwalker.model.config.domain.ConfigQuickLyTaskFinance;
import cn.adwalker.ad.admin.config.service.IConfigEScoreService;

/**
 * @author wjp
 * 
 */
@Service(value = "configEScoreService")
@Transactional
public class ConfigEScoreServiceImpl implements IConfigEScoreService {

	@Resource
	private IConfigEScoreDao configEScoreDao;

	/**
	 * @param configEScoreDao the configEScoreDao to set
	 */
	public void setConfigEScoreDao(IConfigEScoreDao configEScoreDao) {
		this.configEScoreDao = configEScoreDao;
	}

	@Override
	public int updateById(ConfigEScore configEscore) throws Exception {
		return this.configEScoreDao.updateById(configEscore);
	}

	@Override
	public long insert(ConfigEScore configEscore) throws Exception {
		return configEScoreDao.insert(configEscore);
	}

	@Override
	public ConfigEScore getPlatformConfig() {
		return this.configEScoreDao.getPlatformConfig();
	}

	@Override
	public ConfigFinance getFinanceConfig(int status) {
		return configEScoreDao.getFinanceConfig(status);
	}

	@Override
	public int updateById(ConfigFinance configFinance) throws Exception {
		return this.configEScoreDao.updateById(configFinance);
	}

	@Override
	public ConfigPushDelay getPushDelayConfigTax() throws Exception {
		return this.configEScoreDao.getPushDelayConfigTax();
	}

	@Override
	public int updatePushDelayTax(ConfigPushDelay configPushDelay) throws Exception {
		return this.configEScoreDao.updatePushDelayTax(configPushDelay);

	}

	@Override
	public ConfigQuickLyTaskFinance getQuickLyTask(int status) {
		return configEScoreDao.getQuickLyTask(status);
	}

	@Override
	public int updateQuickLyTask(ConfigQuickLyTaskFinance qt) throws Exception {
		return this.configEScoreDao.updateQuickLyTask(qt);
	}
}
