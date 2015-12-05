package cn.adwalker.ad.admin.finance.bean;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

public class AchievementReportbean {
	
private String campaign;
	
	private String adv;
	//状态
	private Long status;
	//发票id
	private Long invoice_id;
	//系统类型
	private String os;
	//销售
	private String sales;
	//结算方式
	private String charge_type;

	//效果发生开始时间
	private String month_stat_date;
	//效果发生结束时间
	private String month_end_date;
	
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
	public String getMonth_stat_date() {
		return month_stat_date;
	}
	public void setMonth_stat_date(String month_stat_date) {
		this.month_stat_date = month_stat_date;
	}
	public String getMonth_end_date() {
		return month_end_date;
	}
	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getAdv() {
		return adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
	}
	public Long getAdv_id() {
		long al = 0L;
		if (!StringUtil.isEmpty(adv) && NumberUtils.isNumber(adv)) {
			al = Long.valueOf(adv);
		}
		return al;
	}
	public String getCompany_name() {
		String s = null;
		if (!StringUtil.isEmpty(adv)) {
			s = adv;
		}
		return s;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}


	
}