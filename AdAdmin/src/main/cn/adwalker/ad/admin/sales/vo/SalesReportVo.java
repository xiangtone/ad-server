package cn.adwalker.ad.admin.sales.vo;

import java.io.Serializable;

public class SalesReportVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5650713941456732101L;

	private Long adv_id;

	private String adv_name;

	private String campaign_name;

	private Long campaign_id;

	private String sales_name;

	private Integer confirm_amount;

	private Double confirm_money;

	private String os;

	private String charage_type;

	public double getIn_price() {
		double d = 0d;
		if (confirm_amount != null && confirm_money != null) {
			d = confirm_money / confirm_amount;
		}
		return d;

	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public String getAdv_name() {
		return adv_name;
	}

	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public Double getConfirm_money() {
		return confirm_money;
	}

	public void setConfirm_money(Double confirm_money) {
		this.confirm_money = confirm_money;
	}

	public String getCharage_type() {
		return charage_type;
	}

	public void setCharage_type(String charage_type) {
		this.charage_type = charage_type;
	}

	public String getSales_name() {
		return sales_name;
	}

	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}

}
