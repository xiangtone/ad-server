package cn.adwalker.model.app.domain;
/**
* <p>Title: BlackCampaignPlacement</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-7-22
 */
public class BlackCampaignPlacement {
	//投放id
	private Long placement_id;
	
	//活动id
	private String campaign_id;

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	
	
}
