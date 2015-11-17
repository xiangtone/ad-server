package cn.adwalker.ad.admin.app.vo;

public class MediaRatingVo {
	private Long devId;
	private String devEmail;
	private Long mediaId;
	private String mediaName;
	private String os;
	private Double scale;
	private String adForm;
	private Long id;

	public Long getDevId() {
		return devId;
	}

	public void setDevId(Long devId) {
		this.devId = devId;
	}

	public String getDevEmail() {
		return devEmail;
	}

	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}


	public Double getScale() {
		return scale;
	}
	
	public Integer getScale_int() {
		Double d = null;
		if (scale != null) {
			d = (Double) scale * 100;
		}
		return d != null ? d.intValue() : null;

	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public String getAdForm() {
		return adForm;
	}

	public void setAdForm(String adForm) {
		this.adForm = adForm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
