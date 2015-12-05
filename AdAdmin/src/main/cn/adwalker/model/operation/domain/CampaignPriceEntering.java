package cn.adwalker.model.operation.domain;

/**
* <p>Title: CampaignPriceEntering</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-2
 */
public class CampaignPriceEntering {
	/** 主键id */
	private Long id;
	/**活动名称 */
	private String campaign_name;
	/** 广告接入价 */
	private Double price;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
