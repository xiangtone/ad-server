package cn.adwalker.ad.control.pool;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.control.dao.ConfigAlarmDao;
import cn.adwalker.ad.control.dao.ConfigEScoreDao;
import cn.adwalker.ad.control.domain.ConfigEScore;
import cn.adwalker.ad.control.domain.ConfigSDK;
import cn.adwalker.ad.control.exception.DatabaseException;


/**
 * spring初始化pool
 * 
 * @author gary
 * 
 */
@Service("springDatePool")
public class SpringDatePool implements InitializingBean {

	private static final Logger log = LoggerFactory
			.getLogger(SpringDatePool.class);

	@Resource
	private ConfigEScoreDao configEScoreDao;

	@Resource
	private ConfigAlarmDao configAlarmDao;

	

	/** 权限子菜单 */
	@SuppressWarnings("unused")
	private ConfigEScore platformConfig;// 平台设置
	@SuppressWarnings("unused")
	private ConfigSDK sdkConfig;

	@Override
	public void afterPropertiesSet() throws Exception {
		init();// 初始化
	}

	private void init() throws Exception {
		log.info("SpringDatePool init..");
		long start = System.currentTimeMillis();
		initPlatformConfig();
		initSDKConfig();

		log.info("SpringDatePool end.. cost time:"
				+ (System.currentTimeMillis() - start));
	}



	public void initSDKConfig() throws Exception {
		sdkConfig = configAlarmDao.getConfig();
	}

	/**
	 * 初始化平台设置
	 */
	public void initPlatformConfig() {
		try {
			platformConfig = configEScoreDao.getPlatformConfig();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	
}
