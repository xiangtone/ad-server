package cn.adwalker.ad.admin.ad.form;

import java.sql.Timestamp;

/**
 * <p>
 * Title: AdAjustmentEditForm
 * </p>
 * <p>
 * Description:update提交form   
 * </p>
 * <p>
 * Company: adwalker   
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class AdAjustmentEditForm {

	// 主键
	private Long id;
	// 排期开始时间
	private Timestamp plan_start;
	// 排期结束时间
	private Timestamp plan_end;
	// 结算方式
	private String charge_type;
	// 渠道包号
	private Long packageId;
	// 单价
	private Double price;
	// 日投放量
	private Double budget_day;
	// 投放单位
	private String budget_type;
	
	
	private Integer confirm_type;

	/**
	 * 
	 */
	private Integer schedule_start;

	/**
	 * 
	 */
	private Integer schedule_end;
	
	private Integer fast_task;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getPlan_start() {
		return plan_start;
	}

	public void setPlan_start(Timestamp plan_start) {
		this.plan_start = plan_start;
	}

	public Timestamp getPlan_end() {
		return plan_end;
	}

	public void setPlan_end(Timestamp plan_end) {
		this.plan_end = plan_end;
	}

	public String getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Double budget_day) {
		this.budget_day = budget_day;
	}

	public String getBudget_type() {
		return budget_type;
	}

	public void setBudget_type(String budget_type) {
		this.budget_type = budget_type;
	}

	public Integer getSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(Integer schedule_start) {
		this.schedule_start = schedule_start;
	}

	public Integer getSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(Integer schedule_end) {
		this.schedule_end = schedule_end;
	}

	public Integer getFast_task() {
		return fast_task;
	}

	public void setFast_task(Integer fast_task) {
		this.fast_task = fast_task;
	}

	public Integer getConfirm_type() {
		return confirm_type;
	}

	public void setConfirm_type(Integer confirm_type) {
		this.confirm_type = confirm_type;
	}
	
}