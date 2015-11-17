package cn.adwalker.ad.admin.operation.vo;

public class AdPriceVo {
	private Long id;
	private Long media_id;
	private String media_name;
	private String adv_email;
	private String os;
	private String adForm;
	private Long campaign_id;
	private String campaign_name;
	private String blanceMode;
	private Double currentPrice;
	private String effectTime;
	
	
 
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
	public Long getMedia_id() {
		return media_id;
	}
	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}
	public String getMedia_name() {
		return media_name;
	}
	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}
	public String getAdv_email() {
		return adv_email;
	}
	public void setAdv_email(String adv_email) {
		this.adv_email = adv_email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdForm() {
		return adForm;
	}
	public void setAdForm(String adForm) {
		this.adForm = adForm;
	}
	public String getBlanceMode() {
		return blanceMode;
	}
	public void setBlanceMode(String blanceMode) {
		this.blanceMode = blanceMode;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getEffectTime() {
		return effectTime;
	}
	public void setEffectTime(String effectTime) {
		this.effectTime = effectTime;
	}
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
