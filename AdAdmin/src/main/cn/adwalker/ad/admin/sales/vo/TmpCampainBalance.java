package cn.adwalker.ad.admin.sales.vo;

import java.io.Serializable;

public class TmpCampainBalance implements Serializable {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -7182079689462889236L;

	private Long campaign_id;

	private String charge_type;

	private Integer confirm_amount;
	
	private Double confirm_money;

	
	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
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

	public Double getConfirm_money() {
		return confirm_money;
	}

	public void setConfirm_money(Double confirm_money) {
		this.confirm_money = confirm_money;
	}
}
