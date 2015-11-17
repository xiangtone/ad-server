package cn.adwalker.ad.admin.sales.bean;

import java.io.Serializable;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.core.util.lang.StringUtil;

public class DetailAndroidQueryBean implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 7514115349030750985L;

	private String start_date;
	private String end_date;
	private Long campaign_id;

	private String packageInfo;

	public Long getPackage_id() {
		long l = 0l;
		if (!StringUtil.isEmpty(packageInfo)
				&& NumberUtils.isNumber(packageInfo)) {
			l = Long.valueOf(packageInfo);
		}
		return l;
	}

	public String getPackage_name() {
		String s = null;
		if (!StringUtil.isEmpty(packageInfo)) {
			s = packageInfo;
		}
		return s;
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

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(String packageInfo) {
		this.packageInfo = packageInfo;
	}
}
