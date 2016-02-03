package cn.adwalker.ad.log.service;

import cn.adwalker.ad.log.thread.EScoreActionLogThread;
import cn.adwalker.ad.log.thread.pool.ThreadPool;
import cn.adwalker.ad.model.log.domain.EScoreManagerLog;

public class AdwalkerLogger {

	/**
	 * 插入广告行为流水
	 * 
	 * @param userId
	 *            用户ID
	 * @param role
	 *            角色
	 * @param method
	 *            方法（*.do）
	 * @param params
	 *            参数列表
	 * @param message
	 *            备注信息
	 */
	public static void log(long userId, String role, String method, String params, String message) {
		EScoreManagerLog actionLog = new EScoreManagerLog();
		actionLog.setUserId(userId);
		actionLog.setRole(role);
		actionLog.setMethod(method);
		actionLog.setParams(params);
		actionLog.setMsg(message);
		actionLog.setLogLevel(1);
		actionLog.setFlag(1);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreActionLogThread(actionLog));
	}

	/**
	 * 插入广告行为流水错误方法
	 * 
	 * @param userId
	 *            用户ID
	 * @param role
	 *            角色
	 * @param method
	 *            方法（*.do）
	 * @param params
	 *            参数列表
	 * @param message
	 *            备注信息
	 */
	public static void error(long userId, String role, String method, String params, String message) {

		EScoreManagerLog actionLog = new EScoreManagerLog();
		actionLog.setUserId(userId);
		actionLog.setRole(role);
		actionLog.setMethod(method);
		actionLog.setParams(params);
		actionLog.setMsg(message);
		actionLog.setLogLevel(1);
		actionLog.setFlag(-1);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreActionLogThread(actionLog));
	}

	/**
	 * 插入广告行为流水
	 * 
	 * @param userId
	 *            用户ID
	 * @param role
	 *            角色
	 * @param method
	 *            方法（*.do）
	 * @param params
	 *            参数列表
	 * @param message
	 *            备注信息
	 */
	public static void log(long userId, String role, String method, String params, String message, Integer logLevel) {

		EScoreManagerLog actionLog = new EScoreManagerLog();
		actionLog.setUserId(userId);
		actionLog.setRole(role);
		actionLog.setMethod(method);
		actionLog.setParams(params);
		actionLog.setMsg(message);
		actionLog.setLogLevel(logLevel);
		actionLog.setFlag(1);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreActionLogThread(actionLog));
	}

}
