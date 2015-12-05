package cn.adwalker.ad.admin.finance.vo;

import cn.adwalker.core.util.lang.ObjectUtils;


/**
 * <p>
 * Title: CampaignConfirmEditVo
 * </p>
 * <p>
 * Description:确认收入Vo
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-8
 */
public class IncomeDetailVo {
	
	// 主键
	private Long id;
	// 广告主id
	private Long adv_id;
	// 广告主名称
	private String company_name;
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;
	// 时间
	private String static_date;
	// 预确认数
	private Integer confirm_amount;
	
	private String status_name;
	// 总收入
	private Double income_money;
	// 单价
	private Double price;
	// 销售人员
	private String name;
	// 平台类型
	private String os;
	//结算方式
	private String charge_type;
	//结算状态
	private Integer status;
	//结算单号
	private Long balance_id;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public Double getIncome_money() {
		if(ObjectUtils.isNotEmpty(price)&&ObjectUtils.isNotEmpty(confirm_amount)){
			income_money=price*confirm_amount.doubleValue();
		}
		return income_money;
	}

	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getConfirm_amount() {
		return confirm_amount;
	}

	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public String getStatus_name() {
		if (status != null) {
			if (status == 3) {
				status_name = "已发布";
			} else if (status == 2) {
				status_name = "未发布";
			} else if (status == 8) {
				status_name = "通过";
			} else if (status == 9) {
				status_name = "不通过";
			}
		}
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public Long getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Long balance_id) {
		this.balance_id = balance_id;
	}

}
