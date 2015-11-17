package cn.adwalker.ad.web.channel.vo;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.util.ObjectUtils;

public class ChannelSdkReportVo {

	// 主键
	private Long id;
	// 应用名称
	private String app_name;
	// 效果发生时间
	private String static_date;
	// 确认数=（confirm_num*渠道指数）
	private Integer confirm_amount;
	// 收入
	private double confirm_money;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getConfirm_amount() {
		if (ObjectUtils.isEmpty(confirm_amount)) {
			confirm_amount = 0;
		} else {
		}
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public double getConfirm_money() {
		if (ObjectUtils.isNotEmpty(confirm_money)) {
			confirm_money = confirm_money * AppConstant.CHANNEL_SDK_SCALE;
		}
		return confirm_money;
	}

	public void setConfirm_money(double confirm_money) {
		this.confirm_money = confirm_money;
	}

}
