/**
 * 
 */
package cn.adwalker.model.sys.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * @author wjp 权限表实体
 */
public class Permission implements Entity {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1166294224689296961L;

	/** 权限编号 */
	private Long id;

	/** 权限名称 */
	private String name;

	/** 权限url */
	private String code;

	private String note;

	/** 菜单排序 */
	private Integer order_num;

	/** 菜单排序 */
	private Integer status;

	/** 创建时间 */
	private Date create_time;

	private Long create_user;

	private Date update_time;

	private Long update_user;
	
	private Integer type;

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

	public Date getCreate_time() {
		return create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Long getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(Long update_user) {
		this.update_user = update_user;
	}
}
