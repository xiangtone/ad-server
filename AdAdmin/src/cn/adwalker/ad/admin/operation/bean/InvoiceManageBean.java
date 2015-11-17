package cn.adwalker.ad.admin.operation.bean;

import java.util.Date;



/**
 * 功能概述：<br>
 * 发票管理
 * 
 * @author nemo
 */
public class InvoiceManageBean {
	
	/** 主键 */
	private long id;

	/** 收支类型(1.收票，2.开票) */
	private Integer type;

	/** 发票类型(0.其他，1.专用，2.普通) */
	private Integer invoice_type;

	/** 开票日期 */
	private Date invoice_date;

	/** 购货单位名称 */
	private String company_income;

	/** 购货纳税人识别号 */
	private String taxpayer_number_income;

	/** 购货单位地址 */
	private String address_income;

	/** 购货单位电话 */
	private String phone_income;
	

	/** 购货单位开户行 */
	private String bank_subbranch_income;

	/** 购货单位银行账号 */
	private String bank_account_income;

	/** 销货单位名称 */
	private String company_outcome;

	/** 销货纳税人识别号 */
	private String taxpayer_number_outcome;

	/** 销货单位地址 */
	private String address_outcome;

	/** 销货单位电话 */
	private String phone_outcome;

	/** 销货单位开户行 */
	private String bank_subbranch_outcome;

	/** 销货单位银行账号 */
	private String bank_account_outcome;

	/** 发票金额 */
	private Double invoice_Money;

	/** 发票扫描件地址 */
	private String invoice_Url;

	public long getId() {
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

	public void setId(long id) {
		this.id = id;
	}

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public Double getInvoice_Money() {
		return invoice_Money;
	}

	public void setInvoice_Money(Double invoice_Money) {
		this.invoice_Money = invoice_Money;
	}

	public String getInvoice_Url() {
		return invoice_Url;
	}

	public void setInvoice_Url(String invoice_Url) {
		this.invoice_Url = invoice_Url;
	}

	public String getCompany_income() {
		return company_income;
	}

	public void setCompany_income(String company_income) {
		this.company_income = company_income;
	}

	public String getTaxpayer_number_income() {
		return taxpayer_number_income;
	}

	public void setTaxpayer_number_income(String taxpayer_number_income) {
		this.taxpayer_number_income = taxpayer_number_income;
	}

	public String getAddress_income() {
		return address_income;
	}

	public void setAddress_income(String address_income) {
		this.address_income = address_income;
	}

	public String getPhone_income() {
		return phone_income;
	}

	public void setPhone_income(String phone_income) {
		this.phone_income = phone_income;
	}

	public String getBank_subbranch_income() {
		return bank_subbranch_income;
	}

	public void setBank_subbranch_income(String bank_subbranch_income) {
		this.bank_subbranch_income = bank_subbranch_income;
	}

	public String getBank_account_income() {
		return bank_account_income;
	}

	public void setBank_account_income(String bank_account_income) {
		this.bank_account_income = bank_account_income;
	}

	public String getCompany_outcome() {
		return company_outcome;
	}

	public void setCompany_outcome(String company_outcome) {
		this.company_outcome = company_outcome;
	}

	public String getTaxpayer_number_outcome() {
		return taxpayer_number_outcome;
	}

	public void setTaxpayer_number_outcome(String taxpayer_number_outcome) {
		this.taxpayer_number_outcome = taxpayer_number_outcome;
	}

	public String getAddress_outcome() {
		return address_outcome;
	}

	public void setAddress_outcome(String address_outcome) {
		this.address_outcome = address_outcome;
	}

	public String getPhone_outcome() {
		return phone_outcome;
	}

	public void setPhone_outcome(String phone_outcome) {
		this.phone_outcome = phone_outcome;
	}

	public String getBank_subbranch_outcome() {
		return bank_subbranch_outcome;
	}

	public void setBank_subbranch_outcome(String bank_subbranch_outcome) {
		this.bank_subbranch_outcome = bank_subbranch_outcome;
	}

	public String getBank_account_outcome() {
		return bank_account_outcome;
	}

	public void setBank_account_outcome(String bank_account_outcome) {
		this.bank_account_outcome = bank_account_outcome;
	}
	
}
