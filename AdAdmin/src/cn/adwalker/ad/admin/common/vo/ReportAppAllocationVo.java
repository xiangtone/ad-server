package cn.adwalker.ad.admin.common.vo;


public class ReportAppAllocationVo {
	
	/**统计开始时间*/
	private String startTime;
	/**统计结束时间*/
	private String endTime;	
	
	/** 开发者搜索类型 */
	private String devType;
	
	/** 应用搜索类型 */
	private String appType;
	
	/** 开发者搜索内容 */
	private String devText;
	
	/** 应用搜索内容 */
	private String appText;
	
	/** 统计类型 1、综合 2、日数据 */
	
	private Integer statType;
	
	/** 0广告墙、1积分墙、2换量墙 */
	
	private Integer statAdType;
	
	/** 行为编号( 1:疑似 2:作弊 3:忽略 4.正常)*/
	private Integer rateStatus;
	
	/** 排序字段 */
	private String orderColumn;
	
	/** 排序条件 */
	private String orderCondition;
	
	
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getDevText() {
		return devText;
	}
	public void setDevText(String devText) {
		this.devText = devText;
	}
	public String getAppText() {
		return appText;
	}
	public void setAppText(String appText) {
		this.appText = appText;
	}
	public Integer getStatAdType() {
		return statAdType;
	}
	public void setStatAdType(Integer statAdType) {
		this.statAdType = statAdType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStatType() {
		return statType;
	}
	public void setStatType(Integer statType) {
		this.statType = statType;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderCondition() {
		return orderCondition;
	}
	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}
	public Integer getRateStatus() {
		return rateStatus;
	}
	public void setRateStatus(Integer rateStatus) {
		this.rateStatus = rateStatus;
	}
	
}
