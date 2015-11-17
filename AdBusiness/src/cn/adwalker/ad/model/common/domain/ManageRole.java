/**
 * 
 */
package cn.adwalker.ad.model.common.domain;

import java.util.Date;

/**
 * @author wjp
 * 角色实体
 */
public class ManageRole {

	/** 角色编号 */
	private Long id;
	
	/** 角色名称 */
	private String roleName;
	
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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
