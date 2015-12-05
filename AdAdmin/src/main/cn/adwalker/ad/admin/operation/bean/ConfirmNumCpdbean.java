package cn.adwalker.ad.admin.operation.bean;

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
public class ConfirmNumCpdbean {
	// 主键
	private Long id;
	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String create_time;
	// 开始
	private String create_time_sart;
	// 结束
	private String create_time_end;
	// 媒体ID
	private Long media_id;
	// 活动ID
	private Long campaign_id;
	/**
	 * 媒体名称
	 */
	private String media_name;
	
	private Integer status;

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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreate_time_sart() {
		return create_time_sart;
	}

	public void setCreate_time_sart(String create_time_sart) {
		this.create_time_sart = create_time_sart;
	}

	public String getCreate_time_end() {
		return create_time_end;
	}

	public void setCreate_time_end(String create_time_end) {
		this.create_time_end = create_time_end;
	}

	
}
