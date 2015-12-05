package cn.adwalker.ad.admin.channel.vo;

public class ChannelScaleVo {

	private Long id;
	private Long media_id;
	private String media_name;
	private Long campaign_id;
	private String campaign_name;

	private String os;
	private Double scale;

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public Integer getScale_int() {
		Double d = null;
		if (scale != null) {
			d = (Double) scale * 100;
		}
		return d != null ? d.intValue() : null;

	}

}
