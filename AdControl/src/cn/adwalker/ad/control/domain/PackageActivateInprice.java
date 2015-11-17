package cn.adwalker.ad.control.domain;

import java.math.BigDecimal;

public class PackageActivateInprice {
	private Long id;
	
	private Long campaign_id;
	
	private String charge_type;
	
	private BigDecimal inprice;
	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return inprice
	 */
	public BigDecimal getInprice() {
		return inprice;
	}
	/**
	 * @param inprice the inprice to set
	 */
	
	public void setInprice(BigDecimal inprice) {
		this.inprice = inprice;
	}
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
}
