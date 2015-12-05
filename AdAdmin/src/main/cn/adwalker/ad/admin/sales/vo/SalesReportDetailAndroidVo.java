package cn.adwalker.ad.admin.sales.vo;

import java.io.Serializable;

public class SalesReportDetailAndroidVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5650713941456732101L;

	private String static_date;

	private String file_name;

	private String charge_type;

	private Integer sys_activate;

	private Integer confirm_amount;

	private Double in_price;

	public double getConfirm_money() {
		double d = 0d;
		if (in_price != null && confirm_amount != null) {
			d = in_price * confirm_amount;
		}
		return d;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getSys_activate() {
		return sys_activate;
	}

	public void setSys_activate(Integer sys_activate) {
		this.sys_activate = sys_activate;
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
