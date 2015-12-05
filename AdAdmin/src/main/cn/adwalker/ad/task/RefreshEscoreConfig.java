package cn.adwalker.ad.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.adwalker.core.pool.SpringDatePool;
import cn.adwalker.core.spring.AppContext;

/**
 * 刷新配置文件表
 * 
 * @author gary
 * 
 */
public class RefreshEscoreConfig extends QuartzJobBean {

	private static final Logger log = LoggerFactory
			.getLogger(RefreshEscoreConfig.class);

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		ApplicationContext ctx = AppContext.getApplicationContext();
		SpringDatePool springDatePool = (SpringDatePool) ctx
				.getBean("springDatePool");
		log.info("RefreshEscoreConfig begin ..");
		springDatePool.initPlatformConfig();
		try {
			springDatePool.initSDKConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("RefreshEscoreConfig end ..");
	}

}
