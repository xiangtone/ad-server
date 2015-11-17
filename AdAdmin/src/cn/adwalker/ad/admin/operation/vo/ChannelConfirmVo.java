package cn.adwalker.ad.admin.operation.vo;

import cn.adwalker.core.util.lang.ObjectUtils;


/**
* <p>Title: ChannelConfirmVo</p>
* <p>Description:渠道成本Vo</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年11月17日
 */
public class ChannelConfirmVo {
	//主键
	private Long id;
	//广告主id
	private Long adv_id;
	//活动id
	private Long campaign_id;
	//活动名称
	private String campaign_name;
	//渠道id
	private Long channel_id;
	//渠道名称
	private String channel_name;
	//效果发生开始时间
	private String month_stat_date;
	//效果发生结束时间
	private String month_end_date;
	//广告主确认数
	private Integer income_amount;
	//预确认数
	private Integer forecast_amount;
	//状态
	private Integer status;	
	//图片
	private String data_photo_url;	
	//预确认金额
	private Double forecast_money;
	//结算金额
	private Double income_money;
	//单价
	private Double price;
	//核减比例
	private Double subtract_ratio;
	// 创建人
	private String create_user_name;
	//销售人员
	private String name;
	//渠道人员
	private String channe_manager;
	//计算方式
	private String charge_type;
	//平台类型
	private String os;

	//结算方式
	private String balance_model;
	//备注
	private String income_remark;
	
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

	public String getData_photo_url() {
		return data_photo_url;
	}
	public void setData_photo_url(String data_photo_url) {
		this.data_photo_url = data_photo_url;
	}
	public String getBalance_model() {
		return balance_model;
	}
	public void setBalance_model(String balance_model) {
		this.balance_model = balance_model;
	}
	public String getIncome_remark() {
		return income_remark;
	}
	public void setIncome_remark(String income_remark) {
		this.income_remark = income_remark;
	}
	public Double getForecast_money() {
		return forecast_money;
	}
	public void setForecast_money(Double forecast_money) {
		this.forecast_money = forecast_money;
	}
	public Double getSubtract_ratio() {
		if(ObjectUtils.isNotEmpty(forecast_money)&& ObjectUtils.isNotEmpty(income_money) ){
			
			subtract_ratio=(income_money/forecast_money)*100;
			
		}
		return subtract_ratio;
	}
	public void setSubtract_ratio(Double subtract_ratio) {
		this.subtract_ratio = subtract_ratio;
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
	public String getChanne_manager() {
		return channe_manager;
	}
	public void setChanne_manager(String channe_manager) {
		this.channe_manager = channe_manager;
	}
	
	
}
