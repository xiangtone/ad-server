package cn.adwalker.ad.web.developer.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DevIncomeReportByHour implements Serializable {

	private static final long serialVersionUID = 8465237714859413876L;

	private String app_id;

	private List<DevIncomeReportByHourTemp> list;

	private Map<String, Integer> map;

	public int getSize() {
		int i = 0;
		if (list != null) {
			i = list.size();
		}
		return i;
	}

	private String staticHour;

	public String getStaticHour() {
		return staticHour;
	}

	public void setStaticHour(String staticHour) {
		this.staticHour = staticHour;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public List<DevIncomeReportByHourTemp> getList() {
		return list;
	}

	public void setList(List<DevIncomeReportByHourTemp> list) {
		this.list = list;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

}