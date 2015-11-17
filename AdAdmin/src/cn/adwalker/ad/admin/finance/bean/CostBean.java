package cn.adwalker.ad.admin.finance.bean;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

public class CostBean {

	private String campaign;
	private String channel;
	private Long invoice_status;// 发票状态
	private Long payment_status;// 付款状态
	private String os;// 系统类型
	private String sales;// 销售
	private String month_stat_date;// 效果发生开始时间
	private String month_end_date;// 效果发生结束时间
	private String balance_model;//结算方式
	
	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

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

	public Long getChannel_id() {
		long al = 0L;
		if (!StringUtil.isEmpty(channel) && NumberUtils.isNumber(channel)) {
			al = Long.valueOf(channel);
		}
		return al;
	}

	public String getChannel_name() {
		String s = null;
		if (!StringUtil.isEmpty(channel)) {
			s = channel;
		}
		return s;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getInvoice_status() {
		return invoice_status;
	}

	public void setInvoice_status(Long invoice_status) {
		this.invoice_status = invoice_status;
	}

	public Long getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(Long payment_status) {
		this.payment_status = payment_status;
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

	public String getBalance_model() {
		return balance_model;
	}

	public void setBalance_model(String balance_model) {
		this.balance_model = balance_model;
	}

}
