package cn.adwalker.ad.admin.ad.vo;

import java.util.ArrayList;
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
public class AdChannelEditVo {
	// 主键
	private Long id;
	
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
