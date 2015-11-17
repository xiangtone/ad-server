package cn.adwalker.ad.web.developer.bean;

import java.io.Serializable;

public class DevReportbean implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -3254991834635524748L;
	/**
	 * 统计日期
	 */
	private String start_date;
	
	/**
	 * 统计日期
	 */
	private String end_date;
	
	/**
	 * 系统
	 */
	private String os;
	/**
	 * 开发者id
	 */
	private Long dev_id;
	/**
	 * 应用id
	 */
	private Long app_id;
	/**
	 * （形式）积分墙推荐墙插屏
	 */
	private Long type_id;
	
	private Long time_q;
	
	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public Long getTime_q() {
		return time_q;
	}

	public void setTime_q(Long time_q) {
		this.time_q = time_q;
	}
	
}
