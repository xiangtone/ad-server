package cn.adwalker.ad.task;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.adwalker.IOSChannel.service.IDelayActionService;

/**
 * <p>定时批量保存渠道点击</p>
 * @author jief
 *
 */
public class BatchAddTask  extends QuartzJobBean{	
	private Logger logger = Logger.getLogger(BatchAddTask.class);
	private IDelayActionService delayActionService;
	public IDelayActionService getDelayActionService() {
		return delayActionService;
	}
	public void setDelayActionService(IDelayActionService delayActionService) {
		this.delayActionService = delayActionService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)throws JobExecutionException {
		logger.info("beginbatchadd click");
		Integer size = delayActionService.batchAddClick();
		logger.info("beginbatchadd click end..."+size);
	}

}
