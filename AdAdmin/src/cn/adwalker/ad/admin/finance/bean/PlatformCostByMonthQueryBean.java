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
public class PlatformCostByMonthQueryBean {

	/** 开发者id (网站主id) */
	private String dev;
	/** 效果发生开始时间 */
	private String year;
	/** 效果发生结束时间 */
	private String month;

	/** 活动名称 */
	private String campaign;

	private String os;

	public Long getDeve_id() {
		long l = 0l;
		if (!StringUtil.isEmpty(dev) && NumberUtils.isNumber(dev)) {
			l = Long.valueOf(dev);
		}
		return l;
	}

	public String getDev_email() {
		String s = null;
		if (!StringUtil.isEmpty(dev)) {
			s = dev;
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
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */

	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */

	public void setMonth(String month) {
		this.month = month;
	}


	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
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

}
