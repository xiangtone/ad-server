/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.ad.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 销售信息VO
 * <p>
 * Title: AdvSalesmanForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-3-29
 */
public class AdAuditLog implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 8655278070637269785L;

	/** 主键ID */
	private Long id;

	/**
	 * 对象id
	 */
	private Long object_id;

	/**
	 * 备注
	 */
	private String note;
	/** 创建时间 */
	private Date create_time;
	/** 创建时间 */
	private Long create_user;
	/** 状态 */
	private Integer ispass;

	/**
	 * 类型0活动审核记录；1投放审核记录
	 */
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getObject_id() {
		return object_id;
	}

	public void setObject_id(Long object_id) {
		this.object_id = object_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public Integer getIspass() {
		return ispass;
	}

	public void setIspass(Integer ispass) {
		this.ispass = ispass;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
