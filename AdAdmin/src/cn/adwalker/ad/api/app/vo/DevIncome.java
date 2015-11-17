/**
 * <p>Title: CallBack.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-26
 * @version 1.0
 */
package cn.adwalker.ad.api.app.vo;

import java.util.Date;

import cn.adwalker.core.util.DateUtil;

/**
 * <p>
 * Title: CallBack
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-26
 */
public class DevIncome {

	public DevIncome() {
		this.create_time = DateUtil.formatDateTime(new Date());
	}

	/**
	 * 创建时间
	 */
	private String create_time;
	
	/**
	 * 开发者id
	 */
	private String dev_id;
	
	/**
	 * 确认总金额
	 */
	private Double confirmed_total;
	
	/**
	 * 冻结的钱
	 */
	private Double freeze_money;

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getDev_id() {
		return dev_id;
	}

	public void setDev_id(String dev_id) {
		this.dev_id = dev_id;
	}

	public Double getConfirmed_total() {
		return confirmed_total;
	}

	public void setConfirmed_total(Double confirmed_total) {
		this.confirmed_total = confirmed_total;
	}

	public Double getFreeze_money() {
		return freeze_money;
	}

	public void setFreeze_money(Double freeze_money) {
		this.freeze_money = freeze_money;
	}
}
