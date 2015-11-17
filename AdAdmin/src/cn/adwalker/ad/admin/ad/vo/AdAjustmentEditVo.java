package cn.adwalker.ad.admin.ad.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.adwalker.ad.admin.operation.vo.OperationPlacementPackageVo;

/**
 * <p>
 * Title: AdAjustmentEditVo
 * </p>
 * <p>
 * Description:广告管理修改回显Vo 
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-4
 */
public class AdAjustmentEditVo {
	// 主键
	private Long id;
	// 排期开始时间
	private Date schedule_start_time;

	/**
	 * 排期开始是否开启
	 */
	private boolean schedule_start;
	// 排期结束时间
	private Date schedule_end_time;

	/**
	 * 排期结束是否开启
	 */
	private boolean schedule_end;
	// 结算方式
	private String charge_type;
	// 渠道包号
	private Long packageId;
	// 单价
	private Double price;
	// 日投放量
	private Integer budget_day;
	// 投放单位
	private String budget_type;
	
	// 日投放量
	private Integer status;
	
	private Long type_id;
	
	private Integer fast_task;
	
	
	private Integer confirm_type;//确认方式，目前只在微信墙上使用
	

	/**
	 * 投放id
	 */
	private Long placementID;

	private List<OperationPlacementPackageVo> list = new ArrayList<OperationPlacementPackageVo>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSchedule_start_time() {
		return schedule_start_time;
	}

	public void setSchedule_start_time(Date schedule_start_time) {
		this.schedule_start_time = schedule_start_time;
	}

	public Date getSchedule_end_time() {
		return schedule_end_time;
	}

	public void setSchedule_end_time(Date schedule_end_time) {
		this.schedule_end_time = schedule_end_time;
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

	public List<OperationPlacementPackageVo> getList() {
		return list;
	}

	public void setList(List<OperationPlacementPackageVo> list) {
		this.list = list;
	}

	public Long getPlacementID() {
		return placementID;
	}

	public void setPlacementID(Long placementID) {
		this.placementID = placementID;
	}

	public Integer getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Integer budget_day) {
		this.budget_day = budget_day;
	}

	public String getBudget_type() {
		return budget_type;
	}

	public void setBudget_type(String budget_type) {
		this.budget_type = budget_type;
	}

	public boolean isSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(boolean schedule_start) {
		this.schedule_start = schedule_start;
	}

	public boolean isSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(boolean schedule_end) {
		this.schedule_end = schedule_end;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
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
