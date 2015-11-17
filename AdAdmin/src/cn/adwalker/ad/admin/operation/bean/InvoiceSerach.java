package cn.adwalker.ad.admin.operation.bean;


/**
* <p>Title: InvoiceSerach</p>
* <p>Description:发票搜索bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-9-27
 */
public class InvoiceSerach {
	
	/** 收支类型(1.收票，2.开票) */
	private Integer income_type;
	
	private Integer help_inv;
	
	private Integer status;
	/** 发票类型(0.其他，1.专用，2.普通) */
	private Integer invoice_type;
	/** 发票抬头 */
	private String invoice_date;
	/** 对方公司名称 */
	private String company_outcome;
	/** 媒体公司名称 */
	private String media_name;
	/** 广告主公司名称 */
	private String company_name;

	

	public Integer getIncome_type() {
		return income_type;
	}

	public void setIncome_type(Integer income_type) {
		this.income_type = income_type;
	}

	public Integer getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(Integer invoice_type) {
		this.invoice_type = invoice_type;
	}
	
	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getCompany_outcome() {
		return company_outcome;
	}

	public void setCompany_outcome(String company_outcome) {
		this.company_outcome = company_outcome;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getHelp_inv() {
		return help_inv;
	}

	public void setHelp_inv(Integer help_inv) {
		this.help_inv = help_inv;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

}
