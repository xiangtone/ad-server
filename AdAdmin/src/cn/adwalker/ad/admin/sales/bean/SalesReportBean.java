package cn.adwalker.ad.admin.sales.bean;

import java.io.Serializable;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

public class SalesReportBean implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5650713941456732101L;

	private String start_date;

	private String end_date;

	private String adv_name;

	private String os;
	
	
	private Long sales_id;
	
	private String campaign;

	public Long getCampaign_id() {
		long l = 0l;
		if (!StringUtil.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign);
		}
		return l;
	}

	public String getCampaign_name() {
		String s = null;
		if (!StringUtil.isEmpty(campaign)) {
			s = campaign;
		}
		return s;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
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

	public String getAdv_name() {
		return adv_name;
	}

	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getSales_id() {
		return sales_id;
	}

	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}
}
