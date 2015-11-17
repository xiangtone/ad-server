/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.ad.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 广告主财务vo
* <p>Title: AdvBankInfoVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-3-29
 */
public class AdvBankInfo implements Entity {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -6430873730913079474L;
	/** 主键ID */
	private Long id;
	/** 广告主id */
	private Long adv_id;
	/** 用户类型 1、广告主 2、开发者 */
	private Integer userType;
	/** 银行账号*/
	private String bank_account;
	/** 开户行*/
	private String bank_subbranch;
	/** （公司名称）财务*/
	private String account_hoder;
	/** 公司注册地址*/
	private String company_regist_address;
	/** 纳税人识别号*/
	private String taxpayer_number;
	/** 发票要求*/
	private Integer invoice_require;
	/** 发票要求(其他)*/
	private String invoice_require_others;
	/** 营业执照*/
	private String business_license;
	/** 税务登记证tax_Registration Certificate */
	private String tax_reg_cer;
	/** 开户许可证*/
	private String account_permit;
	/** 一般纳税人资质证明*/
	private String taxpayer_certificate;	
	/** 创建时间 */
	private Date createTime;
	
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
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getBank_subbranch() {
		return bank_subbranch;
	}
	public void setBank_subbranch(String bank_subbranch) {
		this.bank_subbranch = bank_subbranch;
	}
	public String getAccount_hoder() {
		return account_hoder;
	}
	public void setAccount_hoder(String account_hoder) {
		this.account_hoder = account_hoder;
	}
	
	public String getCompany_regist_address() {
		return company_regist_address;
	}
	public void setCompany_regist_address(String company_regist_address) {
		this.company_regist_address = company_regist_address;
	}
	public String getTaxpayer_number() {
		return taxpayer_number;
	}
	public void setTaxpayer_number(String taxpayer_number) {
		this.taxpayer_number = taxpayer_number;
	}
	public Integer getInvoice_require() {
		return invoice_require;
	}
	public void setInvoice_require(Integer invoice_require) {
		this.invoice_require = invoice_require;
	}
	public String getInvoice_require_others() {
		return invoice_require_others;
	}
	public void setInvoice_require_others(String invoice_require_others) {
		this.invoice_require_others = invoice_require_others;
	}
	public String getBusiness_license() {
		return business_license;
	}
	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}
	public String getTax_reg_cer() {
		return tax_reg_cer;
	}
	public void setTax_reg_cer(String tax_reg_cer) {
		this.tax_reg_cer = tax_reg_cer;
	}
	public String getAccount_permit() {
		return account_permit;
	}
	public void setAccount_permit(String account_permit) {
		this.account_permit = account_permit;
	}
	public String getTaxpayer_certificate() {
		return taxpayer_certificate;
	}
	public void setTaxpayer_certificate(String taxpayer_certificate) {
		this.taxpayer_certificate = taxpayer_certificate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
