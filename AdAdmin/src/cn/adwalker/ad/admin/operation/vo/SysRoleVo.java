/**
 * 
 */
package cn.adwalker.ad.admin.operation.vo;

import java.util.Date;

/**
 * @author wjp
 * 角色实体
 */
public class SysRoleVo {

	/** 角色编号 */
	private Long id;
	
	/** 父id，0：顶级 */
	private Integer parentId;
	
	/** 角色名称 */
	private String roleName;
	
	/** 创建时间 */
	private Date createTime;
	/** 是否默认选中 0:否，1：是 */
	private int isChecked;
	
	
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
}
