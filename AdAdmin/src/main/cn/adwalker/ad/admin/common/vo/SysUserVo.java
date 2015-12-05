/**
 * 
 */
package cn.adwalker.ad.admin.common.vo;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import cn.adwalker.core.security.EscoreUserDetails;

/**
 * @author wjp 管理员实体
 */
public class SysUserVo implements EscoreUserDetails {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -3371185181811917036L;

	/** 管理员编号 */
	private Long id;

	/** 用户名 */
	private String userName;

	/** 密码 */
	private String passWord;

	/** 真实姓名 */
	private String realName;
	/** 邮箱 */
	private String email;

	/** 电话号码 */
	private String telNum;

	/** 类型，1：超管，0：普通管理员 */
	private int type;

	/** 是否删除 0：正常，1：删除 */
	private int del;

	/** 创建时间 */
	private Date createTime;

	/** 角色名称 */
	private String roleName;

	/** 角色id */
	private Long roleId;

	/** 4:华南,1:华东,2:华北,0:平台 */
	private Integer area_type;
	
	private String roleCode;
	
	


	// ******************************新增属性
	private Set<GrantedAuthority> authorities;

	private String password;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean enabled;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}



	/**
	 * @param roleCode the roleCode to set
	 */
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}



	/**
	 * @param id
	 *            the id to set
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
	 * @param userName
	 *            the userName to set
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
	 * @param passWord
	 *            the passWord to set
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
	 * @param realName
	 *            the realName to set
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
	 * @param telNum
	 *            the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public Integer getArea_type() {
		return area_type;
	}

	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the del
	 */
	public int getDel() {
		return del;
	}

	/**
	 * @param del
	 *            the del to set
	 */
	public void setDel(int del) {
		this.del = del;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAuthorities  (Set<GrantedAuthority> cAuthorities) {
		this.authorities=cAuthorities;
	}
	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getAuthorities
	 * </p>
	 * <p>
	 * Description:获取应用权限
	 * </p>
	 * 
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getPassword
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getUsername
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return userName;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: isAccountNonExpired
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: isAccountNonLocked
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: isCredentialsNonExpired
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return accountNonLocked;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: isEnabled
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @return
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	
}
