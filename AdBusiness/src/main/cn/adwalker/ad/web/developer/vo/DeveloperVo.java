/**
 * 
 */
package cn.adwalker.ad.web.developer.vo;

import java.io.File;
import java.util.Date;

/**
 * @author guoyongxiang
 * 
 */
public class DeveloperVo {

	/** ID */
	private Long id;

	/** 应用ID */
	private Long appId;

	/** 应用名称 */
	private String name;

	/** 应用创建时间 */
	private Date createTime;

	/** 搜索类型 */
	private String searchType;

	/** 搜索内容 */
	private String searchText;

	/** 累计积分 */
	private Double totalScore;

	/** 累计提现 */
	private Double totalMoney;

	/** 余额 */
	private Double balance;

	/** 申请提现金额 */
	private Double applyMoney = 0.00;

	/**  开发者账号提款最低额度*/
	private Double devApplyMinMoney;
	/**  开发者每次申请提现的最小提款值 */
	private Double devMinDrawMoney;

	/** 未确认佣金 */
	private Double unconfirmedMoney;

	/** 已确认佣金 */
	private Double confirmedMoney;

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

	/** 上传证件 */
	private File card;

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

	/** 应用数量 */
	private Integer appCount;

	/** 待确认收入 */
	private Float confirm;

	/** 已确认收入 */
	private Float confirmed;

	/** 待结算收入 */
	private Float settlement;

	/** 已结算收入 */
	private Float settlemented;
	
	/*
	 * 
	 */
	private Double finance_income;

	/** 可提现金额 */
	private Double applyingMoney = 0.00;
	
	/** 运营已确认的佣金 */
	private Double operateConfirmedMoney;

	public Double getOperateConfirmedMoney() {
		return operateConfirmedMoney;
	}

	public void setOperateConfirmedMoney(Double operateConfirmedMoney) {
		this.operateConfirmedMoney = operateConfirmedMoney;
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the appId.
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            The appId to set.
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return Returns the searchType.
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType
	 *            The searchType to set.
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return Returns the searchText.
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText
	 *            The searchText to set.
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	/**
	 * @return Returns the totalMoney.
	 */
	public Double getTotalMoney() {
		return totalMoney;
	}

	/**
	 * @param totalMoney
	 *            The totalMoney to set.
	 */
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * @return Returns the balance.
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            The balance to set.
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * @return Returns the applyMoney.
	 */
	public Double getApplyMoney() {
		return applyMoney;
	}

	/**
	 * @param applyMoney
	 *            The applyMoney to set.
	 */
	public void setApplyMoney(Double applyMoney) {
		this.applyMoney = applyMoney;
	}

	/**
	 * @return the devApplyMinMoney
	 */
	public Double getDevApplyMinMoney() {
		return devApplyMinMoney;
	}

	public Double getFinance_income() {
		return finance_income;
	}

	public void setFinance_income(Double finance_income) {
		this.finance_income = finance_income;
	}

	/**
	 * @param devApplyMinMoney
	 *            the devApplyMinMoney to set
	 */
	public void setDevApplyMinMoney(Double devApplyMinMoney) {
		this.devApplyMinMoney = devApplyMinMoney;
	}

	/**
	 * @return Returns the realName.
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            The realName to set.
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the mobilePhone.
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone
	 *            The mobilePhone to set.
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return Returns the qq.
	 */
	public Integer getQq() {
		return qq;
	}

	/**
	 * @param qq
	 *            The qq to set.
	 */
	public void setQq(Integer qq) {
		this.qq = qq;
	}

	/**
	 * @return Returns the postCode.
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            The postCode to set.
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return Returns the companyAddress.
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress
	 *            The companyAddress to set.
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return Returns the websiteUrl.
	 */
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	/**
	 * @param websiteUrl
	 *            The websiteUrl to set.
	 */
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	/**
	 * @return Returns the oldpass.
	 */
	public String getOldpass() {
		return oldpass;
	}

	/**
	 * @param oldpass
	 *            The oldpass to set.
	 */
	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the card.
	 */
	public File getCard() {
		return card;
	}

	/**
	 * @param card
	 *            The card to set.
	 */
	public void setCard(File card) {
		this.card = card;
	}

	/**
	 * @return Returns the cardType.
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            The cardType to set.
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return Returns the cardCode.
	 */
	public String getCardCode() {
		return cardCode;
	}

	/**
	 * @param cardCode
	 *            The cardCode to set.
	 */
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	/**
	 * @return Returns the bankName.
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            The bankName to set.
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return Returns the backSubbranch.
	 */
	public String getBackSubbranch() {
		return backSubbranch;
	}

	/**
	 * @param backSubbranch
	 *            The backSubbranch to set.
	 */
	public void setBackSubbranch(String backSubbranch) {
		this.backSubbranch = backSubbranch;
	}

	/**
	 * @return Returns the bankAccount.
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * @param bankAccount
	 *            The bankAccount to set.
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	/**
	 * @return Returns the appCount.
	 */
	public Integer getAppCount() {
		return appCount;
	}

	/**
	 * @param appCount
	 *            The appCount to set.
	 */
	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}

	/**
	 * @return Returns the confirm.
	 */
	public Float getConfirm() {
		return confirm;
	}

	/**
	 * @param confirm
	 *            The confirm to set.
	 */
	public void setConfirm(Float confirm) {
		this.confirm = confirm;
	}

	/**
	 * @return Returns the confirmed.
	 */
	public Float getConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed
	 *            The confirmed to set.
	 */
	public void setConfirmed(Float confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * @return Returns the settlement.
	 */
	public Float getSettlement() {
		return settlement;
	}

	/**
	 * @param settlement
	 *            The settlement to set.
	 */
	public void setSettlement(Float settlement) {
		this.settlement = settlement;
	}

	/**
	 * @return Returns the settlemented.
	 */
	public Float getSettlemented() {
		return settlemented;
	}

	/**
	 * @param settlemented
	 *            The settlemented to set.
	 */
	public void setSettlemented(Float settlemented) {
		this.settlemented = settlemented;
	}

	/**
	 * @return Returns the unconfirmedMoney.
	 */
	public Double getUnconfirmedMoney() {
		return unconfirmedMoney;
	}

	/**
	 * @param unconfirmedMoney
	 *            The unconfirmedMoney to set.
	 */
	public void setUnconfirmedMoney(Double unconfirmedMoney) {
		this.unconfirmedMoney = unconfirmedMoney;
	}

	/**
	 * @return Returns the confirmedMoney.
	 */
	public Double getConfirmedMoney() {
		return confirmedMoney;
	}

	/**
	 * @param confirmedMoney
	 *            The confirmedMoney to set.
	 */
	public void setConfirmedMoney(Double confirmedMoney) {
		this.confirmedMoney = confirmedMoney;
	}

	/**
	 * @param totalScore
	 *            The totalScore to set.
	 */
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * @return Returns the totalScore.
	 */
	public Double getTotalScore() {
		return totalScore;
	}

	/**
	 * @return Returns the applyingMoney.
	 */
	public Double getApplyingMoney() {
		return applyingMoney;
	}

	/**
	 * @param applyingMoney
	 *            The applyingMoney to set.
	 */
	public void setApplyingMoney(Double applyingMoney) {
		this.applyingMoney = applyingMoney;
	}

	public Double getDevMinDrawMoney() {
		return devMinDrawMoney;
	}

	public void setDevMinDrawMoney(Double devMinDrawMoney) {
		this.devMinDrawMoney = devMinDrawMoney;
	}

}