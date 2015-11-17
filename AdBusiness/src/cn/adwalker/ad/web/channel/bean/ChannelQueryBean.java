package cn.adwalker.ad.web.channel.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;

import cn.adwalker.ad.util.StringUtil;

public class ChannelQueryBean implements Serializable {

	private static final long serialVersionUID = -3254991834635524748L;
	/**
	 * 统计日期
	 */
	private Date date;

	private String appIdOrName;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAppIdOrName() {
		return appIdOrName;
	}

	public void setAppIdOrName(String appIdOrName) {
		this.appIdOrName = appIdOrName;
	}

	public Long getApp_id() {
		long l = 0;
		if (!StringUtil.isEmpty(appIdOrName)
				&& NumberUtils.isNumber(appIdOrName)) {
			l = Long.valueOf(appIdOrName.trim());
		}
		return l;
	}

	public String getName() {
		String name = null;
		if (!StringUtil.isEmpty(appIdOrName)
				&& !NumberUtils.isNumber(appIdOrName)) {
			name = appIdOrName;
		}
		return name;
	}
}
