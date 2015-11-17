package cn.adwalker.ad.dao.domain;

import java.util.Date;

import cn.adwalker.ad.bean.Data;

/**
 * 
* <p>Title: ConfigFinance</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年11月12日
 */
public class ConfigFinance extends Data {

	private static final long serialVersionUID = 5306337653708079466L;
	private Long id;
	private Integer moneyScoreRate;
	private Double uuidRate;
	private Double platformRate;
	private Double devRate;
	private Double advPlatformPercentage;
	private Double uuidConsumeDevPercentage;
	private Integer status;
	private Date createTime;
	private Integer quickly_task;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMoneyScoreRate() {
		return moneyScoreRate;
	}

	public void setMoneyScoreRate(Integer moneyScoreRate) {
		this.moneyScoreRate = moneyScoreRate;
	}

	public Double getUuidRate() {
		return uuidRate;
	}

	public void setUuidRate(Double uuidRate) {
		this.uuidRate = uuidRate;
	}

	public Double getPlatformRate() {
		return platformRate;
	}

	public void setPlatformRate(Double platformRate) {
		this.platformRate = platformRate;
	}

	public Double getDevRate() {
		return devRate;
	}

	public void setDevRate(Double devRate) {
		this.devRate = devRate;
	}

	public Double getAdvPlatformPercentage() {
		return advPlatformPercentage;
	}

	public void setAdvPlatformPercentage(Double advPlatformPercentage) {
		this.advPlatformPercentage = advPlatformPercentage;
	}

	public Double getUuidConsumeDevPercentage() {
		return uuidConsumeDevPercentage;
	}

	public void setUuidConsumeDevPercentage(Double uuidConsumeDevPercentage) {
		this.uuidConsumeDevPercentage = uuidConsumeDevPercentage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getQuickly_task() {
		return quickly_task;
	}

	public void setQuickly_task(Integer quickly_task) {
		this.quickly_task = quickly_task;
	}
}
