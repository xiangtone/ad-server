package cn.adwalker.ad.admin.operation.bean;

/**
* <p>Title: IosNumDetailbean</p>
* <p>Description:ios效果明细的bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-11-14
 */
public class IosNumDetailbean {
	// 主键
	private Long id;
	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String static_start_date;
	// 效果结束时间
	private String static_end_date;
	//媒体名称
	private String media_name;
	//平台类型
	private Integer media_type;
	
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
	public String getMedia_name() {
		return media_name;
	}
	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}
	public Integer getMedia_type() {
		return media_type;
	}
	public void setMedia_type(Integer media_type) {
		this.media_type = media_type;
	}

	
	
}
