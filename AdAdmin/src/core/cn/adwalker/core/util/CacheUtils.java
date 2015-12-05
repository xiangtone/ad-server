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

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.core.pool.ThreadPoolUtil;
import cn.adwalker.core.thread.ClearCacheTask;

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
public abstract class CacheUtils {

	private CacheUtils() {
	}


	/**
	 * 
	* <p>Title: updateAdCache</p>
	* <p>Description:更新广告缓存</p>
	* @param adId
	* @author cuidd
	* @date 2014-2-10
	* @return void
	* @version 1.0
	 */
	public static void updateAdCache(Long adId) {
		String cacheUrl = ConfigUtil.getString("updateCahce.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?adIds=").append(adId);
		execute(params.toString());
	}

	/**
	 * 
	 * <p>
	 * Title: updateConfigCache
	 * </p>
	 * <p>
	 * Description:更新财务配置缓存
	 * </p>
	 * 
	 * @author cuidd
	 * @date 2013-7-17
	 * @return void
	 * @version 1.0
	 */
	public static void updateConfigCache() {
		String cacheUrl = ConfigUtil.getString("updateConfigFinance.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl);
		execute(params.toString());
	}

	/**
	 * 
	 * <p>
	 * Title: updateAppCache
	 * </p>
	 * <p>
	 * Description:更新应用缓存
	 * </p>
	 * 
	 * @param adId
	 * @author cuidd
	 * @date 2013-7-17
	 * @return void
	 * @version 1.0
	 */
	public static void updateAppCache(Long adId) {
		String cacheUrl = ConfigUtil.getString("updateAppCahce.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?appId=").append(adId);
		execute(params.toString());
	}

	/**
	 * 
	 * <p>
	 * Title: updateConfigBlackCache
	 * </p>
	 * <p>
	 * Description:更新应用黑名单
	 * </p>
	 * 
	 * @author cuidd
	 * @date 2013-7-17
	 * @return void
	 * @version 1.0
	 */
	public static void updateConfigBlackCache(Long app_id) {
		String cacheUrl = ConfigUtil.getString("updateBlackCahce.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?appId=").append(app_id);
		execute(params.toString());
	}

	/**
	 * <p>Title: updateConfigAdvPrice</p>
	 * <p>Description:更新接入单价</p>
	 * @param config_id
	 * @author cuidd
	 * @date 2014年6月24日
	 * @return void
	 * @version 1.0
	 */
	public static void updateConfigAdvPrice(String config_id) {
		String cacheUrl = ConfigUtil.getString("updateConfigAdvPrice.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?key=campaignConfigne_n_1").append(config_id);
		executeApi(params.toString());
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
	private static void execute(String url) {
		ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
		String cacheUrl = ConfigUtil.getString("updateCahce.ip");
		if (!StringUtils.isEmpty(cacheUrl)) {
			String arr[] = cacheUrl.split(",");
			for (int i = 0; i < arr.length; i++) {
				threadPool.execute(new ClearCacheTask("http://" + arr[i]
						+ "/AdService/" + url));
			}

		}

	}
	
	
	/**
	* <p>Title: executeApi</p>
	* <p>Description:TODO</p>
	* @param url
	* @author cuidd
	* @date 2014年6月24日
	* @return void
	* @version 1.0
	 */
			
			
	private static void executeApi(String url) {
		ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
		String cacheUrl = ConfigUtil.getString("updateConfigAdvPrice.ip");
		if (!StringUtils.isEmpty(cacheUrl)) {
			String arr[] = cacheUrl.split(",");
			for (int i = 0; i < arr.length; i++) {
				threadPool.execute(new ClearCacheTask("http://" + arr[i]
						+ "/AdAPI/" + url));
			}

		}

	}
	
	//媒体、广告防作弊配置
	public static void updateAdMediaDeleteCache(String value) {
		String cacheUrl = ConfigUtil.getString("updateAdMediaDeleteCache.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?key=").append(value);
		executeApi(params.toString());
	}
	//方案区域
	public static void updateSchemeDeleteCache(String value) {
		String cacheUrl = ConfigUtil.getString("updateSchemeCache.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?key=").append(value);
		executeApi(params.toString());
	}
	
}
