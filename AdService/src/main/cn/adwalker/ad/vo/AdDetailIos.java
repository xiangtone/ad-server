package cn.adwalker.ad.vo;

import java.io.Serializable;

public class AdDetailIos implements Serializable{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -7811569331111182865L;

	private String ad_name;
	
	private String icon_url;
	
	
	private String	slogan;
	
	private String	score_desc ;
	
	
	private String click_url;
	
	
	private String score_long_desc;

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getScore_desc() {
		return score_desc;
	}

	public void setScore_desc(String score_desc) {
		this.score_desc = score_desc;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public String getScore_long_desc() {
		return score_long_desc;
	}

	public void setScore_long_desc(String score_long_desc) {
		this.score_long_desc = score_long_desc;
	}
}
