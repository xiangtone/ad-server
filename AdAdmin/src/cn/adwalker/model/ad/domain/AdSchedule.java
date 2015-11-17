package cn.adwalker.model.ad.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

 /**
  * 
 * <p>Title: AdSchedule</p>
 * <p>Description:定时任务实体</p>
 * <p>Company: adwalker</p> 
 * @author    cuidd
 * @date       2013-7-3
  */
public class AdSchedule implements Entity  {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1198583911373049433L;
	// 主键
	private Long id;
	// 活动id
	private Long ad_id;

	// 任务类型1上线,-1是下线
	private Integer type;

	// 创建时间
	private Date task_time;
	// 包id

	// 排期开始时间
	private Date run_time;

	private Integer status;
	
	/**
	 * 定时任务类型
	 */
	private Integer task_type;
	
	/**
	 * 更新时间
	 */
	private Date update_time;

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

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getTask_type() {
		return task_type;
	}

	public void setTask_type(Integer task_type) {
		this.task_type = task_type;
	}
}
