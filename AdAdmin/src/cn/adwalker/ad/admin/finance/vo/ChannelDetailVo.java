package cn.adwalker.ad.admin.finance.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: PlatformDetailVo
 * </p>
 * <p>
 * Description:渠道成本明细
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-10-26
 */
public class ChannelDetailVo {

	/** 主键 */
	private Long id;
	/** 渠道id */
	private Long channel_id;
	/** 渠道名称 */
	private String channel_name;
	// 广告形式
	private Long type_id;
	// 广告主id
	private Long adv_id;
	// 广告主名称
	private String company_name;
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;
	// 效果发生开始时间
	private String month_stat_date;
	// 效果发生结束时间
	private String month_end_date;
	// 成本时间
	private String cost_time;
	// 广告主确认数
	private Integer income_amount;
	// 单价
	private Double price;
	// 计算方式
	private String charge_type;
	// 平台类型
	private String os;
	// 金额
	private Double income_money;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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

	public String getMonth_stat_date() {
		return month_stat_date;
	}

	public void setMonth_stat_date(String month_stat_date) {
		this.month_stat_date = month_stat_date;
	}

	public String getMonth_end_date() {
		return month_end_date;
	}

	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
	}

	public String getCost_time() {
		return cost_time;
	}

	public void setCost_time(String cost_time) {
		this.cost_time = cost_time;
	}

	public Integer getIncome_amount() {
		return income_amount;
	}

	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
	}

	public Double getPrice() {
		if (ObjectUtils.isNotEmpty(income_money)
				&& ObjectUtils.isNotEmpty(income_amount)) {
			price = income_money / income_amount;
		}
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Double getIncome_money() {
		return income_money;
	}

	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}

	public Long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

}
