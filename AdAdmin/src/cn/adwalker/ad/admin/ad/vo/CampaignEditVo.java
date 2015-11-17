package cn.adwalker.ad.admin.ad.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 活动主持VO  
 * <p>
 * Title: CampaignVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-7
 */
public class CampaignEditVo {
	
	// 主键
	private Long id;
	
	/**
	 * 投放
	 */
	private Long placement_id;
	

	// 活动名称
	private String campaign_name;

	// 活动类型
	private Integer campaign_type;

	// 活动类别
	private Integer category_id;
	// 状态
	private Integer status;
	// 广告主id
	private Long adv_id;
	//销售
	private Long salesman_id;

	// 计费方式
	private String charge_type;
	// 接入单价
	private Double price;
	// 预算
	private Double budget;
	
	// 加价率
	private Integer balance_cycle;
	
	// 确认方式（0线下、1线上）
	private Integer confirm_mode;
	/**
	 * 限量要求
	 */
	private String campaign_required;
	// 平台类型
	private String os;
	
	// 排期开始时间
	private Date plan_start;
	
	// 排期结束时间
	private Date plan_end;
	// 数据入库时间
	private Timestamp create_time;

	// 操作人id
	private Long create_user;

	// 区域定向
	private Integer area_directional;
	// sdk定向
	private Integer sdk_version;

	// 应用类型
	private Integer app_type;


	// 终端类型
	private Integer terminal_type;

	// 时段定向
	private Integer time_directional;
	
	/**
	 * 手机品牌
	 */
	private Integer phone_brand;
	/**
	 * 运营商
	 */
	private Integer operat_agencies;
	
	//  增加 IS_DSP 判断是否是DSP广告 默认为0（否）,
	private Integer is_dsp;
	//   增加 DSP_ID。DSP标识. 
	private String dsp_id;
	
	//广告来源
	private Integer res_type;
	/********************** getter\setter方法 ****************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCampaign_type() {
		return campaign_type;
	}

	public void setCampaign_type(Integer campaign_type) {
		this.campaign_type = campaign_type;
	}

	public Integer getTime_directional() {
		return time_directional;
	}

	public void setTime_directional(Integer time_directional) {
		this.time_directional = time_directional;
	}

	public Integer getTerminal_type() {
		return terminal_type;
	}

	public void setTerminal_type(Integer terminal_type) {
		this.terminal_type = terminal_type;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}
	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Integer getApp_type() {
		return app_type;
	}

	public void setApp_type(Integer app_type) {
		this.app_type = app_type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public Integer getArea_directional() {
		return area_directional;
	}

	public void setArea_directional(Integer area_directional) {
		this.area_directional = area_directional;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public Date getPlan_start() {
		return plan_start;
	}

	public void setPlan_start(Date plan_start) {
		this.plan_start = plan_start;
	}

	public Date getPlan_end() {
		return plan_end;
	}

	public void setPlan_end(Date plan_end) {
		this.plan_end = plan_end;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Integer getPhone_brand() {
		return phone_brand;
	}

	public void setPhone_brand(Integer phone_brand) {
		this.phone_brand = phone_brand;
	}

	public Integer getOperat_agencies() {
		return operat_agencies;
	}

	public void setOperat_agencies(Integer operat_agencies) {
		this.operat_agencies = operat_agencies;
	}

	public String getCampaign_required() {
		return campaign_required;
	}

	public void setCampaign_required(String campaign_required) {
		this.campaign_required = campaign_required;
	}

	/**
	 * @return balance_cycle
	 */
	public Integer getBalance_cycle() {
		return balance_cycle;
	}

	/**
	 * @param balance_cycle the balance_cycle to set
	 */
	
	public void setBalance_cycle(Integer balance_cycle) {
		this.balance_cycle = balance_cycle;
	}

	public Integer getConfirm_mode() {
		return confirm_mode;
	}

	public void setConfirm_mode(Integer confirm_mode) {
		this.confirm_mode = confirm_mode;
	}

	public Integer getSdk_version() {
		return sdk_version;
	}

	public void setSdk_version(Integer sdk_version) {
		this.sdk_version = sdk_version;
	}

	public Integer getIs_dsp() {
		return is_dsp;
	}

	public void setIs_dsp(Integer is_dsp) {
		this.is_dsp = is_dsp;
	}

	public String getDsp_id() {
		return dsp_id;
	}

	public void setDsp_id(String dsp_id) {
		this.dsp_id = dsp_id;
	}

	/**
	 * @return salesman_id
	 */
	public Long getSalesman_id() {
		return salesman_id;
	}

	/**
	 * @param salesman_id the salesman_id to set
	 */
	
	public void setSalesman_id(Long salesman_id) {
		this.salesman_id = salesman_id;
	}

	public Integer getRes_type() {
		return res_type;
	}

	public void setRes_type(Integer res_type) {
		this.res_type = res_type;
	}
}
