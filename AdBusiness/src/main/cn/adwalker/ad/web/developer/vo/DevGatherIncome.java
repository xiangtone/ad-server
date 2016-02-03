package cn.adwalker.ad.web.developer.vo;

import java.io.Serializable;
import java.util.List;

public class DevGatherIncome implements Serializable {

	private static final long serialVersionUID = 8465237714859413876L;

	private String app_id;

	public int getSize() {
		int i = 0;
		if (list != null) {
			i = list.size();
		}
		return i;
	}

	private List<DevGatherIncomeTemp> list;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public List<DevGatherIncomeTemp> getList() {
		return list;
	}

	public void setList(List<DevGatherIncomeTemp> list) {
		this.list = list;
	}
}