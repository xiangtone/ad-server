package cn.adwalker.ad.task;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.adwalker.ad.admin.task.service.IiosTaskService;
import cn.adwalker.core.spring.AppContext;

/**
 * <p>
 * Title: DataMonitorTask
 * </p>
 * <p>
 * Description:定时数据监控
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-8-22
 */
public class IosDataTask extends QuartzJobBean {

	private static final Logger log = LoggerFactory
			.getLogger(IosDataTask.class);

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: executeInternal
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param arg0
	 * @throws JobExecutionException
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		ApplicationContext ctx = AppContext.getApplicationContext();
		IiosTaskService service = (IiosTaskService) ctx
				.getBean(IiosTaskService.class);
		log.info("IosDataTask begin ..");
		try {
			service.doTask(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("IosDataTask end ..");
	}

}
