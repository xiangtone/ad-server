package cn.adwalker.model.config.domain;

import java.io.IOException;

import cn.adwalker.core.util.JacksonMapper;

public class ConfigQuickLyTaskFinance {

	private Long id;
	private Integer quickly_task;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	public Integer getQuickly_task() {
		return quickly_task;
	}

	public void setQuickly_task(Integer quickly_task) {
		this.quickly_task = quickly_task;
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
