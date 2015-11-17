package cn.adwalker.ad.admin.operation.bean;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

/**
* <p>Title: CahannelConfirmbean</p>
* <p>Description:渠道成本结算bean</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年11月17日
 */
public class CahannelConfirmbean {
	
	private String channel;
	
	private String campaign;
	//广告主id
	private Long adv_id;
	//状态
	private Long status;
	//发票id
	private Long invoice_id;

	//效果发生开始时间
	private String month_stat_date;
	//效果发生结束时间
	private String month_end_date;
	//平台类型	
	private String os;
	
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
		long l = 0l;
		if (!StringUtil.isEmpty(channel) && NumberUtils.isNumber(channel)) {
			l = Long.valueOf(channel);
		}
		return l;
	}

	public String getChannel_name() {
		String s = null;
		if (!StringUtil.isEmpty(channel)) {
			s = channel;
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
	public Long getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}


	
	
	
}
