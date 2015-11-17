package cn.adwalker.ad.admin.finance.bean;
/**
* <p>Title: DevIncomeEffectSearchbean</p>
* <p>Description:网站主收入搜索bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-14
 */
public class DevIncomeEffectSearchbean {
	
	/**开发者id (网站主id)*/
	private Long developerId;
	/**开发者名称*/
	private String devEmail;
	/**效果发生开始时间*/
	private String statStartTime;
	/**效果发生结束时间*/
	private String statEndTime;	
	/**结算状态 0是未结算，1是已结算*/
	private Integer status;
	/**活动id*/
	private Long campaign_id;
	/**活动名称*/
	private String campaign_name;
	
	public Long getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}
	public String getDevEmail() {
		return devEmail;
	}
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}
	public String getStatStartTime() {
		return statStartTime;
	}
	public void setStatStartTime(String statStartTime) {
		this.statStartTime = statStartTime;
	}
	public String getStatEndTime() {
		return statEndTime;
	}
	public void setStatEndTime(String statEndTime) {
		this.statEndTime = statEndTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
}
