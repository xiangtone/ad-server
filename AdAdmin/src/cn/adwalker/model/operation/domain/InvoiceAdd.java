package cn.adwalker.model.operation.domain;

import java.util.Date;


/**
* <p>Title: InvoiceAdd</p>
* <p>Description:发票管理</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-9-13
 */
public class InvoiceAdd {

	/** 主键 */
	private Long id;
	/** 收支类型(1.收票，2.开票) */
	private Integer type;
	
	private Integer status;
	/** 发票类型(0.其他，1.专用，2.普通) */
	private Integer invoice_type;
	/** 0：媒体，1：广告主 */
	private Integer med_adv;
	/** 对方公司发票名称 */
	private String company_outcome;
	/** 开票日期 */
	private String invoice_date;
	/** 发票编号 */
	private String number_outcome;
	/** 快递号 */
	private String express_outcome;
	/** 发票金额 */
	private Double invoice_money;
	/** 发票金额 */
	private Double income_money;
	/** 备注 */
	private String remarks;
	/** 我方公司名称 */
	private String company_name;
	/** 开票人 */
	private String drawer;
	
	private Date create_time;
	/** 媒体id */
	private Long media_id;
	
	private Long manager_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(Integer invoice_type) {
		this.invoice_type = invoice_type;
	}

	public Integer getMed_adv() {
		return med_adv;
	}

	public void setMed_adv(Integer med_adv) {
		this.med_adv = med_adv;
	}

	public String getCompany_outcome() {
		return company_outcome;
	}

	public void setCompany_outcome(String company_outcome) {
		this.company_outcome = company_outcome;
	}

	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getNumber_outcome() {
		return number_outcome;
	}

	public void setNumber_outcome(String number_outcome) {
		this.number_outcome = number_outcome;
	}

	public String getExpress_outcome() {
		return express_outcome;
	}

	public void setExpress_outcome(String express_outcome) {
		this.express_outcome = express_outcome;
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

	/**
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
