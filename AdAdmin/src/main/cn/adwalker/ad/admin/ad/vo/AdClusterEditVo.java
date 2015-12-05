package cn.adwalker.ad.admin.ad.vo;


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
public class AdClusterEditVo {
	// 主键
	private Long id;



	// 结算方式
	private String charge_type;

	// 单价
	private Double price;
	
	private Double	budget_day;
	
	private String	budget_type;


	
	// 日投放量
	private Integer status;



	
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
}
