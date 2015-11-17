package cn.adwalker.ad.model.channel.domain;

/**
 * 渠道帐号信息
 * @author Administrator
 * 
 */
public class UserChannels {
	
	private Long id;
	//状态
	private Integer userType;
	
	private String email;
	//渠道账号密码
	private String password;
	//渠道名称
	private String channel_name;
	//渠道Id
	private String channel_id;
	//合作方式
	private Integer channe_mode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public Integer getChanne_mode() {
		return channe_mode;
	}
	public void setChanne_mode(Integer channe_mode) {
		this.channe_mode = channe_mode;
	}
	
	
}
