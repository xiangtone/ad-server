package cn.adwalker.ad.web.developer.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.adwalker.ad.util.StringUtil;

public class DevIncomeReport implements Serializable {

	private static final long serialVersionUID = 8465237714859413876L;

	/**
	 * 应用
	 */
	private String app_id;

	/**
	 * 日期
	 */
	private String date;

	public String getKey() throws Exception {
		if (StringUtil.isEmpty(app_id) || StringUtil.isEmpty(date)) {
			throw new Exception();
		}
		return app_id + "_" + date;
	}

	private List<DevIncomeReportTemp> list;

	private Map<String, Integer> map;

	public int getSize() {
		int i = 0;
		if (list != null) {
			i = list.size();
		}
		return i;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public List<DevIncomeReportTemp> getList() {
		return list;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public void setList(List<DevIncomeReportTemp> list) {
		this.list = list;
	}
}