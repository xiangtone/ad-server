package cn.adwalker.ad.admin.operation.bean;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

/**
* <p>Title: FinanceRevenueCostbean</p>
* <p>Description:确认收入bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-7
 */
public class AchievementReportbean {
	
	private String campaign;
	
	private String confirm_id;
	
	private String adv;
	//状态
	private Long status;
	//发票id
	private Long invoice_id;
	
	private String month;

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

	public String getConfirm_id() {
		return confirm_id;
	}

	public void setConfirm_id(String confirm_id) {
		this.confirm_id = confirm_id;
	}

	/**
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	
	public void setMonth(String month) {
		this.month = month;
	}
}
