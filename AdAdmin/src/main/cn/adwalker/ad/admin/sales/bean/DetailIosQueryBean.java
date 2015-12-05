package cn.adwalker.ad.admin.sales.bean;

import java.io.Serializable;

public class DetailIosQueryBean implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 7514115349030750985L;

	private String start_date;
	private String end_date;
	private Long campaign_id;

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
}
