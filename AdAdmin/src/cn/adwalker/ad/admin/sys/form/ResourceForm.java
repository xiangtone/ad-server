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
public class ResourceForm {
	/** 资源名称 */
	private String name;
	/** 资源URL */
	private String url;
	/** 描述 */
	private String note;
	/** 创建时间 */
	private Date create_time;
	/** 状态（0：正常，-1：删除） */
	private Integer status;
	/** 上级ID（如果为0，则为顶级） */
	private Long parent_id;
	/** 排序字段 */
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
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

}