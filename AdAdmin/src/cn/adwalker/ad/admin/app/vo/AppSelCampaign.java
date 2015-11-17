package cn.adwalker.ad.admin.app.vo;

public class AppSelCampaign {
	
	private Long id;
	
	private Long placement_id;
	
	
	private String campaign_name;
	
	//1打开，0关闭
	private Integer status;//注意，这个地方不是活动或者投放表里表状态，是应用配置里的状态
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
}
