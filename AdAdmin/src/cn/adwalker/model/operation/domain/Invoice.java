package cn.adwalker.model.operation.domain;


/**
* <p>Title: InvoiceAdd</p>
* <p>Description:发票管理domin</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-9-13
 */
public class Invoice {

	private Long id;//主键
	private Long adv_id;//广告主id
	private Double invoice_money;//发票金额
	private Double income_money;//确认金额
	private String company_name;//我放公司
	private Integer med_adv;//我放公司
	private Integer status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}
	public Double getInvoice_money() {
		return invoice_money;
	}
	public void setInvoice_money(Double invoice_money) {
		this.invoice_money = invoice_money;
	}
	public Double getIncome_money() {
		return income_money;
	}
	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Integer getMed_adv() {
		return med_adv;
	}
	public void setMed_adv(Integer med_adv) {
		this.med_adv = med_adv;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
