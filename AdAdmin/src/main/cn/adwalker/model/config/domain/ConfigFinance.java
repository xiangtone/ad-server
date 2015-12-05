package cn.adwalker.model.config.domain;

import java.io.IOException;

import cn.adwalker.core.util.JacksonMapper;

public class ConfigFinance {

	private Long id;
	private Integer moneyScoreRate;

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
