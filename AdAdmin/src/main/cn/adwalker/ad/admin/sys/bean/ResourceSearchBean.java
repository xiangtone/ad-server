package cn.adwalker.ad.admin.sys.bean;

import java.util.Date;

/**
 * 
* <p>Title: ResourceForm</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-3-15
 */
public class ResourceSearchBean {
	/** 主键 */
	private Long id;
	/**  资源名称 */
	private String res_name;
	/**  资源URL */
	private String res_url;
	/**  描述 */
	private String short_desc;
	/** 创建时间 */
	private Date create_time;	
	/** 状态（0：正常，-1：删除）*/
	private Integer status;
	/**  上级ID（如果为0，则为顶级） */
	private Integer parent_id;
	/**  排序字段 */
	private Integer sort;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getRes_url() {
		return res_url;
	}
	public void setRes_url(String res_url) {
		this.res_url = res_url;
	}
	public String getShort_desc() {
		return short_desc;
	}
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
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
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getStat_report_id() {
		return stat_report_id;
	}
	public void setStat_report_id(Integer stat_report_id) {
		this.stat_report_id = stat_report_id;
	}
	
	
}