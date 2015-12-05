package cn.adwalker.ad.admin.common.form;

import java.util.Date;

/**
* <p>Title: InteriorMailForm</p>
* <p>Description:站内信</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-1-24
 */
public class InteriorMailForm {

	/** 主键 */
	private Long id;
	/** 管理员ID */
	private Long managerId;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 1草稿，2发布', */
	private Integer status;
	/** 开发者id集合 */
	private String dev_id_str;
	/** 发布日期 */
	private Date create_time;
	/** 修改日期 */
	private Date update_time;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDev_id_str() {
		return dev_id_str;
	}
	public void setDev_id_str(String dev_id_str) {
		this.dev_id_str = dev_id_str;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}
