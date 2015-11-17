package cn.adwalker.core.log.service;

import java.util.Date;

import cn.adwalker.core.log.thread.SysManagerLogThread;
import cn.adwalker.core.log.thread.pool.ThreadPool;
import cn.adwalker.model.log.domain.SysLog;

public class SysLogger {

	/**
	 * 自身操作
	 * 
	 * @param userId
	 * @param roleId 
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param menu
	 *            系统菜单
	 */
	public static void log(Long userId, Long roleId, String method,
			String params, int logLevel, String msg, String menu) {

		SysLog managerLog = new SysLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(
				new SysManagerLogThread(managerLog));
	}

	

	/**
	 * 不带积分和钱
	 * 
	 * @param userId
	 * @param roleId
	 * @param target
	 * @param toUserId
	 * @param toUserAppId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param formerJson
	 * @param modifyJson
	 * @param menu
	 *            系统菜单
	 */
	public static void log(Long userId, Long roleId,
			 String method, String params, int logLevel,
			String msg) {

		SysLog managerLog = new SysLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(
				new SysManagerLogThread(managerLog));
	}

	/**
	 * 
	 * @param userId
	 *            用户id
	 * @param roleId
	 *            用户角色
	 * @param target
	 *            目标（1：广告主，2：开发者）
	 * @param toUserId
	 *            目标用户id
	 * @param toUserAppId
	 *            目标用户应用id
	 * @param method
	 *            调用方法
	 * @param params
	 *            参数
	 * @param logLevel
	 *            日志级别
	 * @param msg
	 *            备注信息
	 * @param formerMoney
	 *            原始钱
	 * @param modifyMoney
	 *            修改的钱
	 * @param formerScore
	 *            原始积分
	 * @param modifyScore
	 *            修改的积分
	 * @param formerJson
	 *            原始对象的json
	 * @param modifyJson
	 *            修改对象的json
	 * @param menu
	 *            系统菜单
	 */
	public static void error(Long userId, Long roleId, int target,
			Long toUserId, Long toUserAppId, String method, String params,
			int logLevel, String msg, double formerMoney, double modifyMoney,
			double formerScore, double modifyScore, String formerJson,
			String modifyJson, String menu) {

		SysLog managerLog = new SysLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(2);
		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(
				new SysManagerLogThread(managerLog));
	}

	/**
	 * 不带积分和钱
	 * 
	 * @param userId
	 * @param roleId
	 * @param target
	 * @param toUserId
	 * @param toUserAppId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param formerJson
	 * @param modifyJson
	 * @param menu
	 *            系统菜单
	 */
	public static void error(Long userId, Long roleId,String method, String params,
			int logLevel, String msg) {

		SysLog managerLog = new SysLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(
				new SysManagerLogThread(managerLog));
	}

	/**
	 * 自身操作
	 * 
	 * @param userId
	 * @param roleId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param menu
	 *            系统菜单
	 */
	public static void error(Long userId, Long roleId, String method,
			String params, int logLevel, String msg, String menu) {

		SysLog managerLog = new SysLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(
				new SysManagerLogThread(managerLog));
	}
	
	
	
	public static void info(Long userId, Long roleId, String method,
			String params, String msg,Long res_id) {
		SysLog managerLog = new SysLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setMsg(msg);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setRes_id(res_id);
		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(
				new SysManagerLogThread(managerLog));
	}

}
