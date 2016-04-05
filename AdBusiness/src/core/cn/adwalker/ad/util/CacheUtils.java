package cn.adwalker.ad.util;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.ad.core.cache.ClearCacheTask;
import cn.adwalker.ad.core.pool.ThreadPoolUtil;

public abstract class CacheUtils {

	private CacheUtils() {
	}

	public static void updateAdCache(Long adId) {
		String cacheUrl = ConfigUtil.getString("updateCahce.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?adIds=").append(adId);
		execute(params.toString());
	}

	public static void updateConfigCache() {
		String cacheUrl = ConfigUtil.getString("updateConfigFinance.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl);
		execute(params.toString());
	}

	public static void updateAppCache(Long adId) {
		String cacheUrl = ConfigUtil.getString("updateAppCahce.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?appId=").append(adId);
		execute(params.toString());
	}
	public static void updateAppApiCache(Long adId) {
		String cacheUrl = ConfigUtil.getString("updateAppApiCahce.url");
		StringBuffer params = new StringBuffer();
		params.append(cacheUrl).append("?key=memAPIAppRel").append(adId);
		executeApi(params.toString());
	}

	private static void execute(String url) {
		String cacheUrl = ConfigUtil.getString("updateCahce.ip");
		if (!StringUtils.isEmpty(cacheUrl)) {
			String arr[] = cacheUrl.split(",");
			for (int i = 0; i < arr.length; i++) {
				ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
				threadPool.execute(new ClearCacheTask("http://" + arr[i] + "/AdService/" + url));
			}
		}
	}
	
	private static void executeApi(String url) {
		String cacheUrl = ConfigUtil.getString("updateApiCahce.ip");
		if (!StringUtils.isEmpty(cacheUrl)) {
			String arr[] = cacheUrl.split(",");
			for (int i = 0; i < arr.length; i++) {
				ThreadPoolExecutor threadPool = ThreadPoolUtil.getThreadPool();
				threadPool.execute(new ClearCacheTask("http://" + arr[i] + "/AdAPI/" + url));
			}
		}
	}
}
