package cn.adwalker.ad.admin.ad.form;


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
public class AdChannelEditForm {

	// 主键
	private Long id;
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
