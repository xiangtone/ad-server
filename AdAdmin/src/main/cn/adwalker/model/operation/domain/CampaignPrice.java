package cn.adwalker.model.operation.domain;

/**
* <p>Title: CampaignPrice</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-13
 */
public class CampaignPrice implements  java.io.Serializable{

	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -3658024358724175341L;
	private Long id;
	/** 活动接入单价 */
	private Double price;
	/** 活动id */
	private Long campaign_id;
	/** 渠道包号*/
	private String code;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	
	
}
