package cn.adwalker.ad.log.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程池主方法
 * 
 * @author gary
 * 
 */
public class ThreadPool {

	private static final Logger log = LoggerFactory.getLogger(ThreadPool.class);

	private static ThreadPool instance;

	private ThreadPoolExecutor treadPool;

	private ThreadPool() {
		init();
	}

	private void init() {
		treadPool = new ThreadPoolExecutor(5, // 主线程数
				10, // 最大线程数
				0, // 等待时间
				TimeUnit.SECONDS,// 时间单位
				new ArrayBlockingQueue(10000),// 队列数
				new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());// 超出队列后的操作（有4个状态）
		log.info("初始化线程池...");
	}

	public static ThreadPool getInstance() {
		if (instance == null)
			instance = new ThreadPool();
		return instance;
	}

	/**
	 * 线程执行器
	 */
	public ThreadPoolExecutor getThreadPoolExecutor() {
		return treadPool;
	}

}