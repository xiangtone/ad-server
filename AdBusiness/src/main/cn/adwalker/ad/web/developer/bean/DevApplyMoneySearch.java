/**
 * 
 */
package cn.adwalker.ad.web.developer.bean;


/**
 * @author guoyongxiang
 * 
 */
public class DevApplyMoneySearch {

	/** 主键 */
	private Long id;

	/** 开发者ID */
	private Long dev_id;

	/** 开始时间 */
	private String startTime;

	/** 结束时间 */
	private String endTime;
	
	
	private Integer status;

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            The startTime to set.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            The endTime to set.
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
