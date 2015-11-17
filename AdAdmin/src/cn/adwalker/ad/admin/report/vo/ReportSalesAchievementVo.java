/**
 * <p>Title: ReportAdByHour.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-15
 * @version 1.0
 */
package cn.adwalker.ad.admin.report.vo;

import cn.adwalker.core.util.lang.MathUtil;
import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * <p>
 * Title: ReportAdByHour
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-15
 */
public class ReportSalesAchievementVo {
	// 主键
	private Long id;
	// 广告主ID
	private Long adv_Id;
	// 广告主名称
	private String adv_email;
	// 活动id
	private Long campaign_id;
	// 活动名称
	private String campaign_name;
	// 效果发生时间
	private String month_stat_date;
	// 效果发生时间
	private String month_end_date;
	// 效果发生时间
	private String static_date;
	// 平台类型
	private String os;
	//
	private String area_type_name;
	// 销售人
	private String sales_name;
	// 大区
	private Integer area_type;
	// 确认收入
	private Double income_money;
	//单价
	private Double price;
	// 预确认收入
	private Double forecast_money;
	// 收入差额
	private Double balance;
	// 收入差额
	private Integer income_amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdv_Id() {
		return adv_Id;
	}

	public void setAdv_Id(Long adv_Id) {
		this.adv_Id = adv_Id;
	}

	public String getAdv_email() {
		return adv_email;
	}

	public void setAdv_email(String adv_email) {
		this.adv_email = adv_email;
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Double getIncome_money() {
		if (income_amount != null && price != null) {
			income_money = MathUtil.multiply(income_amount, price);
		}
		return income_money;
	}

	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}
	public Double getBalance() {
		if (ObjectUtils.isNotEmpty(income_money)&& ObjectUtils.isNotEmpty(forecast_money)) {
			balance = income_money - forecast_money;
		}
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getForecast_money() {
		return forecast_money;
	}

	public void setForecast_money(Double forecast_money) {
		this.forecast_money = forecast_money;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getIncome_amount() {
		return income_amount;
	}

	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
	}

	public String getMonth_end_date() {
		return month_end_date;
	}

	public void setMonth_end_date(String month_end_date) {
		this.month_end_date = month_end_date;
	}

	public String getStatic_date() {
		if(ObjectUtils.isNotEmpty(month_stat_date)&& ObjectUtils.isNotEmpty(month_end_date)){
			static_date=this.month_stat_date+"至"+this.month_end_date;
		}
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public String getSales_name() {
		return sales_name;
	}

	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}

	public Integer getArea_type() {
		return area_type;
	}

	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
	}
	public String getArea_type_name() {
		if(area_type!=null){
			if(area_type==0){
				area_type_name="平台";
			}else if(area_type==4){
				area_type_name="华南";
			}else if(area_type==1){
				area_type_name="华东";
			}else if(area_type==2){
				area_type_name="华北";
			}
		}
		return area_type_name;
	}

	public void setArea_type_name(String area_type_name) {
		this.area_type_name = area_type_name;
	}

	
}
