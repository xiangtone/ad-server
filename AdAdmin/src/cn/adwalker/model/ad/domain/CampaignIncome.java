package cn.adwalker.model.ad.domain;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: CampaignIncome
 * </p>
 * <p>
 * Description:活动收入
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-15
 */
public class CampaignIncome implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -3658024358724175341L;
	/** 主键 */
	private Long id;

	/** 广告主id */
	private Long campaign_id;

	/**
	 * 日期
	 */
	private String static_date;

	private Integer confirm_num;

	/** 操作系统 */
	private String os;

	/** 广告出价 */
	private Double price;

	/** 状态 */
	private Integer status;

	/*** 财务单价 **/
	private Double finance_price;

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Double getFinance_price() {
		return finance_price;
	}

	public void setFinance_price(Double finance_price) {
		this.finance_price = finance_price;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

}
