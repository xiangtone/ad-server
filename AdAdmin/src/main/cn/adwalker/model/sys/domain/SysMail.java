/**
 * 
 */
package cn.adwalker.model.sys.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * @author wjp 管理员实体
 */
public class SysMail implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5777387441340179719L;

	/** 管理员编号 */
	private Long id;

	private Long create_user;
	/** 创建时间 */
	private Date createTime;

	/** 用户名 */
	private String title;

	/** 密码 */
	private String content;

	/** 真实姓名 */
	private String receiver;

	private Integer send_status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getSend_status() {
		return send_status;
	}

	public void setSend_status(Integer send_status) {
		this.send_status = send_status;
	}

}
