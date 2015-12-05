package cn.adwalker.ad.admin.finance.bean;

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
public class CostBlanceBean {
	// 主键
	private Long id;
	

	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String static_start_date;
	// 效果结束时间
	private String static_end_date;
	// 效果时间
	private String create_time;
	//媒体名称
	private String media_name;
	
	private Integer status;
	/**
	 * 业绩提交时间开始
	 */
	private String submit_start_date;
	/**
	 * 业绩提交时间结束
	 */
	private String submit_end_date;
	/**
	 * 结算时间开始
	 */
	private String operator_start_date;
	/**
	 * 结算时间结束
	 */
	private String operator_end_date;
	
	/**
	 * 渠道账号：email
	 */
	private String channel_account;
	

	public String getChannel_account() {
		return channel_account;
	}

	public void setChannel_account(String channel_account) {
		this.channel_account = channel_account;
	}

	public String getOperator_start_date() {
		return operator_start_date;
	}

	public void setOperator_start_date(String operator_start_date) {
		this.operator_start_date = operator_start_date;
	}

	public String getOperator_end_date() {
		return operator_end_date;
	}

	public void setOperator_end_date(String operator_end_date) {
		this.operator_end_date = operator_end_date;
	}

	public String getSubmit_start_date() {
		return submit_start_date;
	}

	public void setSubmit_start_date(String submit_start_date) {
		this.submit_start_date = submit_start_date;
	}

	public String getSubmit_end_date() {
		return submit_end_date;
	}

	public void setSubmit_end_date(String submit_end_date) {
		this.submit_end_date = submit_end_date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
