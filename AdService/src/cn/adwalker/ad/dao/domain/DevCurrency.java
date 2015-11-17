/**
* <p>Title: DevCurrency.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-7
* @version 1.0
*/
package cn.adwalker.ad.dao.domain;

import cn.adwalker.ad.bean.Data;

/**
 * <p>Title: DevCurrency</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-7
 */
public class DevCurrency extends Data {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -2431915282940625081L;
	
	/**开发者应用id*/
	private Long app_id;
	
	/**货币显示名称*/
	private String virtual_currency_name;
	
	/**货币与人民币的比例*/
	private String exchange_rate_rmb;
	
	/**用途*/
	private String application;
	
	/**状态 0 关闭 1开启*/
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

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
