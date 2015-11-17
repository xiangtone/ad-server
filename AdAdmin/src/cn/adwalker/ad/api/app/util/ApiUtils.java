/**
 * <p>Title: CacheUtils.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-3
 * @version 1.0
 */
package cn.adwalker.ad.api.app.util;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.adwalker.core.listener.StartupListener;
import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.thread.ApiSendTask;
import cn.adwalker.core.util.ConfigUtil;
import cn.adwalker.core.util.lang.StringUtil;

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
public abstract class ApiUtils {
	private static Log logger = LogFactory.getLog(StartupListener.class);

	private ApiUtils() {
	}

	/**
	 * 
	 * <p>
	 * Title: sendStaticByHour
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param data
	 * @author cuidd
	 * @date 2013-8-2
	 * @return void
	 * @version 1.0
	 */
	public static void sendStaticByHour(String data) {
		String url = ConfigUtil.getString("api.kuaiyou.data.byhour");
		StringBuilder params = new StringBuilder();
		params.append(
				"sig=" + StringUtil.encode(Signature.signature())
						+ "&res_data=").append(StringUtil.encode(data));
		execute(url, params.toString());
	}

	/**
	 * 
	 * <p>
	 * Title: sendStaticByDay
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param data
	 * @author cuidd
	 * @date 2013-8-9
	 * @return void
	 * @version 1.0
	 */
	public static void sendStaticByDay(String data) {
		String url = ConfigUtil.getString("api.kuaiyou.data.day");
		StringBuilder params = new StringBuilder();
		params.append("sig=" + Signature.signature() + "&res_data=").append(
				StringUtil.encode(data));
		execute(url, params.toString());
	}

	/**
	 * 
	 * <p>
	 * Title: sendChannelEffectByDay
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param data
	 * @author cuidd
	 * @date 2013-9-10
	 * @return void
	 * @version 1.0
	 */
	public static void sendChannelEffectByDay(String data) {
		String url = ConfigUtil.getString("api.kuaiyou.date.channel");
		StringBuilder params = new StringBuilder();
		params.append("sig=" + Signature.signature() + "&res_data=").append(
				StringUtil.encode(data));
		execute(url, params.toString());
	}

	/**
	 * 
	 * <p>
	 * Title: sendFinnanceMony
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param data
	 * @author cuidd
	 * @date 2013-8-9
	 * @return void
	 * @version 1.0
	 */
	public static void sendFinnanceMony(String data) {
		String url = ConfigUtil.getString("api.kuaiyou.date.finance");
		StringBuilder params = new StringBuilder();
		params.append(
				"sig=" + StringUtil.encode(Signature.signature())
						+ "&res_data=").append(StringUtil.encode(data));
		execute(url, params.toString());
	}

	/**
	 * <p>
	 * Title: sendApp
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param xorMapEncrypt
	 * @author cuidd
	 * @date 2013-8-12
	 * @return void
	 * @version 1.0
	 */

	public static void sendApp(String data) {
		String url = ConfigUtil.getString("api.kuaiyou.app.callback");
		StringBuilder params = new StringBuilder();
		logger.info(data);
		params.append(
				"sig=" + StringUtil.encode(Signature.signature())
						+ "&res_data=").append(StringUtil.encode(data));
		execute(url, params.toString());

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
	private static void execute(String url, String param) {
		ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
		threadPool.execute(new ApiSendTask(url, param));
	}

}
