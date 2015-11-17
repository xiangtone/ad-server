package cn.adwalker.IOSChannel.picker.xmemcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import cn.adwalker.IOSChannel.picker.util.Logger;
import cn.adwalker.ad.util.ConfigUtil;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;



public class XMemcachedUtil {
    private static final Logger logger = Logger.getLogger(XMemcachedUtil.class);
    public static XMemcachedUtil util = null;
	public static MemcachedClient memcachedClient = null;
	public static MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public static void setMemcachedClient(MemcachedClient memcachedClient) {
		XMemcachedUtil.memcachedClient = memcachedClient;
	}
	static {
		logger.logInfo("xmemcached client building....."+ConfigUtil.getString("cache_url"));
		try {
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ConfigUtil.getString("cache_url")));
			memcachedClient = builder.build();
		} catch (IOException e) {
			logger.logError("memcached client build error:"+e.getMessage());
		}
		logger.logInfo("memcached client building done");
	}
	
	public synchronized static XMemcachedUtil getInstance(){
		if(null==util){
			util = new XMemcachedUtil();
		}
		return util;
	}
	
	public boolean add(String key, Integer time, Object value){
		try {
			
			return memcachedClient.add(key, time, value);
		} catch (TimeoutException e) {
			logger.logError("TimeoutException error: "+e.getMessage());
		} catch (InterruptedException e) {
			logger.logError("InterruptedException error: "+e.getMessage());
		} catch (MemcachedException e) {
			logger.logError("MemcachedException error: "+e.getMessage());
		}
		return false;
	}
	public boolean delete(String key){
		try {
			return memcachedClient.delete(key);
		} catch (TimeoutException e) {
			logger.logError("TimeoutException error: "+e.getMessage());
		} catch (InterruptedException e) {
			logger.logError("InterruptedException error: "+e.getMessage());
		} catch (MemcachedException e) {
			logger.logError("MemcachedException error: "+e.getMessage());
		}
		return false;
	}
	public boolean keyExist(String key){
		try {
			return null==memcachedClient.get(key)?false:true;
		} catch (Exception e) {
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public <T> T get(String key,Class<T> toType){
		try {
			return (T)memcachedClient.get(key);
		} catch (TimeoutException e) {
			logger.logError("TimeoutException error: "+e.getMessage());
		} catch (InterruptedException e) {
			logger.logError("InterruptedException error: "+e.getMessage());
		} catch (MemcachedException e) {
			logger.logError("MemcachedException error: "+e.getMessage());
		}
		return null;
	}
	public static void main(String[] args){
        XMemcachedUtil.getInstance().add("luoyou", 3600, "testaaa");
       System.out.println( XMemcachedUtil.getInstance().get("luoyou", String.class));
     }
	
}
