package cn.adwalker.ad.core.pool;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.model.common.dao.ConfigAdwalkerDao;
import cn.adwalker.ad.model.common.domain.ConfigEScore;
import cn.adwalker.ad.model.common.domain.ConfigFinance;

/**
 * spring初始化pool
 * 
 * @author gary
 * 
 */
@Service("springDatePool")
@Scope("singleton")
public class SpringDatePool implements InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(SpringDatePool.class);

	@Resource
	private ConfigAdwalkerDao configEScoreDao;
	private static ConfigEScore platformConfig;// 平台设置
	private static ConfigFinance financeConfig; // 财务设置

	@Override
	public void afterPropertiesSet() throws Exception {
		init();// 初始化
	}

	private void init() {
		log.info("SpringDatePool init..");
		long start = System.currentTimeMillis();

		initPlatformConfig();
		initFinanceConfig();

		log.info("SpringDatePool end.. cost time:" + (System.currentTimeMillis() - start));
	}

	/**
	 * 初始化平台设置
	 */
	public void initPlatformConfig() {
		platformConfig = configEScoreDao.getPlatformConfig();
	}

	/**
	 * 初始化财务设置'
	 */
	public void initFinanceConfig() {
		financeConfig = configEScoreDao.getFinanceConfig(AppConstant.FINANCE_ONLINE_STATUS);
	}

	/**
	 * 获取平台配置文件
	 * 
	 * @return
	 */
	public ConfigEScore getPlatformConfig() {
		return platformConfig;
	}

	/**
	 * 获取财务设置文件
	 * 
	 * @return
	 */
	public ConfigFinance getFinanceConfig() {
		return financeConfig;
	}

}
