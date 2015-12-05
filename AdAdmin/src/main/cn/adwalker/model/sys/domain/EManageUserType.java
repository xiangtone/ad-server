/**
 * 
 */
package cn.adwalker.model.sys.domain;

/**
 * @author wjp
 *
 */
public enum EManageUserType {

	ADMIN(1, "超级管理员"),COMMON_ADV(0, "普通管理员");
	
	/** type */
	private Integer type;
	/** value */
	private String name;

	private EManageUserType(Integer type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
