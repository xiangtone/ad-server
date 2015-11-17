package cn.adwalker.ad.control.domain;

import java.io.Serializable;
import java.util.Date;

public class AdSchedule implements Serializable {

	private static final long serialVersionUID = -8963131701756488572L;
	
	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 类型,1 上线,-1下线
	 */
	private Integer type;
	
	/**
	 * 广告id
	 */
	private Long adId;
	
	/**
	 * 状态0未执行,1正常执行，-3定时任务执行但未生效
	 */
	private Integer status;
	
	/**
	 * 定时上下时间
	 */
	private Date taskTime;
	
	/**
	 * 定时执行时间
	 */
	private Date runTime;
	
	/**
	 * 人工修改广告定时时间
	 */
	private Date updateTime;
	
	/**
	 * 系统上下线,1，人工设置定时上线,2人工设置定时下线，3系统设上线
	 */
	private Integer taskType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}

	public Date getRunTime() {
		return runTime;
	}

	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

}
