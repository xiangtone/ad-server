package cn.adwalker.ad.admin.sales.vo;

import java.io.Serializable;

public class SalesReportDetailIosVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5650713941456732101L;

	private String static_date;

	private String charge_type;

	private Integer click;

	private Integer activate;

	private Integer confirm_amount;

	private Double in_price;

	public double getConfirm_money() {
		double d = 0d;
		if (in_price != null && confirm_amount != null) {
			d = in_price * confirm_amount;
		}
		return d;
	}

	/**
	 * 
	* <p>Title: getCtra</p>
	* <p>Description:激活转化率</p>
	* @return
	* @author cuidd
	* @date 2014-8-14
	* @return Double
	* @version 1.0
	 */
	public Double getCtra() {
		double d=0d;
		if (activate!=null&&click!=null) {
			d=(100d*activate)/click;
		}
		return d;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public Double getIn_price() {
		return in_price;
	}

	public void setIn_price(Double in_price) {
		this.in_price = in_price;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

}
