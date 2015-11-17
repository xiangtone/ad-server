package cn.adwalker.ad.admin.finance.bean;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

/**
 * <p>
 * Title: DevIncomeAuditbean
 * </p>
 * <p>
 * Description:开发者收入搜索bean
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-15
 */
public class ChannelCostQueryBean {

	/** 效果发生开始时间 */
	private String statStartTime;
	/** 效果发生结束时间 */
	private String statEndTime;
	/** 结算状态 0是未结算，1是已结算 */
	private Integer status;
	/** 开发者佣金 1不足1元，10不足10元 */
	private Integer dev_confirmMoney;

	/** 活动名称 */
	private String campaign;

	private String channel;
	/** 渠道人员 */
	private String sales;
	/** 投放方式*/
	private String charge_type;

	private String os;

	/**
	 * 
	 */
	private Integer balance_status;

	/**
	 * @return channel_id
	 */
	public Long getChannel_id() {
		long l = 0l;
		if (!StringUtil.isEmpty(channel) && NumberUtils.isNumber(channel)) {
			l = Long.valueOf(channel);
		}
		return l;
	}

	/**
	 * @return channel_name
	 */
	public String getChannel_name() {
		String s = null;
		if (!StringUtil.isEmpty(channel)) {
			s = channel;
		}
		return s;
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

	/**
	 * @return channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStatStartTime() {
		return statStartTime;
	}

	public void setStatStartTime(String statStartTime) {
		this.statStartTime = statStartTime;
	}

	public String getStatEndTime() {
		return statEndTime;
	}

	public void setStatEndTime(String statEndTime) {
		this.statEndTime = statEndTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public Integer getDev_confirmMoney() {
		return dev_confirmMoney;
	}

	public void setDev_confirmMoney(Integer dev_confirmMoney) {
		this.dev_confirmMoney = dev_confirmMoney;
	}

	/**
	 * @return balance_status
	 */
	public Integer getBalance_status() {
		return balance_status;
	}

	/**
	 * @param balance_status
	 *            the balance_status to set
	 */

	public void setBalance_status(Integer balance_status) {
		this.balance_status = balance_status;
	}

	/**
	 * @return os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */

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
