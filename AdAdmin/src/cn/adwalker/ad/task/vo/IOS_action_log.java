package cn.adwalker.ad.task.vo;

public class IOS_action_log {

	private String id;
	private String campaign_config_id;
	private String channel_config_id;
	private Long placement_id;
	private Long channel_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCampaign_config_id() {
		return campaign_config_id;
	}

	public void setCampaign_config_id(String campaign_config_id) {
		this.campaign_config_id = campaign_config_id;
	}

	public String getChannel_config_id() {
		return channel_config_id;
	}

	public void setChannel_config_id(String channel_config_id) {
		this.channel_config_id = channel_config_id;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public Long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}
}
