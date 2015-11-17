/**
 * <p>Title: CacheUtils.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-3
 * @version 1.0
 */
package cn.adwalker.core.util;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.thread.DevEffectTask;

/**
 * <p>
 * Title: CacheUtils
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-3
 */
public abstract class DevIncomeUtils {

	private DevIncomeUtils() {
	}

	public static void query() {

		try {
			Date date = DateUtil.parseDate("2013-03-25");
			for (int i = 0; i < 90; i++) {
				execute(date);
				date = DateUtil.addDays(date, 1);
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Title: execute
	 * </p>
	 * <p>
	 * Description:执行缓存方法
	 * </p>
	 * 
	 * @param url
	 * @author cuidd
	 * @date 2013-7-17
	 * @return void
	 * @version 1.0
	 */
	private static void execute(Date date) {
		ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
		threadPool.execute(new DevEffectTask(date));

	}

}
