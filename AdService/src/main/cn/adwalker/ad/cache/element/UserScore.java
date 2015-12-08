/**
* <p>Title: UserScore.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-23
* @version 1.0
*/
package cn.adwalker.ad.cache.element;

import cn.adwalker.ad.bean.Data;


/**
 * <p>Title: UserScore</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-23
 */
public class UserScore extends Data {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -5803283164189788874L;
	
	private Long id;
	private String uuid;
	private Long app_id;
	private Integer score;
	private Integer updateScore;
	private long delayTome;
	private Integer todayInteger;
	private Integer downLoadTime;
	public UserScore(){}
	
	public UserScore(String uuid, Long appId, Integer score,
			Integer todayInteger, Integer downLoadTime) {
		super();
		this.uuid = uuid;
		app_id = appId;
		this.score = score;
		this.todayInteger = todayInteger;
		this.downLoadTime = downLoadTime;
	}
	public Integer getTodayInteger() {
		return todayInteger;
	}
	public void setTodayInteger(Integer todayInteger) {
		this.todayInteger = todayInteger;
	}
	public Integer getDownLoadTime() {
		return downLoadTime;
	}
	public void setDownLoadTime(Integer downLoadTime) {
		this.downLoadTime = downLoadTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getUpdateScore() {
		return updateScore;
	}
	public void setUpdateScore(Integer updateScore) {
		this.updateScore = updateScore;
	}
	public long getDelayTome() {
		return delayTome;
	}
	public void setDelayTome(long delayTome) {
		this.delayTome = delayTome;
	}
	
	

}
