/**
 * 
 */
package cn.adwalker.model.sys.domain;

import cn.adwalker.core.repository.Entity;

/**
 * @author wjp 权限表实体
 */
public class PermissionResRel implements Entity {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -7690021350216662061L;

	/** 权限编号 */
	private Long id;

	/** 权限名称 */
	private Long permission_id;

	/** 权限名称 */
	private Long resource_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Long permission_id) {
		this.permission_id = permission_id;
	}

	public Long getResource_id() {
		return resource_id;
	}

	public void setResource_id(Long resource_id) {
		this.resource_id = resource_id;
	}
}
