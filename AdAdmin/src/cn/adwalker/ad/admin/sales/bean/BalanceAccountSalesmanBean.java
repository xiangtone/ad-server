package cn.adwalker.ad.admin.sales.bean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * <p>
 * Title: AdByPlacementBean
 * </p>
 * <p>
 * Description:广告管理bean    
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class BalanceAccountSalesmanBean {

	// 活动id
	private String campaign;

	// 平台类型
	private String os;
	// 广告主邮箱
	private String adv;
	// 结算方式
	private String charge_type;
	// 投放状态
	private Integer balance_status;
	
	private Integer area_type;
	
	private Long sales_id;
	

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getAdv_email() {
		String s = null;
		if (!StringUtils.isEmpty(adv)) {
			s = adv.trim();
		}
		return s;
	}

	public Long getAdv_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(adv) && NumberUtils.isNumber(adv)) {
			l = Long.valueOf(adv);
		}
		return l;
	}

	public Long getCampaign_id() {
		Long l = 0l;
		if (!StringUtils.isEmpty(campaign) && NumberUtils.isNumber(campaign)) {
			l = Long.valueOf(campaign);
		}
		return l;
	}
	public String getCampaign_name() {
		String s = null;
		if (!StringUtils.isEmpty(campaign)) {
			s = campaign;
		}
		return s;
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

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getAdv() {
		return adv;
	}

	public void setAdv(String adv) {
		this.adv = adv;
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

	/**
	 * @return area_type
	 */
	public Integer getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type the area_type to set
	 */
	
	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}

	/**
	 * @return sales_id
	 */
	public Long getSales_id() {
		return sales_id;
	}

	/**
	 * @param sales_id the sales_id to set
	 */
	
	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}
	
}
