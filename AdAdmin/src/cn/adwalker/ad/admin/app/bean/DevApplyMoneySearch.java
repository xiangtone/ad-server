/**
 * 
 */
package cn.adwalker.ad.admin.app.bean;


/**
 * @author guoyongxiang
 * 
 */
public class DevApplyMoneySearch {

	/** 主键 */
	private Long id;

	/** 开发者ID */
	private Long developerId;

	/** 开始时间 */
	private String startTime;

	/** 结束时间 */
	private String endTime;

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the developerId.
	 */
	public Long getDeveloperId() {
		return developerId;
	}

	/**
	 * @param developerId
	 *            The developerId to set.
	 */
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
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
