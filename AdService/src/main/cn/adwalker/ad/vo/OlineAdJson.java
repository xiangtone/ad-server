package cn.adwalker.ad.vo;

import java.util.List;

import cn.adwalker.ad.bean.Data;

public class OlineAdJson extends Data {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -4263625757513175104L;
	private List<OnlineAd> recodeList;
	public List<OnlineAd> getRecodeList() {
		return recodeList;
	}
	public void setRecodeList(List<OnlineAd> recodeList) {
		this.recodeList = recodeList;
	}
}
