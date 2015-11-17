package cn.adwalker.model.sys.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: DateMonitor
 * </p>
 * <p>
 * Description:T_LOG_MONITOR表
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-8-22
 */
public class DateMonitor implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private Long id;
	/** 创建时间 */
	private Date create_time;
	/** 状态（状态(0：正常，1、失败)） */
	private Integer status;
	/**
	 * 定时任务类型（0：按天统计----广告，1：按天统计----应用，2：实时统计----应用，3：实时统计----广告，4：开发者收入审核，5：
	 * 渠道数据统计）
	 */
	private Integer task_type;
	/** 发送状态（0：未发送，1：发送） */
	private Integer send_status;

	private String hour;
	private String static_date;
	private Integer date_count;
	private Integer last_date_count;

	private String column_name;

	private String table_name;
	private Double rate;
	private Integer monitor_type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTask_type() {
		return task_type;
	}

	public void setTask_type(Integer task_type) {
		this.task_type = task_type;
	}

	public Integer getSend_status() {
		return send_status;
	}

	public void setSend_status(Integer send_status) {
		this.send_status = send_status;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getDate_count() {
		return date_count;
	}

	public void setDate_count(Integer date_count) {
		this.date_count = date_count;
	}

	public Integer getLast_date_count() {
		return last_date_count;
	}

	public void setLast_date_count(Integer last_date_count) {
		this.last_date_count = last_date_count;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getMonitor_type() {
		return monitor_type;
	}

	public void setMonitor_type(Integer monitor_type) {
		this.monitor_type = monitor_type;
	}
}
