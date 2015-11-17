package cn.adwalker.ad.admin.channel.bean;

/**
 * 
 * <p>
 * Title: ConfirmNumberbean
 * </p>
 * <p>
 * Description:TODO 
 * </p>
 * <p>
 * Company: adwalker 
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-16
 */
public class ChannelEffectQueryBean {
	// 主键
	private Long id;
	// 渠道包号
	private String package_id;
	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String static_start_date;
	// 效果结束时间
	private String static_end_date;
	// 效果时间
	private String create_time;
	
	// 角色id
	private Long role_id;
	//登录人名称
	private String real_name;
	/**
	 * 
	 */
	private String media_name;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}

	public String getStatic_start_date() {
		return static_start_date;
	}

	public void setStatic_start_date(String static_start_date) {
		this.static_start_date = static_start_date;
	}

	public String getStatic_end_date() {
		return static_end_date;
	}

	public void setStatic_end_date(String static_end_date) {
		this.static_end_date = static_end_date;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
}
