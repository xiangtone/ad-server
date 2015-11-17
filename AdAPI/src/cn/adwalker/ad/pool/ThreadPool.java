package cn.adwalker.ad.pool;
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
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ThreadPool.class);
	private static  ThreadPool instance;
	private ThreadPoolExecutor treadPool;
	private ThreadPool() {
		init();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init() {
		treadPool = new ThreadPoolExecutor(5, // 主线程数
				50, // 最大线程数
				30, // 线程最大空闲时间
				TimeUnit.SECONDS,// 时间单位
				new ArrayBlockingQueue(10000),// 任务队列数
				new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());// 超出队列后的操作抛出异常（有4个状态）
	}
	/**
	 * 获取线程池实例
	 * @return
	 */
	public static ThreadPool getInstance() {
		if (instance == null)
			synchronized (ThreadPool.class) {
				if (instance == null){
					instance = new ThreadPool();
				}
			}
		return instance;
	}
	/**
	 * 线程执行器
	 */
	public ThreadPoolExecutor getThreadPoolExecutor() {
		return treadPool;
	}
	/**
	 * 运行任务
	 * @param task
	 */
	public void execute(Runnable task){
		this.treadPool.execute(task);
	}

}