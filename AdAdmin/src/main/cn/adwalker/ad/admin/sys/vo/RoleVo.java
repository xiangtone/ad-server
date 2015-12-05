/**
 * 
 */
package cn.adwalker.ad.admin.sys.vo;

import java.util.Date;

/**
 * 
* <p>Title: RoleVo</p>
* <p>Description: 角色vo</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2013-7-11
 */
public class RoleVo {

	/** 角色编号 */
	private Long id;
	
	/** 角色名称 */
	private String name;
	
	
	/** 角色名称 */
	private String code;
	
	
	/** 角色名称 */
	private String note;
	
	/** 用户数量 */
	private int userCount;
	
	/** 创建时间 */
	private Date create_time;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return the userCount
	 */
	public int getUserCount() {
		return userCount;
	}

	/**
	 * @param userCount the userCount to set
	 */
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
	
}
