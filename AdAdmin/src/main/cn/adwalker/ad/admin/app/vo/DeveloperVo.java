/**
 * 
 */
package cn.adwalker.ad.admin.app.vo;

import java.util.Date;

/**
 * @author guoyongxiang
 *
 */
public class DeveloperVo {

	/** 应用ID */
	private Long appId;
	
	/** 开发者ID */
	private Long dev_id;
	
	/** 管理员ID */
	private Long managerId;
	
	/** 应用创建时间 */
	private Date createTime;

	/** 累计提现 */
	private Double totalMoney;
	
	/** 余额 */
	private Double balance;
	
	/** 申请提现金额 */
	private Double applyMoney;
	
	/** 提现最小值 */
	private Integer devApplyMinMoney;
	
	/** 联系人姓名 */
	private String realName;
	
	/** 电子邮箱 */
	private String email;
	
	/** 移动电话 */
	private String mobilePhone;
	
	/** QQ */
	private Integer qq;
	
	/** 邮编 */
	private String postCode;
	
	/** 公司地址 */
	private String companyAddress;
	
	/** 公司网址 */
	private String websiteUrl;
	
	/** 旧密码 */
	private String oldpass;
	
	/** 新密码 */
	private String password;
	
	/** 证件类型 */
	private String cardType;
	
	/** 证件号 */
	private String cardCode;
	
	/** 银行名称 */
	private String bankName;
	
	/** 支行名称 */
	private String backSubbranch;
	
	/** 银行账号 */
	private String bankAccount;
	
	/** 当前登录用户名 */
	private String userName;
	
	/** 用户应用数 */
	private Integer appCount;
	
	/**
	 * @return the appId
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}


	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
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
	 * @return the totalMoney
	 */
	public Double getTotalMoney() {
		return totalMoney;
	}

	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * @return the applyMoney
	 */
	public Double getApplyMoney() {
		return applyMoney;
	}

	/**
	 * @param applyMoney the applyMoney to set
	 */
	public void setApplyMoney(Double applyMoney) {
		this.applyMoney = applyMoney;
	}

	/**
	 * @return the devApplyMinMoney
	 */
	public Integer getDevApplyMinMoney() {
		return devApplyMinMoney;
	}

	/**
	 * @param devApplyMinMoney the devApplyMinMoney to set
	 */
	public void setDevApplyMinMoney(Integer devApplyMinMoney) {
		this.devApplyMinMoney = devApplyMinMoney;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return the qq
	 */
	public Integer getQq() {
		return qq;
	}

	/**
	 * @param qq the qq to set
	 */
	public void setQq(Integer qq) {
		this.qq = qq;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the websiteUrl
	 */
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	/**
	 * @param websiteUrl the websiteUrl to set
	 */
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	/**
	 * @return the oldpass
	 */
	public String getOldpass() {
		return oldpass;
	}

	/**
	 * @param oldpass the oldpass to set
	 */
	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the cardCode
	 */
	public String getCardCode() {
		return cardCode;
	}

	/**
	 * @param cardCode the cardCode to set
	 */
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the backSubbranch
	 */
	public String getBackSubbranch() {
		return backSubbranch;
	}

	/**
	 * @param backSubbranch the backSubbranch to set
	 */
	public void setBackSubbranch(String backSubbranch) {
		this.backSubbranch = backSubbranch;
	}

	/**
	 * @return the bankAccount
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount the bankAccount to set
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
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
	 * @return the appCount
	 */
	public Integer getAppCount() {
		return appCount;
	}

	/**
	 * @param appCount the appCount to set
	 */
	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}
	
}
