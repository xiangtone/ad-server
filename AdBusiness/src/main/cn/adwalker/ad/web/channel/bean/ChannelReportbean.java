package cn.adwalker.ad.web.channel.bean;

import java.io.Serializable;

public class ChannelReportbean implements Serializable {

	private static final long serialVersionUID = -3254991834635524748L;
	
	/**
	 * 发生日期开始
	 */
	private String start_date;
	
	/**
	 * 发生日期开始结束
	 */
	private String end_date;
	/**
	 * 结算方式
	 */
	private String charge_type;
	/**
	 * 活动名称
	 */
	private String campaign_name;
	/**
	 * 应用名称
	 */
	private String app_name;
	
	
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
	public String getCharge_type() {
		return charge_type;
	}
	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}


}
