package cn.adwalker.ad.admin.report.bean;
/**
* <p>Title: CensusGeneralViewBean</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-29
 */
public class CensusGeneralViewBean {
	//时间
	private int wall_response_category;
	
	private String start_stat_date;
	private String end_stat_date;
	private String week_stat_date;
	private String month_stat_date;
	private Integer income_cost_type;
	private Integer platform_channel_type;
	private String android_ios_type;
	private String os;
	
	
	private String start_date;
	private String end_date;
	
	public String getStart_stat_date() {
		return start_stat_date;
	}
	public void setStart_stat_date(String start_stat_date) {
		this.start_stat_date = start_stat_date;
	}
	public String getEnd_stat_date() {
		return end_stat_date;
	}
	public void setEnd_stat_date(String end_stat_date) {
		this.end_stat_date = end_stat_date;
	}
	public String getWeek_stat_date() {
		return week_stat_date;
	}
	public void setWeek_stat_date(String week_stat_date) {
		this.week_stat_date = week_stat_date;
	}
	public String getMonth_stat_date() {
		return month_stat_date;
	}
	public void setMonth_stat_date(String month_stat_date) {
		this.month_stat_date = month_stat_date;
	}
	public Integer getIncome_cost_type() {
		return income_cost_type;
	}
	public void setIncome_cost_type(Integer income_cost_type) {
		this.income_cost_type = income_cost_type;
	}
	public Integer getPlatform_channel_type() {
		return platform_channel_type;
	}
	public void setPlatform_channel_type(Integer platform_channel_type) {
		this.platform_channel_type = platform_channel_type;
	}
	
	public String getAndroid_ios_type() {
		return android_ios_type;
	}
	public void setAndroid_ios_type(String android_ios_type) {
		this.android_ios_type = android_ios_type;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getWall_response_category() {
		return wall_response_category;
	}
	public void setWall_response_category(int wall_response_category) {
		this.wall_response_category = wall_response_category;
	}
	
	
}
