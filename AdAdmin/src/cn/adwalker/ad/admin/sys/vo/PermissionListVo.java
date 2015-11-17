package cn.adwalker.ad.admin.sys.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <p>
 * Title: ResourceListVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-15
 */
public class PermissionListVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private Long id;
	/** 资源名称 */
	private String name;
	/** 资源URL */
	private String code;
	/** 描述 */
	private String note;
	/** 创建时间 */
	private Date create_time;
	/** 状态（0：正常，-1：删除） */
	private Integer status;
	/** 排序字段 */
	private Integer order_num;
	/** 权限类型表(0,链接；1表单，3，操作按钮) */
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
