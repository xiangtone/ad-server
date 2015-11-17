package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

import cn.adwalker.core.util.Function;
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
public class AchievementReportVo {
	
	// 主键
	private Long id;
	// 广告主id
	private Long adv_id;
	
	private String month;
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
	private String cost_date;
	// 广告主确认数
	private Integer income_amount;
	// 预确认数
	private Integer forecast_amount;
	// 渠道数
	private Integer channel_amount;
	// 状态
	private Integer status;
	// 状态名称
	private String status_name;
	// 总收入
	private Double income_money;
	// 总成本
	private Double cost_money;

	// 平台收入
	private Double platform_income_money;
	// 平台成本
	private Double platform_cost_money;

	// 渠道收入
	private Double channel_income_money;
	// 渠道成本
	private Double channel_cost_money;
	// 渠道毛利率=（渠道收入 – 渠道成本）/渠道收入 * 100%；
	private Double cha_gross_profit_rate;

	// 单价
	private Double price;
	// 创建人
	private String create_user_name;
	// 销售人员
	private String name;
	// 计算方式
	private String charge_type;
	// 平台类型
	private String os;
	// 大区
	private Integer area_type;
	// 大区名称
	private String area_type_name;
	
	
	/**
	 * 业绩提交时间
	 */
	private Date publish_time;
	
	/**
	 * 收入确认时间
	 */
	private Date confirm_time;

	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	public Date getConfirm_time() {
		return confirm_time;
	}

	public void setConfirm_time(Date confirm_time) {
		this.confirm_time = confirm_time;
	}

	// 总毛利率=（总收入 - 总成本）/总收入 * 100%；
	public Double getGross_profit_rate() {
		double d = 0d;
		if (ObjectUtils.isNotEmpty(income_money)
				&& ObjectUtils.isNotEmpty(cost_money) && income_money > 0) {
			d = Function.divide(Function.subtract(income_money, cost_money),
					income_money) * 100;
		}
		return d;
	}

	// 平台毛利率=（平台收入 – 平台成本）/平台收入 * 100%；
	public Double getPla_gross_profit_rate() {
		double d = 0d;
		if (ObjectUtils.isNotEmpty(platform_income_money)
				&& ObjectUtils.isNotEmpty(platform_cost_money)
				&& platform_income_money > 0) {
			d = Function.divide(Function.subtract(platform_income_money,
					platform_cost_money), platform_income_money) * 100;
		}
		return d;
	}

	// 损耗=（预确认数 – 确认数）/ 预确认数 * 100%；
	public Double getUllage() {
		double d = 0d;
		if (ObjectUtils.isNotEmpty(forecast_amount)
				&& ObjectUtils.isNotEmpty(income_amount) && forecast_amount > 0) {
			d = Function.divide(
					Function.subtract(forecast_amount, income_amount),
					forecast_amount) * 100;
		}
		return d;
	}

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

	public String getMonth_stat_date() {
		return month_stat_date;
	}

	public void setMonth_stat_date(String month_stat_date) {
		this.month_stat_date = month_stat_date;
	}

	public Integer getIncome_amount() {
		return income_amount;
	}

	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
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

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMonth_end_date() {
		return month_end_date;
	}

	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getArea_type() {
		return area_type;
	}

	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}

	public Integer getForecast_amount() {
		return forecast_amount;
	}

	public void setForecast_amount(Integer forecast_amount) {
		this.forecast_amount = forecast_amount;
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

	public String getArea_type_name() {
		if (area_type != null) {
			if (area_type == 4) {
				area_type_name = "福建易达营销部";
			} else if (area_type == 1) {
				area_type_name = "广东易达营销部";
			} else if (area_type == 2) {
				area_type_name = "华北易达营销部";
			} else if(area_type == 3){
				area_type_name = "上海易达营销部";
			}else if(area_type == 5){
				area_type_name = "杭州易达营销部";
			}else if(area_type == 6){
				area_type_name = "易达项目部";
			}else{
				area_type_name = "易积分";
			}
		}
		return area_type_name;
	}

	public void setArea_type_name(String area_type_name) {
		this.area_type_name = area_type_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public Double getCost_money() {
		if (ObjectUtils.isNotEmpty(platform_cost_money)) {
			if (ObjectUtils.isNotEmpty(channel_cost_money)) {
				cost_money = platform_cost_money + channel_cost_money;
			} else {
				cost_money = platform_cost_money;
			}
		} else {
			cost_money = channel_cost_money;
		}
		return cost_money;
	}

	public void setCost_money(Double cost_money) {
		this.cost_money = cost_money;
	}

	public Double getPlatform_income_money() {
		return platform_income_money;
	}

	public void setPlatform_income_money(Double platform_income_money) {
		this.platform_income_money = platform_income_money;
	}

	public Double getPlatform_cost_money() {
		return platform_cost_money;
	}

	public void setPlatform_cost_money(Double platform_cost_money) {
		this.platform_cost_money = platform_cost_money;
	}

	public Double getChannel_income_money() {

		return channel_income_money;
	}

	public void setChannel_income_money(Double channel_income_money) {
		this.channel_income_money = channel_income_money;
	}

	public Double getChannel_cost_money() {
		return channel_cost_money;
	}

	public void setChannel_cost_money(Double channel_cost_money) {
		this.channel_cost_money = channel_cost_money;
	}

	public Double getCha_gross_profit_rate() {
		if (ObjectUtils.isNotEmpty(channel_income_money)
				&& ObjectUtils.isNotEmpty(channel_cost_money)) {
			if(channel_income_money<=0){
				return 0d;
			}
			cha_gross_profit_rate = (channel_income_money - channel_cost_money)
					/ channel_income_money * 100;
		}
		return cha_gross_profit_rate;
	}

	public void setCha_gross_profit_rate(Double cha_gross_profit_rate) {
		this.cha_gross_profit_rate = cha_gross_profit_rate;
	}

	public Integer getChannel_amount() {
		return channel_amount;
	}

	public void setChannel_amount(Integer channel_amount) {
		this.channel_amount = channel_amount;
	}

	public String getCost_date() {
		return cost_date;
	}

	public void setCost_date(String cost_date) {
		this.cost_date = cost_date;
	}

	/**
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	
	public void setMonth(String month) {
		this.month = month;
	}

	public String getStr_ullage() {
		Double a=getUllage();
		if(a!=null&&a>0){
			return a+"%";
		}else{
			return "0.00%";
		}
	}

	public String getStr_gross_profit_rate() {
		Double a=getGross_profit_rate();
		if(a!=null&&a>0){
			return a+"%";
		}else{
			return "0.00%";
		}
	}

	public String getStr_pla_gross_profit_rate() {
		Double a=getPla_gross_profit_rate();
		if(a!=null&&a>0){
			return a+"%";
		}else{
			return "0.00%";
		}
	}

	public String getStr_cha_gross_profit_rate() {
		Double a=getCha_gross_profit_rate();
		if(a!=null&&a>0){
			return a+"%";
		}else{
			return "0.00%";
		}
	}
}
