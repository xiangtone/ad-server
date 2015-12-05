/**
 * 
 */
package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

/**
 * @author wjp
 * 广告效果一次确认
 */
public class AdEffectFirstConfirmVo {

	/** 主键id */
	private Long id;
	
	/** 广告主id */
	private Long advertiserId;
	
	/** 广告主邮箱 */
	private String advEmail;
	
	/** 广告id */
	private Long adId;
	
	/** 广告名称 */
	private String adName;
	
	/** 联系人 */
	private String realName;
	
	/** 广告出价 */
	private double price;
	
	/** 一次确认有效数量 */
	private int advValidAmount;
	
	/** 一次确认金额 */
	private double advConfirmMoney;
	
	/** 平台未确认数量 */
	private int platformAmount;
	
	/** 平台未确认收益 */
	private double platformMoney;
	
	/**  差额 */
	private double balance;
	
	/** 二次确认金额 */
	private double actualMoney;
	
	/** 二次确认数量 */
	private int actualAmount;
	
	/** 管理员id */
	private Long managerId;
	
	/** 审核状态 */
	private int status;
	
	/** 描述 */
	private String description;
	
	/** 审核时间 */
	private Date checkTime;
	
	/** 统计时间 */
	private String statDate;
	
	/** 录入时间 */
	private Date enterTime;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 更新时间 */
	private Date updateTime;

	/** 广告主名称 */
	private String userAdverName;
	
	/** 状态 */
	private String statusName;
	
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
	 * @return the advertiserId
	 */
	public Long getAdvertiserId() {
		return advertiserId;
	}

	/**
	 * @param advertiserId the advertiserId to set
	 */
	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}

	/**
	 * @return the advEmail
	 */
	public String getAdvEmail() {
		return advEmail;
	}

	/**
	 * @param advEmail the advEmail to set
	 */
	public void setAdvEmail(String advEmail) {
		this.advEmail = advEmail;
	}

	/**
	 * @return the adId
	 */
	public Long getAdId() {
		return adId;
	}

	/**
	 * @param adId the adId to set
	 */
	public void setAdId(Long adId) {
		this.adId = adId;
	}

	/**
	 * @return the adName
	 */
	public String getAdName() {
		return adName;
	}

	/**
	 * @param adName the adName to set
	 */
	public void setAdName(String adName) {
		this.adName = adName;
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the advValidAmount
	 */
	public int getAdvValidAmount() {
		return advValidAmount;
	}

	/**
	 * @param advValidAmount the advValidAmount to set
	 */
	public void setAdvValidAmount(int advValidAmount) {
		this.advValidAmount = advValidAmount;
	}

	/**
	 * @return the advConfirmMoney
	 */
	public double getAdvConfirmMoney() {
		return advConfirmMoney;
	}

	/**
	 * @param advConfirmMoney the advConfirmMoney to set
	 */
	public void setAdvConfirmMoney(double advConfirmMoney) {
		this.advConfirmMoney = advConfirmMoney;
	}

	/**
	 * @return the platformAmount
	 */
	public int getPlatformAmount() {
		return platformAmount;
	}

	/**
	 * @param platformAmount the platformAmount to set
	 */
	public void setPlatformAmount(int platformAmount) {
		this.platformAmount = platformAmount;
	}

	/**
	 * @return the platformMoney
	 */
	public double getPlatformMoney() {
		return platformMoney;
	}

	/**
	 * @param platformMoney the platformMoney to set
	 */
	public void setPlatformMoney(double platformMoney) {
		this.platformMoney = platformMoney;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the actualMoney
	 */
	public double getActualMoney() {
		return actualMoney;
	}

	/**
	 * @param actualMoney the actualMoney to set
	 */
	public void setActualMoney(double actualMoney) {
		this.actualMoney = actualMoney;
	}

	/**
	 * @return the actualAmount
	 */
	public int getActualAmount() {
		return actualAmount;
	}

	/**
	 * @param actualAmount the actualAmount to set
	 */
	public void setActualAmount(int actualAmount) {
		this.actualAmount = actualAmount;
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the checkTime
	 */
	public Date getCheckTime() {
		return checkTime;
	}

	/**
	 * @param checkTime the checkTime to set
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	

	/**
	 * @return the enterTime
	 */
	public Date getEnterTime() {
		return enterTime;
	}

	/**
	 * @param enterTime the enterTime to set
	 */
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
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

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public String getUserAdverName() {
		return userAdverName;
	}

	public void setUserAdverName(String userAdverName) {
		this.userAdverName = userAdverName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
