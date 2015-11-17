/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.channel.form;

import java.util.Date;

/**
* <p>Title: UpdateChannel</p>
* <p>Description:修改渠道</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-16
 */
public class UpdateChannel {
	/** 主键ID */
	private Long id;
	/** 渠道名 */
	private String name;
	/** QQ */
	private String qq;
	/** 平台类型 */
	private String os;
	/** 渠道联系人 */
	private String real_name;
	/** 移动电话 */
	private String phone;
	/** 渠道负责人 */
	private String channe_manager;
	/** 修改时间 */
	private Date update_time;
	/** 合作方式 */
	private Integer channe_mode;
	/** sdk渠道标示 */
	private String marking;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getChanne_manager() {
		return channe_manager;
	}
	public void setChanne_manager(String channe_manager) {
		this.channe_manager = channe_manager;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getChanne_mode() {
		return channe_mode;
	}
	public void setChanne_mode(Integer channe_mode) {
		this.channe_mode = channe_mode;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarking() {
		return marking;
	}
	public void setMarking(String marking) {
		this.marking = marking;
	}
	
	
}
