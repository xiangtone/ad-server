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

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.adwalker.ad.util.AppConstant;
import cn.adwalker.ad.util.PListUrl;

/**
 * <p>Title: BatchAddTask</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p>  
 * @author    www.adwalker.cn
 * @date       2013-5-15
 */
public class QueryAppUrlTask extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(QueryAppUrlTask.class);
	

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
		logger.debug("更新应用下载地址.....");
		AppConstant.PLIST_URL=PListUrl.getPlist();
	}

}
