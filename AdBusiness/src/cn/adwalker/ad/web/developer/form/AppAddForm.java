package cn.adwalker.ad.web.developer.form;

public class AppAddForm {

	/**
	 * 应用市场地址
	 */
	private String marketUrl;

	/** 货币显示名称 */
	private String virtual_currency_name;

	/** 货币与人民币的比例 */
	private String exchange_rate_rmb;

	/**
	 * 应用状态
	 */
	private Integer status;

	public String getVirtual_currency_name() {
		return virtual_currency_name;
	}

	public void setVirtual_currency_name(String virtual_currency_name) {
		this.virtual_currency_name = virtual_currency_name;
	}

	public String getExchange_rate_rmb() {
		return exchange_rate_rmb;
	}

	public void setExchange_rate_rmb(String exchange_rate_rmb) {
		this.exchange_rate_rmb = exchange_rate_rmb;
	}

	public String getMarketUrl() {
		return marketUrl;
	}

	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
