package cn.adwalker.ad.model.log.domain;

import java.util.Date;

/**
 * 行为日志
 * 
 * 1.登录
 * 
 * @author gary
 * 
 */
public class EScoreActionLog {

	private long userId;// 用户ID
	private String role;// 角色
	private String method;// 方法名称(*.do)
	private String params;// 参数列表
	private String msg;// 备注信息
	private int logLevel;// 日志级别
	private int flag;// 1：成功 -1:失败
	private Date createTime;// 创建时间

	public EScoreActionLog() {
		createTime = new Date();
	}

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
