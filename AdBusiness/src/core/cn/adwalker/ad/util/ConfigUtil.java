package cn.adwalker.ad.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.PropertyResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取config.properties配置文件 并内置线程2分钟更新一次
 * 
 * @author gary
 * 
 */
public class ConfigUtil {

	private static final Logger log = LoggerFactory.getLogger(ConfigUtil.class);

	private static PropertyResourceBundle bundle;
	/** 属性文件绝对路径 */
	private static String propFilePath = null;
	/** 属性文件的最近更新时间 */
	private static long lastModified = 0L;
	/** 属性文件变动的监视线程 */
	private static PropertyMonitor monitor = null;

	static {
		try {
			java.net.URL url = ConfigUtil.class.getResource("");
			int index = url.getPath().indexOf("/WEB-INF/"); // 取属性文件的绝对路径
			String pathPrefix = url.getPath().substring(0, index + 9).replace("%20", " "); // 替换掉空格
			propFilePath = pathPrefix + "config.properties";

			// 加载属性文件
			FileInputStream fis = new FileInputStream(propFilePath);
			bundle = new PropertyResourceBundle(fis);
			// 启动属性文件监视线程
			monitor = new ConfigUtil().new PropertyMonitor();
			monitor.start();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 查看属性文件是否更新的监视线程, 每隔10秒检查一次该文件的变化, 若有变化则重新加载
	 */
	public class PropertyMonitor extends Thread {
		private long checkInterval = 10000L; // 检查时间间隔: 10秒

		public void run() {
			while (true) {
				try {
					sleep(checkInterval);
				} catch (InterruptedException e) {
					log.error("InterruptedException encountered, exit.");
					return;
				}
				try {
					File file = new File(propFilePath);
					if (file.lastModified() > lastModified) {
						lastModified = file.lastModified(); // 记录最新修改时间
						ConfigUtil.reloadConfig();
					}
				} catch (Exception e) {
					log.error("failed to reload the properties file." + e.getMessage() + ", continue to monitor.");
				}
			}

		}
	}

	/**
	 * 重新加载配置文件config.properties
	 * 
	 * @throws Exception
	 *             加载配置文件时抛出的异常
	 */
	public static void reloadConfig() throws Exception {
		try {
			FileInputStream fis = new FileInputStream(propFilePath);
			bundle = new PropertyResourceBundle(fis);
			log.warn("the property-file been changed.");
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 对外方法
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			String result = bundle.getString(key);
			return result;
		} catch (java.util.MissingResourceException mre) {
			return "";
		}
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			try {
				String asd = getString("gary");
				System.out.println(asd);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}