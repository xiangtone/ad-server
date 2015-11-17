package cn.adwalker.ad.control.domain;

import java.io.Serializable;

public class ActivateClickByCampaignAndType implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 5320508541573211721L;

	private Long campaign_id;
	
	private Long type_id;

	private Integer activate;

	private Integer click;

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
}

