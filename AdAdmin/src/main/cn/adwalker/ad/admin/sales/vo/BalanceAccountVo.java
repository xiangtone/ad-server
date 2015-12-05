package cn.adwalker.ad.admin.sales.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Title: AdByPlacementVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class BalanceAccountVo {
	
	private Long id;

	// 排期开始时间
	private Date start_date;

	// 排期结束时间
	private Date end_date;
	
	private String salesman_name;
	
	private BigDecimal forecast_money;
	
	
	private Integer forecast_amount;
	
	private BigDecimal income_money;
	
	// 广告主id
	private Long adv_id;
	// 广告主邮箱
	private String adv_email;

	// 广告主邮箱
	private String adv_name;
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;
	// 投放名称
	private String placement_name;
	// 平台类型
	private String os;
	
	private String charge_type;
	// t投放状态（0:上线1:人工下线,-10:推广结束,-20:超量下线）
	private Integer status;
	
	private Integer bad_debt;
	
	
	
	//回款金额
	private BigDecimal payments;
	
	
	/**
	 * 销售对账状态
	 */
	private Integer balance_status;
	

	
	/**
	 * @return adv_id
	 */
	public Long getAdv_id() {
		return adv_id;
	}

	/**
	 * @param adv_id the adv_id to set
	 */
	
	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	/**
	 * @return adv_email
	 */
	public String getAdv_email() {
		return adv_email;
	}

	/**
	 * @param adv_email the adv_email to set
	 */
	
	public void setAdv_email(String adv_email) {
		this.adv_email = adv_email;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return charge_type
	 */
	public String getCharge_type() {
		return charge_type;
	}

	/**
	 * @param charge_type the charge_type to set
	 */
	
	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public String getAdv_name() {
		return adv_name;
	}

	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}

	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return bad_debt
	 */
	public Integer getBad_debt() {
		return bad_debt;
	}

	/**
	 * @param bad_debt the bad_debt to set
	 */
	
	public void setBad_debt(Integer bad_debt) {
		this.bad_debt = bad_debt;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}


	public String getPlacement_name() {
		return placement_name;
	}

	public void setPlacement_name(String placement_name) {
		this.placement_name = placement_name;
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
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return forecast_money
	 */
	public BigDecimal getForecast_money() {
		return forecast_money;
	}

	/**
	 * @param forecast_money the forecast_money to set
	 */
	
	public void setForecast_money(BigDecimal forecast_money) {
		this.forecast_money = forecast_money;
	}

	/**
	 * @return salesman_name
	 */
	public String getSalesman_name() {
		return salesman_name;
	}

	/**
	 * @param salesman_name the salesman_name to set
	 */
	
	public void setSalesman_name(String salesman_name) {
		this.salesman_name = salesman_name;
	}

	/**
	 * @return payments
	 */
	public BigDecimal getPayments() {
		return payments;
	}

	/**
	 * @param payments the payments to set
	 */
	
	public void setPayments(BigDecimal payments) {
		this.payments = payments;
	}

	/**
	 * @return forecast_amount
	 */
	public Integer getForecast_amount() {
		return forecast_amount;
	}

	/**
	 * @param forecast_amount the forecast_amount to set
	 */
	
	public void setForecast_amount(Integer forecast_amount) {
		this.forecast_amount = forecast_amount;
	}

	/**
	 * @return balance_status
	 */
	public Integer getBalance_status() {
		return balance_status;
	}

	/**
	 * @param balance_status the balance_status to set
	 */
	
	public void setBalance_status(Integer balance_status) {
		this.balance_status = balance_status;
	}

	/**
	 * @return income_money
	 */
	public BigDecimal getIncome_money() {
		return income_money;
	}

	/**
	 * @param income_money the income_money to set
	 */
	
	public void setIncome_money(BigDecimal income_money) {
		this.income_money = income_money;
	}
	
	
}
