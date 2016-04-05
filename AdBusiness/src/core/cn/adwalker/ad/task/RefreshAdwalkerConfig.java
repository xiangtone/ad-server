package cn.adwalker.ad.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.adwalker.ad.core.pool.SpringDatePool;
import cn.adwalker.ad.core.spring.AppContext;

/**
 * 刷新行云广告配置文件表
 */
public class RefreshAdwalkerConfig extends QuartzJobBean {

	private static final Logger log = LoggerFactory.getLogger(RefreshAdwalkerConfig.class);
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		ApplicationContext ctx = AppContext.getApplicationContext();
		SpringDatePool springDatePool = (SpringDatePool) ctx.getBean("springDatePool");
		log.info("RefreshEscoreConfig begin ..");
		springDatePool.initPlatformConfig();
		System.out.println("1----->"+springDatePool.toString());
		log.info("RefreshEscoreConfig end ..");
	}

}
