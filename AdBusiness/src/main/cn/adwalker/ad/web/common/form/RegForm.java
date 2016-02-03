package cn.adwalker.ad.web.common.form;

import java.util.Date;

public class RegForm {
	/** 主键ID */
	private Long id;

	/** 电子邮箱 */
	private String email;

	/** 密码 */
	private String password;

	/** 帐号状态 0:初始化 1：封号 -2:不可进入开发者中心 */
	private Integer status;
	/** 用户类型 1、广告主 2、开发者 */
	private Integer userType;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
