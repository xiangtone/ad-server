package cn.adwalker.ad.admin.operation.vo;

public class AdEffectIosImpVo {
	// 主键
	private Long id;

	/*
	 * 
	 */
	private Long type_id;

	private Long ad_id;
	
	private Double in_price;

	private Double out_price;
	// 状态
	private Integer status;
	// 活动名称
	private String ad_name;

	private Double income;

	private Integer click_num;

	private Integer confirm_num;
	
	private Double cost;

	// 效果发生时间
	private String static_date;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public Double getOut_price() {
		return out_price;
	}

	public void setOut_price(Double out_price) {
		this.out_price = out_price;
	}

	public Integer getClick_num() {
		return click_num;
	}

	public void setClick_num(Integer click_num) {
		this.click_num = click_num;
	}

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	public Double getIn_price() {
		return in_price;
	}

	public void setIn_price(Double in_price) {
		this.in_price = in_price;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
}
