package cn.adwalker.ad.web.developer.form;

import java.util.Date;

public class DevPassword {

	/** ID */
	private Long id;
	/** 应用创建时间 */
	private Date createTime;
	/** 电子邮箱 */
	private String email;

	/** 旧密码 */
	private String oldpass;

	/** 新密码 */
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}