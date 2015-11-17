package cn.adwalker.ad.picker.memcached;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.adwalker.ad.util.ConfigUtil;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

@SuppressWarnings("deprecation")
public class MemcachedUtil {
	private static final Logger logger = Logger.getLogger(MemcachedUtil.class);
	private static final String cache_url = ConfigUtil.getString("cache_url");
    private static MemcachedUtil util = null;
	public  static MemCachedClient mcc = new MemCachedClient();

	public synchronized static MemcachedUtil getInstance(){
		if(null==util){
			util = new MemcachedUtil();
		}
		return util;
	}
	static {
        logger.info("building memcache............"+cache_url);
		String[] servers = { cache_url };
		Integer[] weights = { 3 };
		// 获取socke连接池的实例对象
		SockIOPool pool = SockIOPool.getInstance();
		// 设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);
		// 设置初始连接数、最小和最大连接数以及最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);
		// 设置主线程的睡眠时间
		pool.setMaintSleep(30);
		// 设置TCP的参数，连接超时等
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		// 初始化连接池
		pool.initialize();
		// 紧缩设置，超过指定大小（单位为K）的数据都会被紧缩
		mcc.setCompressEnable(true);
		mcc.setCompressThreshold(64 * 1024);
	}

	/**
	 * 保护型构造方法，不允许实例化！
	 */
	protected MemcachedUtil() {
	}

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */

	public boolean add(String key, Object value) {
		return mcc.add(key, value);
	}

	/**
	 * 添加一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @param expiry
	 * @return
	 */
	public boolean add(String key, Object value, Date expiry) {
		return mcc.add(key, value, expiry);
	}

	/**
	 * set一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */

	public boolean set(String key, Object value) {
		return mcc.set(key, value);
	}

	/**
	 * set一个指定的值到缓存中.
	 * 
	 * @param key
	 * @param value
	 * @param expiry
	 * @return
	 */
	public boolean set(String key, Object value, Date expiry) {
		return mcc.set(key, value, expiry);
	}

	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		
		return mcc.get(key);
		
	}
    public boolean delete(String key) {
		return mcc.delete(key);
	}
	@SuppressWarnings("unchecked")
	public <T> T get(String key,Class<T> toType){
		try {
			return (T)mcc.get(key);
		} catch (Exception e) {
			logger.info("Exception error: "+e.getMessage());
		} 
		return null;
	}
	
	/**
	 * 获取当前MemCache的状态.
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getStats() {
		return mcc.stats();
	}

	public boolean replace(String key, Object value) {
		return mcc.replace(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		return mcc.replace(key, value, expiry);
	}

	public boolean clear(String key) {
		return mcc.delete(key);
	}

	public List<String> getAllKeys() {
		List<String> list = new ArrayList<String>();
		Map<String, Map<String, String>> items = mcc.statsItems();
		for (Iterator<String> itemIt = items.keySet().iterator(); itemIt
				.hasNext();) {
			String itemKey = itemIt.next();
			Map<String, String> maps = items.get(itemKey);
			for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt
					.hasNext();) {
				String mapsKey = mapsIt.next();
				String mapsValue = maps.get(mapsKey);
				if (mapsKey.endsWith("number")) {// memcached key 类型
					String[] arr = mapsKey.split(":");
					int slabNumber = Integer.valueOf(arr[1].trim());
					int limit = Integer.valueOf(mapsValue.trim());

					Map<String, Map<String, String>> dumpMaps = mcc
							.statsCacheDump(slabNumber, limit);
					for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt
							.hasNext();) {
						String dumpKey = dumpIt.next();
						Map<String, String> allMap = dumpMaps.get(dumpKey);
						for (Iterator<String> allIt = allMap.keySet()
								.iterator(); allIt.hasNext();) {
							String allKey = allIt.next();
							list.add(allKey.trim());
						}
					}
				}
			}
		}
		return list;
	}


}
