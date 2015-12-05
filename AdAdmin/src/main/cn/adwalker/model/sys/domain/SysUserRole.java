/**
 * 
 */
package cn.adwalker.model.sys.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * @author wjp 管理员角、色映射关系实体
 */
public class SysUserRole implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 2110105584492802560L;

	/** 主键编号 */
	private Long id;

	/** 管理员编号 */
	private Long userId;

	/** 角色编号 */
	private Long roleId;

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

}
