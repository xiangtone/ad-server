/**
 * 
 */
package cn.adwalker.model.news.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 功能概述：<br>
 * 新闻公告实体类
 * 
 * @author guoyongxiang
 */
public class NewsNoticedomain implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -3292118487229285067L;
	/** 主键 */
	private Long id;
	/** 管理员ID */
	private Long manager_id;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 类型 1:新闻 2:公告 */
	private Integer type;
	/** sysdate */
	private Date create_time;
	/** 更新时间 */
	private Date update_time;
	/** 逻辑删除标识 */
	private Integer del;

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

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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

	/**
	 * @return the del
	 */
	public Integer getDel() {
		return del;
	}

	/**
	 * @param del
	 *            the del to set
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
}
