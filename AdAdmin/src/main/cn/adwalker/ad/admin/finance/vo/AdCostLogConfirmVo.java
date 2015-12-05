package cn.adwalker.ad.admin.finance.vo;

public class AdCostLogConfirmVo {
	private Long app_id; // 应用ID
	private String app_name; // 应用名称
	private Long ad_id; // 广告ID
	private String ad_name; // 广告名称
	private String imei; // IMEI
	private String pay; // 支付方式
	private Double confirm_income; // 确认收入
	private Integer type; // 0-正常激活数据 1-系统奖励
	private String stat_date; // 效果发生时间
	private double count; // 确认激活数
	private String channel; // 渠道
	private Double price; // 单价
	private Double into_rate; // 分成比例
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getInto_rate() {
		return into_rate;
	}

	public void setInto_rate(Double into_rate) {
		this.into_rate = into_rate;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public Double getConfirm_income() {
		return confirm_income;
	}

	public void setConfirm_income(Double confirm_income) {
		this.confirm_income = confirm_income;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStat_date() {
		return stat_date;
	}

	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
}
