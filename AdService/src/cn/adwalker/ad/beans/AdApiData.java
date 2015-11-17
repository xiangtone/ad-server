package cn.adwalker.ad.beans;

import java.util.ArrayList;
import java.util.List;

public class AdApiData {
	private String code;
	private List<ApiAdInfo> data = new ArrayList<ApiAdInfo>();
	private Page page = new Page(0, 0);

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ApiAdInfo> getData() {
		return data;
	}

	public void setData(List<ApiAdInfo> data) {
		this.data = data;
	}
}
