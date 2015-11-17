package cn.adwalker.ad.control.vo;

import java.util.Date;

/**
 * 平台投放vo
 * <p>
 * Title: AdOld
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-9
 */
public class AdScheduleVo {
	// 主键
	private Long id;
	// 活动id
	private Long ad_id;

	// -1下线，1上线
	private Integer type;

	// 创建时间
	private Date task_time;
	// 包id

	// 排期开始时间
	private Date run_time;
	
	private Integer status;
	
	
	private Integer ad_status;
	
	/**
	 * 定时上线类型:1上线，2下线，3超量下线后上线
	 */
	private Integer task_type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getTask_time() {
		return task_time;
	}

	public void setTask_time(Date task_time) {
		this.task_time = task_time;
	}

	public Date getRun_time() {
		return run_time;
	}

	public void setRun_time(Date run_time) {
		this.run_time = run_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAd_status() {
		return ad_status;
	}

	public void setAd_status(Integer ad_status) {
		this.ad_status = ad_status;
	}

	public Integer getTask_type() {
		return task_type;
	}

	public void setTask_type(Integer task_type) {
		this.task_type = task_type;
	}
}
