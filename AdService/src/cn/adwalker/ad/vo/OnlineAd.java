package cn.adwalker.ad.vo;

public class OnlineAd {
	private String	ad_id;
	
	private String	ad_mark;
	
	private String ad_name;
	
	private int limit_time;

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public int getLimit_time() {
		return limit_time;
	}

	public void setLimit_time(int limit_time) {
		this.limit_time = limit_time;
	}

	public String getAd_mark() {
		return ad_mark;
	}

	public void setAd_mark(String ad_mark) {
		this.ad_mark = ad_mark;
	}

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
}
