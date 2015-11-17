package cn.adwalker.ad.control.util;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.ad.control.pool.ThreadPoolUtil;
import cn.adwalker.ad.control.task.ClearCacheTask;

public abstract class CacheUtils {

	private CacheUtils() {
		
	}

	public static void updateAdCache(Long adId) {
		String cacheUrl = ConfigUtil.getString("updateCahce.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?adIds=").append(adId);
		executeService(params.toString());
	}

	public static void updateConfigCache() {
		String cacheUrl = ConfigUtil.getString("updateConfigFinance.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl);
		executeService(params.toString());
	}

	public static void updateAppCache(Long adId) {
		String cacheUrl = ConfigUtil.getString("updateAppCahce.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?appId=").append(adId);
		execute(params.toString());
	}

	//媒体、广告防作弊配置
	public static void updateAdMediaDeleteCache(String value) {
		String cacheUrl = ConfigUtil.getString("updateAdMediaDeleteCache.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?key=").append(value);
		executeApi(params.toString());
	}
	
	private static void executeService(String url) {
		ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
		String cacheUrl = ConfigUtil.getString("updateServiceCahce.ip");
		if (!StringUtils.isEmpty(cacheUrl)) {
			String arr[] = cacheUrl.split(",");
			for (int i = 0; i < arr.length; i++) {
				threadPool.execute(new ClearCacheTask("http://" + arr[i] + "/AdService/" + url));
			}

		}

	}
	
	private static void executeApi(String url) {
		String cacheUrl = ConfigUtil.getString("updateConfigAdvPrice.ip");
		if (!StringUtils.isEmpty(cacheUrl)) {
			String arr[] = cacheUrl.split(",");
			for (int i = 0; i < arr.length; i++) {
				ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
				threadPool.execute(new ClearCacheTask("http://" + arr[i] + "/AdAPI/" + url));
			}
		}
	}
	
	private static void execute(String url) {
		String cacheUrl = ConfigUtil.getString("updateCahce.ip");
		if (!StringUtils.isEmpty(cacheUrl)) {
			String arr[] = cacheUrl.split(",");
			for (int i = 0; i < arr.length; i++) {
				ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
				threadPool.execute(new ClearCacheTask("http://" + arr[i] + "/EScore_Service/" + url));
			}
		}
	}
	
}
