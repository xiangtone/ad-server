package cn.adwalker.ad.web.channel.vo;

import cn.adwalker.ad.util.ObjectUtils;

public class ChannelReportVo {

	// 主键
	private Long id;
	// 活动名称
	private String campaign_name;
	// 文件名称
	private String file_name;
	// 效果发生时间
	private String static_date;
	// 结算方式
	private String charge_type;
	// 确认数=（confirm_num*渠道指数）
	private Integer confirm_amount;
	// 广告接入单价
	private double price;
	// 收入
	private double confirm_money;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getConfirm_money() {

		if (ObjectUtils.isNotNull(confirm_amount) && ObjectUtils.isNotNull(price)) {
			confirm_money = this.price * this.confirm_amount;
		}
		return confirm_money;
	}

	public void setConfirm_money(double confirm_money) {
		this.confirm_money = confirm_money;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

}
