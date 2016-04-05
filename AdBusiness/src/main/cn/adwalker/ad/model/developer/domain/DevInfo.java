package cn.adwalker.ad.model.developer.domain;

import java.util.Date;

public class DevInfo {
	/** 主键ID */
	private Long id;
	/** QQ */
	private String qq;
	/** 真实姓名 */
	private String realName;
	/** 公司名 */
	private String companyName;

	/** 公司地址 */
	private String companyAddress;
	/** 移动电话 */
	private String mobilePhone;
	/** 账户类型 1、个人 2、公司 */
	private Integer type;
	/** 修改时间 */
	private Date updateTime;
	/** 帐号状态 0:初始化 1：封号 -2:不可进入开发者中心 */
	private Integer status;
	
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
