package cn.adwalker.ad.admin.sys.form;


/**
* <p>Title: SysUserAccoutVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p>   
* @author   lichuang
* @date       2013-4-18
 */
public class UserEditForm {

	/** 管理员编号 */
	private Long id;
	/** 用户名 */
	private String userName;
	/** 真实姓名 */
	private String realName;
	/** 邮箱 */
	private String email;
	/** 区域 */
	private Integer area_type;
	
	private Long role_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getArea_type() {
		return area_type;
	}
	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
}
