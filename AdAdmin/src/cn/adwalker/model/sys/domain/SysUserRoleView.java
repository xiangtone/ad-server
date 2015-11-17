package cn.adwalker.model.sys.domain;
/**
* <p>Title: SysUserRoleView</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-27
 */
public class SysUserRoleView {

	private Long id;
	
	private Long role_id;
	
	private String name;
	
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
