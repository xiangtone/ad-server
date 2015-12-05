package cn.adwalker.ad.admin.sys.bean;


/**
 * 
* <p>Title: ResourceForm</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-3-15
 */
public class PermissionSearchBean {
	/**  资源名称 */
	private String name;
	/**  资源URL */
	private String code;
	/** 权限类型表(0,链接；1表单，3，操作按钮) */
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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