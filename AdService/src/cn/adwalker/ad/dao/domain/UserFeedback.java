package cn.adwalker.ad.dao.domain;

public class UserFeedback {
	private long id;
	private long adId;
	private String uuid;
	private String version;
	private String desc;
	private String email;
	private Integer type;
	
	
	public UserFeedback() {
	}
	
	public UserFeedback(long adId, String uuid, String version, String desc,
			String email, Integer type) {
		this.adId = adId;
		this.uuid = uuid;
		this.version = version;
		this.desc = desc;
		this.email = email;
		this.type = type;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAdId() {
		return adId;
	}
	public void setAdId(long adId) {
		this.adId = adId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
