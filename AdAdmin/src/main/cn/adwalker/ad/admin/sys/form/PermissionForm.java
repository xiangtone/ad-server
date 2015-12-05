package cn.adwalker.ad.admin.sys.form;

import java.util.Date;

/**
 * 
 * <p>
 * Title: ResourceForm
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
public class PermissionForm {
	/** 资源名称 */
	private String name;
	/** 资源URL */
	private String code;
	/** 描述 */
	private String note;
	/** 创建时间 */
	private Date create_time;
	/** 状态（0：正常，-1：删除） */
	private Integer order_num;
	/** 报表配置id */
	private Integer stat_report_id;
	/** 权限类型表(0,链接；1表单，3，操作按钮) */
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getStat_report_id() {
		return stat_report_id;
	}

	public void setStat_report_id(Integer stat_report_id) {
		this.stat_report_id = stat_report_id;
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
}