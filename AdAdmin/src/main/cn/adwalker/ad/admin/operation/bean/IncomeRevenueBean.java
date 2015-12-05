package cn.adwalker.ad.admin.operation.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * <p>
 * Title: IncomeRevenueBean
 * </p>
 * <p>
 * Description:确认收入bean
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-8
 */
public class IncomeRevenueBean {
	// 活动id
	private String campaign;
	// 设备标识符
	private String income_mac;
	// 效果结束时间
	private String statStartTime;
	// 效果开始时间
	private String statEndTime;
	// 状态
	private Integer status;
	
	public Long getCampaign_id() {
		Long l = null;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign.trim());
		}
		return l;
	}

	public String getCampaign_name() {
		String s = null;
		if (!StringUtils.isEmpty(campaign)) {
			s = campaign.trim();
		}
		return s;
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

	public String getIncome_mac() {
		return income_mac;
	}

	public void setIncome_mac(String income_mac) {
		this.income_mac = income_mac;
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
	

}