package cn.adwalker.ad.admin.ad.form;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 
* <p>Title: CampaignAddForm</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014-8-15
 */
public class CampaignAddForm {
	
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
	
	//活动来源，1、直客；2、二道贩子
	private Integer res_type;
	
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
	
	//  增加 IS_DSP 判断是否是DSP广告 默认为0（否）,
	private Integer is_dsp;
	//   增加 DSP_ID。DSP标识. 
	private String dsp_id;
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
