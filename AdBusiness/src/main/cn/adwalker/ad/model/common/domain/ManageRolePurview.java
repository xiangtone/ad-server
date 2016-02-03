/**
 * 
 */
package cn.adwalker.ad.model.common.domain;

import java.util.Date;

/**
 * @author wjp
 * 角色、权限映射关系实体
 */
public class ManageRolePurview {

	/** 主键编号 */
	private Long id;
	
	/** 角色编号 */
	private Long roleId;
	
	/** 权限编号 */
	private Long resId;
	
	/** 创建时间 */
	private Date createTime;

	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the resId
	 */
	public Long getResId() {
		return resId;
	}

	/**
	 * @param resId the resId to set
	 */
	public void setResId(Long resId) {
		this.resId = resId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
