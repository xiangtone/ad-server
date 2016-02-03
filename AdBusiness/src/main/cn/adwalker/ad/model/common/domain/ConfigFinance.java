package cn.adwalker.ad.model.common.domain;

import java.io.IOException;
import java.util.Date;

import cn.adwalker.ad.util.JacksonMapper;

public class ConfigFinance {

	private Long id;
	private Integer moneyScoreRate;
	private Double uuidRate;
	private Double platformRate;
	private Double advPlatformPercentage;
	private Double uuidConsumeDevPercentage;
	private Integer status;
	private Date createTime;
	
	// 新增逻辑属性
	/** 平台对第一轮广告的抽成 */
	private Integer advPlatformPercentageInteger;
	/** 终端用户消费开发者应用时，平台和开发者瓜分用户积分比率 */
	private Integer uuidConsumeDevPercentageInteger;

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

	public Integer getAdvPlatformPercentageInteger() {
		advPlatformPercentageInteger = (int) (advPlatformPercentage * 100);
		return advPlatformPercentageInteger;
	}

	public Integer getUuidConsumeDevPercentageInteger() {
		uuidConsumeDevPercentageInteger = (int) (uuidConsumeDevPercentage * 100);
		return uuidConsumeDevPercentageInteger;
	}
	
	public String toString() {
		String json = null;
		try {
			json = JacksonMapper.objectToJsonString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

}
