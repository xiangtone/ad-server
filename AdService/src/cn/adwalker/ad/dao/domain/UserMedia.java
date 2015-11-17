package cn.adwalker.ad.dao.domain;

public class UserMedia {
	private Long id;
	private Long app_id;
	private Integer user_type;
	private String uuid;
	private String create_time;
	private String itunes_account;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getItunes_account() {
		return itunes_account;
	}
	public void setItunes_account(String itunes_account) {
		this.itunes_account = itunes_account;
	}
}
