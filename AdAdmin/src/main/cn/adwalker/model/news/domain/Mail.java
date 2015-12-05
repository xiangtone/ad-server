/*
 * NewsNoticeVo.java    
 */
package cn.adwalker.model.news.domain;

import java.text.ParseException;
import java.util.Date;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.core.repository.Entity;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 功能描述<br>
 * 群发邮件vo
 * 
 * @author cai
 */
public class Mail implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 1099182449527113990L;

	/** 主键 */
	private Long id;

	/** 管理员ID */
	private Long manager_id;

	/** 标题 */
	private String title;

	/** 内容 */
	private String content;

	/** 类型 1:开发者 2:广告主 */
	private Integer type;

	/** 更新日期 */
	private Date update_time;

	/** 要发送目标邮箱 */
	private String email;

	public Mail() throws ParseException {
		update_time = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getShowType() {
		if (ObjectUtils.isNotEmpty(type)) {
			if (AppConstant.NEWS_NOTICE_TYPE_NEWS == this.type) {
				return "开发者";
			} else if (AppConstant.NEWS_NOTICE_TYPE_NOTICE == this.type) {
				return "广告主";
			}
		} else {
			return "";
		}
		return content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
