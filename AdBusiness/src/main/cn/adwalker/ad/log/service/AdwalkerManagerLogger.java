package cn.adwalker.ad.log.service;

import java.util.Date;

import cn.adwalker.ad.log.thread.EScoreManagerLogThread;
import cn.adwalker.ad.log.thread.pool.ThreadPool;
import cn.adwalker.ad.model.log.domain.EScoreManagerLog;

public class AdwalkerManagerLogger {

	/**
	 * 
	 * @param userId 用户id
	 * @param roleId 用户角色
	 * @param target 目标（1：广告主，2：开发者）
	 * @param toUserId 目标用户id
	 * @param toUserAppId 目标用户应用id
	 * @param method 调用方法
	 * @param params 参数
	 * @param logLevel 日志级别
	 * @param msg 备注信息
	 * @param formerMoney 原始钱1
	 * @param modifyMoney 修改的钱
	 * @param formerScore 原始积分
	 * @param modifyScore 修改的积分
	 * @param formerJson 原始对象的json
	 * @param modifyJson 修改对象的json
	 * @param menu 系统菜单
	 */
	public static void log(Long userId, Long roleId, int target , Long toUserId , Long toUserAppId , String method , String params , int logLevel ,String msg,
			double formerMoney ,double modifyMoney ,  double formerScore ,double modifyScore , String formerJson , String modifyJson,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setTarget(target);
		managerLog.setToUserId(toUserId);
		managerLog.setToUserAppId(toUserAppId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setFormerMoney(formerMoney);
		managerLog.setModifyMoney(modifyMoney);
		managerLog.setFormerScore(formerScore);
		managerLog.setModifyScore(modifyScore);
		managerLog.setFormerJson(formerJson);
		managerLog.setModifyJson(modifyJson);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}
	
	/**
	 * 自身操作
	 * @param userId
	 * @param roleId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param menu 系统菜单
	 */
	public static void log(Long userId, Long roleId,String method , String params , int logLevel ,String msg,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}
	
	/**
	 * 不带积分
	 * @param userId
	 * @param roleId
	 * @param target
	 * @param toUserId
	 * @param toUserAppId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param formerMoney
	 * @param modifyMoney
	 * @param formerJson
	 * @param modifyJson
	 * @param menu 系统菜单
	 */
	public static void log(Long userId, Long roleId, int target , Long toUserId , Long toUserAppId , String method , String params , int logLevel ,String msg,
			double formerMoney ,double modifyMoney , String formerJson , String modifyJson,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setTarget(target);
		managerLog.setToUserId(toUserId);
		managerLog.setToUserAppId(toUserAppId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setFormerMoney(formerMoney);
		managerLog.setModifyMoney(modifyMoney);
		managerLog.setFormerJson(formerJson);
		managerLog.setModifyJson(modifyJson);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}
	
	/**
	 * 不带钱
	 * @param userId
	 * @param roleId
	 * @param target
	 * @param toUserId
	 * @param toUserAppId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param formerScore
	 * @param modifyScore
	 * @param formerJson
	 * @param modifyJson
	 * @param fill
	 * @param menu 系统菜单
	 */
	public static void log(Long userId, Long roleId, int target , Long toUserId , Long toUserAppId , String method , String params , int logLevel ,String msg,
			  double formerScore ,double modifyScore , String formerJson , String modifyJson,String fill,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setTarget(target);
		managerLog.setToUserId(toUserId);
		managerLog.setToUserAppId(toUserAppId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setFormerScore(formerScore);
		managerLog.setModifyScore(modifyScore);
		managerLog.setFormerJson(formerJson);
		managerLog.setModifyJson(modifyJson);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}

	/**
	 * 不带积分和钱
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
	 * @param menu 系统菜单
	 */
	public static void log(Long userId, Long roleId, int target , Long toUserId , Long toUserAppId , String method , String params , int logLevel ,String msg,
			 String formerJson , String modifyJson,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setTarget(target);
		managerLog.setToUserId(toUserId);
		managerLog.setToUserAppId(toUserAppId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setFormerJson(formerJson);
		managerLog.setModifyJson(modifyJson);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}
	
	/**
	 * 
	 * @param userId 用户id
	 * @param roleId 用户角色
	 * @param target 目标（1：广告主，2：开发者）
	 * @param toUserId 目标用户id
	 * @param toUserAppId 目标用户应用id
	 * @param method 调用方法
	 * @param params 参数
	 * @param logLevel 日志级别
	 * @param msg 备注信息
	 * @param formerMoney 原始钱
	 * @param modifyMoney 修改的钱
	 * @param formerScore 原始积分
	 * @param modifyScore 修改的积分
	 * @param formerJson 原始对象的json
	 * @param modifyJson 修改对象的json
	 * @param menu 系统菜单
	 */
	public static void error(Long userId, Long roleId, int target , Long toUserId , Long toUserAppId , String method , String params , int logLevel ,String msg,
			double formerMoney ,double modifyMoney ,  double formerScore ,double modifyScore , String formerJson , String modifyJson,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setTarget(target);
		managerLog.setToUserId(toUserId);
		managerLog.setToUserAppId(toUserAppId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setFormerMoney(formerMoney);
		managerLog.setModifyMoney(modifyMoney);
		managerLog.setFormerScore(formerScore);
		managerLog.setModifyScore(modifyScore);
		managerLog.setFormerJson(formerJson);
		managerLog.setModifyJson(modifyJson);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(2);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}

	/**
	 * 不带积分
	 * @param userId
	 * @param roleId
	 * @param target
	 * @param toUserId
	 * @param toUserAppId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param formerMoney
	 * @param modifyMoney
	 * @param formerJson
	 * @param modifyJson
	 * @param menu 系统菜单
	 */
	public static void error(Long userId, Long roleId, int target , Long toUserId , Long toUserAppId , String method , String params , int logLevel ,String msg,
			double formerMoney ,double modifyMoney , String formerJson , String modifyJson,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setTarget(target);
		managerLog.setToUserId(toUserId);
		managerLog.setToUserAppId(toUserAppId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setFormerMoney(formerMoney);
		managerLog.setModifyMoney(modifyMoney);
		managerLog.setFormerJson(formerJson);
		managerLog.setModifyJson(modifyJson);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}
	
	/**
	 * 不带钱
	 * @param userId
	 * @param roleId
	 * @param target
	 * @param toUserId
	 * @param toUserAppId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param formerScore
	 * @param modifyScore
	 * @param formerJson
	 * @param modifyJson
	 * @param fill
	 * @param menu 系统菜单
	 */
	public static void error(Long userId, Long roleId, int target , Long toUserId , Long toUserAppId , String method , String params , int logLevel ,String msg,
			  double formerScore ,double modifyScore , String formerJson , String modifyJson,String fill,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setTarget(target);
		managerLog.setToUserId(toUserId);
		managerLog.setToUserAppId(toUserAppId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setFormerScore(formerScore);
		managerLog.setModifyScore(modifyScore);
		managerLog.setFormerJson(formerJson);
		managerLog.setModifyJson(modifyJson);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}

	/**
	 * 不带积分和钱
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
	 * @param menu 系统菜单
	 */
	public static void error(Long userId, Long roleId, int target , Long toUserId , Long toUserAppId , String method , String params , int logLevel ,String msg,
			 String formerJson , String modifyJson,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setTarget(target);
		managerLog.setToUserId(toUserId);
		managerLog.setToUserAppId(toUserAppId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setFormerJson(formerJson);
		managerLog.setModifyJson(modifyJson);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}
	
	/**
	 * 自身操作
	 * @param userId
	 * @param roleId
	 * @param method
	 * @param params
	 * @param logLevel
	 * @param msg
	 * @param menu 系统菜单
	 */
	public static void error(Long userId, Long roleId,String method , String params , int logLevel ,String msg,String menu) {
		
		EScoreManagerLog managerLog = new EScoreManagerLog();
		managerLog.setUserId(userId);
		managerLog.setRoleId(roleId);
		managerLog.setMethod(method);
		managerLog.setParams(params);
		managerLog.setLogLevel(logLevel);
		managerLog.setMsg(msg);
		managerLog.setCreateTime(new Date());
		managerLog.setFlag(1);
		managerLog.setMenu(menu);

		ThreadPool pool = ThreadPool.getInstance();
		pool.getThreadPoolExecutor().execute(new EScoreManagerLogThread(managerLog));
	}
	
}
