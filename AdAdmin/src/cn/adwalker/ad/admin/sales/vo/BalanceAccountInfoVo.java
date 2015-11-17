package cn.adwalker.ad.admin.sales.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class BalanceAccountInfoVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -1234074692204745748L;

	/** 开始日期 */
	private Date start_date;

	private Date end_date;

	private Long campaign_id;

	private String campaign_name;

	/**
	 * 预确认数
	 */
	private Integer forecast_amount;

	/**
	 * 预确认收入
	 */
	private BigDecimal forecast_money;

	private String adv_name;

	private List<BalanceAccountDetailInfoVo> detailList;

	/**
	 * @return campaign_id
	 */
	public Long getCampaign_id() {
		return campaign_id;
	}

	/**
	 * @param campaign_id
	 *            the campaign_id to set
	 */

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	/**
	 * @return campaign_name
	 */
	public String getCampaign_name() {
		return campaign_name;
	}

	/**
	 * @param campaign_name
	 *            the campaign_name to set
	 */

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	/**
	 * @return adv_name
	 */
	public String getAdv_name() {
		return adv_name;
	}

	/**
	 * @param adv_name
	 *            the adv_name to set
	 */

	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}

	/**
	 * @return start_date
	 */
	public Date getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date
	 *            the start_date to set
	 */

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date
	 *            the end_date to set
	 */

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return forecast_amount
	 */
	public Integer getForecast_amount() {
		return forecast_amount;
	}

	/**
	 * @param forecast_amount
	 *            the forecast_amount to set
	 */

	public void setForecast_amount(Integer forecast_amount) {
		this.forecast_amount = forecast_amount;
	}

	/**
	 * @return forecast_money
	 */
	public BigDecimal getForecast_money() {
		return forecast_money;
	}

	/**
	 * @param forecast_money
	 *            the forecast_money to set
	 */

	public void setForecast_money(BigDecimal forecast_money) {
		this.forecast_money = forecast_money;
	}

	/**
	 * @return detailList
	 */
	public List<BalanceAccountDetailInfoVo> getDetailList() {
		return detailList;
	}

	/**
	 * @param detailList
	 *            the detailList to set
	 */

	public void setDetailList(List<BalanceAccountDetailInfoVo> detailList) {
		this.detailList = detailList;
	}
}
