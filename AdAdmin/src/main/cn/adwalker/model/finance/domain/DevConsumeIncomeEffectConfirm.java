/**
 * 
 */
package cn.adwalker.model.finance.domain;

import java.util.Date;

/**
 * @author wjp 开发者广告效果确认
 */
public class DevConsumeIncomeEffectConfirm {
	
	/** 主键  */
	private Long id;
	/** 统计日期  */
	private String statDate;
	/** 开发者ID  */
	private Long developerId;
	/** 开发者邮箱  */
	private String devEmail;
	/** 联系人  */
	private String realName;
	/** 应用ID  */
	private Long appId;
	/** 应用名称  */
	private String appName;
	/**  消耗积分的用户数 */
	private Double scoreVisitorAmount;
	/** 获得的积分  */
	private Double score;
	/** 预计收入  */
	private Double forecastIncome;
	/**  审核状态(0:待审核 1:通过 -1:失败) */
	private Integer status;
	/**  管理员ID */
	private Long managerId;
	/** 管理员确认收入  */
	private Double manageMoney;
	/** 描述  */
	private String description;
	/** 审核时间  */
	private Date checkTime;
	/**  sysdate */
	private Date createTime;
	/**开发者状态*/
	private Integer devStatus;
	/**财务收入*/
	private Double financeMoney;
	/**结算时间*/
	private Date financeTime;
	/**操作人id*/
	private Long financeId;
	public Long getFinanceId() {
		return financeId;
	}
	public void setFinanceId(Long financeId) {
		this.financeId = financeId;
	}
	/**操作人*/
	private String financeName;
	public Double getFinanceMoney() {
		return financeMoney;
	}
	public void setFinanceMoney(Double financeMoney) {
		this.financeMoney = financeMoney;
	}
	public Date getFinanceTime() {
		return financeTime;
	}
	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Returns the statDate.
	 */
	public String getStatDate() {
		return statDate;
	}
	/**
	 * @param statDate The statDate to set.
	 */
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	/**
	 * @return Returns the developerId.
	 */
	public Long getDeveloperId() {
		return developerId;
	}
	/**
	 * @param developerId The developerId to set.
	 */
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}
	/**
	 * @return Returns the devEmail.
	 */
	public String getDevEmail() {
		return devEmail;
	}
	/**
	 * @param devEmail The devEmail to set.
	 */
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	/**
	 * @return Returns the realName.
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName The realName to set.
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * @return Returns the appId.
	 */
	public Long getAppId() {
		return appId;
	}
	/**
	 * @param appId The appId to set.
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	/**
	 * @return Returns the appName.
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * @param appName The appName to set.
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * @return Returns the scoreVisitorAmount.
	 */
	public Double getScoreVisitorAmount() {
		return scoreVisitorAmount;
	}
	/**
	 * @param scoreVisitorAmount The scoreVisitorAmount to set.
	 */
	public void setScoreVisitorAmount(Double scoreVisitorAmount) {
		this.scoreVisitorAmount = scoreVisitorAmount;
	}
	/**
	 * @return Returns the score.
	 */
	public Double getScore() {
		return score;
	}
	/**
	 * @param score The score to set.
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	/**
	 * @return Returns the forecastIncome.
	 */
	public Double getForecastIncome() {
		return forecastIncome;
	}
	/**
	 * @param forecastIncome The forecastIncome to set.
	 */
	public void setForecastIncome(Double forecastIncome) {
		this.forecastIncome = forecastIncome;
	}
	/**
	 * @return Returns the status.
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return Returns the managerId.
	 */
	public Long getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId The managerId to set.
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	/**
	 * @return Returns the manageMoney.
	 */
	public Double getManageMoney() {
		return manageMoney;
	}
	/**
	 * @param manageMoney The manageMoney to set.
	 */
	public void setManageMoney(Double manageMoney) {
		this.manageMoney = manageMoney;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the checkTime.
	 */
	public Date getCheckTime() {
		return checkTime;
	}
	/**
	 * @param checkTime The checkTime to set.
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFinanceName() {
		return financeName;
	}
	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}
	public Integer getDevStatus() {
		return devStatus;
	}
	public void setDevStatus(Integer devStatus) {
		this.devStatus = devStatus;
	}
	
	
}
