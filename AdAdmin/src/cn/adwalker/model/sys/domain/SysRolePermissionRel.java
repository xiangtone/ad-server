package cn.adwalker.model.sys.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: SysRole
 * </p>
 * <p>
 * Description:角色实体
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-11
 */
public class SysRolePermissionRel implements Entity {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -8844207104483606016L;

	/** 角色编号 */
	private Long id;

	/** 角色名称 */
	private String name;

	/** 角色名称 */
	private String code;

	/** 角色名称 */
	private String note;

	/** 角色名称 */
	private Integer order_num;

	/** 创建时间 */
	private Date create_time;

	/** 角色名称 */
	private Long create_user;

	/** 角色名称 */
	private Integer status;
	

	/** 角色名称 */
	private Long update_user;
	
	/** 创建时间 */
	private Date update_time;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(Long update_user) {
		this.update_user = update_user;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
