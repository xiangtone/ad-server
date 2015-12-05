package cn.adwalker.ad.admin.finance.vo;

/**
 * 功能概述：<br>
 *
 *	DevConsumeEffectConfirm 处理 开发者信息 
 *
 * @author jiaozhichao
 */
public class DevConsumeConfirmToDeveloperVo {
	/**  需要加上的已确认收入 */
	private Double manageMoney;
	/**  开发者id */
	private Long devId;
	
	private Long appId;
	
	private String statDate;
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
	 * @return Returns the devId.
	 */
	public Long getDevId() {
		return devId;
	}
	/**
	 * @param devId The devId to set.
	 */
	public void setDevId(Long devId) {
		this.devId = devId;
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
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	
}
