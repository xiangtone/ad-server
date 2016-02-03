package cn.adwalker.ad.model.application.domain;

/**
 * 虚拟货币
 * 
 * @author Administrator  
 * 
 */
public class AppCurrency {

	/** 开发者应用id */
	private Long app_id;

	/** 货币显示名称 */
	private String virtual_currency_name;

	/** 货币与人民币的比例 */
	private String exchange_rate_rmb;
	

	/** 状态 0 关闭 1开启 */
	private Integer status;

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}