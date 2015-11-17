/**
* <p>Title: BatchAddTask.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-5-15
* @version 1.0
*/
package cn.adwalker.ad.task;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.adwalker.ad.service.IDelayActionService;
import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.core.spring.AppContext;

/**
 * <p>Title: BatchAddTask</p>
 * <p>Description:TOD</p>
 * <p>Company: adwalker</p>  
 * @author    www.adwalker.cn
 * @date       2013-5-15
 */
public class BatchAddTask extends QuartzJobBean {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(BatchAddTask.class);
	

	/**  (non-Javadoc)
	* <p>Title: executeInternal</p>
	* <p>Description:TODO</p>
	* @param arg0
	* @throws JobExecutionException
	* @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	*/
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		  SimpleDateFormat sdf = new SimpleDateFormat(AppConstant.ISODateNum, Locale.CHINA);
		  java.util.Date nowdate=new java.util.Date();
		  Long keyLong =Long.parseLong(sdf.format(nowdate))-1l;
		  ApplicationContext context=AppContext.getApplicationContext();
		  IDelayActionService delayActionService=context.getBean(IDelayActionService.class);
		  delayActionService.batchAddData(keyLong);
		
	}

}
