package cn.adwalker.ad.admin.operation.vo;

/**
 * <p>
 * Title: CampaignConfirmIos
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-7-31
 */
public class CampaignConfirmIosEditVo {
	// 主键
	private Long id;

	// 活动名称
	private Long ad_id;

	// 活动名称
	private Long media_id;

	/**
	 * 
	 */
	private String start_time;

	private String end_time;

	private Long parent_id;

	private Long type_id;
	
	
	private Integer confirm_num;
	
	

	// 活动名称
	private String media_name;

	// 广告主确认数
	private Integer status;

	// 结算金额
	private Double income_money;
	// 单价
	private Double price;

	public String getType_name() {
		String s = null;
		if (type_id != null) {
			if (type_id == 0) {
				s = "积分墙";
			} else if (type_id == 1) {
				s = "推荐";
			} else if (type_id == 4) {
				s = "插屏";
			} else if (type_id == 5) {
				s = "banner";
			}
		}
		return s;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getIncome_money() {
		return income_money;
	}

	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}

	public Double getPrice() {
		return price;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	
	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
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
}
