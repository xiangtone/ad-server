/**
 * 
 */
package cn.adwalker.model.sys.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * @author wjp 权限表实体
 */
public class SysResource implements Entity {

	/** @Fields serialVersionUID : TODO */
	// private static final long serialVersionUID = -5271142196466894824L;

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 4230472897423766864L;

	/** 权限编号 */
	private Long id;

	/** 权限名称 */
	private String name;

	/** 权限url */
	private String url;

	/** 父id，0：顶级 */
	private Long parent_id;

	/** 菜单排序 */
	private Integer order_num;

	/** 报表配置id */
	private Integer stat_report_id;

	/** 菜单排序 */
	private Integer status;

	/** 描述 */
	private String note;

	/** 创建时间 */
	private Date create_time;

	/** 创建时间 */
	private Long create_user;

	/** 创建时间 */
	private Date update_time;

	/** 创建时间 */
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
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

	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
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

	public Integer getStatus() {
		return status;
	}

	public Integer getStat_report_id() {
		return stat_report_id;
	}

	public void setStat_report_id(Integer stat_report_id) {
		this.stat_report_id = stat_report_id;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
