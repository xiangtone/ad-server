/**
 * 
 */
package cn.adwalker.ad.model.common.domain;

import java.util.Date;

/**
 * @author wjp
 * 管理员实体
 */
public class SysUser {

	/** 管理员编号 */
	private Long id;
	
	/** 用户名 */
	private String userName;
	
	/** 密码 */
	private String passWord;
	
	/** 真实姓名 */
	private String realName;
	
	/** 电话号码 */
	private String telNum;
	
	/** 类型，1：超管，0：普通管理员 */
	private Integer type;
	
	/** 是否删除 0：正常，1：正常 */
	private Integer del;
	
	/** 创建时间 */
	private Date createTime;

	
	
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

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}

	/**
	 * @param telNum the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the del
	 */
	public Integer getDel() {
		return del;
	}

	/**
	 * @param del the del to set
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
	
	
}
