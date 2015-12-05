package cn.adwalker.ad.admin.operation.bean;


/**
* <p>Title: InterfaceIosBean</p>
* <p>Description:ios接口配置bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-5
 */
public class InterfaceIosBean {
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;
	
	public Long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public String getCampaign_name() {
		return campaign_name;
	}
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}
	
}
