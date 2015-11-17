package cn.adwalker.ad.admin.operation.vo;

public class AdEffectIosVo {
	// 主键  
	private Long id;

	/**
	 * 活动id
	 */
	private Long campaign_id;

	private Double price;
	// 状态
	private Integer status;
	// 活动名称
	private String campaign_name;

	private Double confirm_total;

	// 效果发生时间
	private String start_time;

	private String end_time;

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

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getConfirm_total() {
		return confirm_total;
	}

	public void setConfirm_total(Double confirm_total) {
		this.confirm_total = confirm_total;
	}
	
	
}
