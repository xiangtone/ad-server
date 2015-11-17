/**
 * 
 */
package cn.adwalker.model.log.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * @author wjp 管理员行为日志记录v
 */
public class SysLog implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 1076080901355910942L;

	/** 主键编号 */
	private Long id;

	/** 操作者id */
	private Long userId;

	/** 操作这角色Id */
	private Long roleId;

	/** 方法名称 （*.do） */
	private String method;

	/** 方法名称 （*.do） */
	private Long res_id;

	/** 参数列表 */
	private String params;

	/** 级别 */
	private Integer logLevel;

	/** 备注信息 */
	private String msg;

	/** 操作结果 1：成功，-1：失败 */
	private Integer flag;

	/** 创建时间 */
	private Date createTime;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return the logLevel
	 */
	public Integer getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel
	 *            the logLevel to set
	 */
	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getRes_id() {
		return res_id;
	}

	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}
}
