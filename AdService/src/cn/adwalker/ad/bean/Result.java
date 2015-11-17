package cn.adwalker.ad.bean;

import java.io.IOException;

import cn.adwalker.core.utils.JacksonMapper;


public class Result extends Data {

	private static final long serialVersionUID = 220661836201366779L;
	private String status;// 状态 ok/error
	private Data data;// 数据

	public Result() {

	}

	public Result(String status, Data data) {
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
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
