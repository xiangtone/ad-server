package cn.adwalker.ad.admin.operation.vo;


/**
* <p>Title: CampaignConfirmEditVo</p>
* <p>Description:确认收入Vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-8
 */
public class CampaignConfirmEditVo {
	//主键
	private Long id;
	//广告主id
	private Long adv_id;
	//活动id
	private Long campaign_id;
	//活动名称
	private String campaign_name;
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
	//发票状态
	private Integer invoice_status;	
	//状态名称
	private String status_name;	
	//图片
	private String data_photo_url;	
	//结算金额
	private Double income_money;
	//单价
	private Double price;
	// 创建人
	private String create_user_name;
	//销售人员
	private String name;

	//平台类型
	private String os;
	//大区
	private Integer area_type;	
	//大区名称
	private String area_type_name;
	
	//结算方式
	private String balance_model;
	
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
		if(status!= null){
		if(status==1){
			status_name="已发布";
		}else if(status==2){
			status_name="生成发票";
			
		}else if(status==0){
			status_name="未发布";
		}
		}
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	public String getArea_type_name() {
		if(area_type!=null){
			if(area_type==4){
				area_type_name="华南";
			}else if(area_type==1){
				area_type_name="华北";
			}else if(area_type==2){
				area_type_name="生成发票";
			}else{
				area_type_name="平台";
			}
		}
		return area_type_name;
	}
	public void setArea_type_name(String area_type_name) {
		this.area_type_name = area_type_name;
	}
	public Integer getInvoice_status() {
		return invoice_status;
	}
	public void setInvoice_status(Integer invoice_status) {
		this.invoice_status = invoice_status;
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
}
