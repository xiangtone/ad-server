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
public class ConfirmNumChannelbean {
	// 主键
	private Long id;
	// 渠道包号
	private String package_id;

	// 渠道包号
	private String package_code;

	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String create_time;
	/**
	 * 媒体名称
	 */
	private String media_name;
	// 状态
	private Integer page_status;

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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
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

	public String getPackage_code() {
		return package_code;
	}

	public void setPackage_code(String package_code) {
		this.package_code = package_code;
	}

	public Integer getPage_status() {
		return page_status;
	}

	public void setPage_status(Integer page_status) {
		this.page_status = page_status;
	}
}
