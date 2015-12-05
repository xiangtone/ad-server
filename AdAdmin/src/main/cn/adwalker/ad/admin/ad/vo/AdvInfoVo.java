/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.vo;

import java.util.Date;

import cn.adwalker.model.ad.domain.Advertiser;
import cn.adwalker.model.ad.domain.AdvBankInfo;

/**
 * 查看信息
 * <p>
 * Title: AdvInfoVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-1
 */
public class AdvInfoVo {

	public AdvInfoVo() {
		super();
	}
	/** 主键ID */
	private Long id;
	/** 电子邮箱 */
	private String email;

	/** 密码 */
	private String password;

	/** 帐号状态 0:初始化 1：封号 2、正常 */
	private Integer status;

	/** 公司名 */
	private String companyName;

	/** 公司地址 */
	private String companyAddress;

	/** 1个人 2 公司 */
	private Integer type;

	/** 邮编 */
	private String postCode;

	/** QQ */
	private String qq;

	/** 真实姓名 */
	private String realName;

	/** 移动电话 */
	private String mobilePhone;

	/** 固定电话 */
	private String fixedPhone;
	/** 创建时间 */
	private Date createTime;
	/** 信用额度 */
	private Double creditQuota;
	/** 用户类型 1、广告主 2、开发者 */
	private Integer userType;
	
	
	/** 区域类型(4:华南、1:华东、2:华北、0:平台) */
	private Integer area_type;
	/** 银行账号 */
	private String bank_account;
	/** 开户行 */
	private String bank_subbranch;
	/** （公司名称）财务 */
	private String account_hoder;
	/** 公司注册地址 */
	private String company_regist_address;
	/** 纳税人识别号 */
	private String taxpayer_number;
	/** 发票要求 */
	private Integer invoice_require;
	/** 发票要求(其他) */
	private String invoice_require_others;
	/** 营业执照 */
	private String business_license;
	/** 税务登记证tax_Registration Certificate */
	private String tax_reg_cer;
	/** 开户许可证 */
	private String account_permit;
	/** 一般纳税人资质证明 */
	private String taxpayer_certificate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getCreditQuota() {
		return creditQuota;
	}

	public void setCreditQuota(Double creditQuota) {
		this.creditQuota = creditQuota;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	/**
	 * @return area_type
	 */
	public Integer getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type the area_type to set
	 */
	
	public void setArea_type(Integer area_type) {
		this.area_type = area_type;
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
	
	public AdvInfoVo(Advertiser registadv,	AdvBankInfo advBankInfo) {
      //销售信息
		this.area_type=registadv.getArea_type();
	    //广告主财务信息
		this.account_hoder=advBankInfo.getAccount_hoder();
		this.bank_account=advBankInfo.getBank_account();
		this.company_regist_address=advBankInfo.getCompany_regist_address();
		this.bank_subbranch=advBankInfo.getBank_subbranch();
		this.tax_reg_cer=advBankInfo.getTax_reg_cer();
		this.taxpayer_certificate=advBankInfo.getTaxpayer_certificate();
		this.taxpayer_number=advBankInfo.getTaxpayer_number();
		this.invoice_require_others=advBankInfo.getInvoice_require_others();
		this.business_license=advBankInfo.getBusiness_license();
		this.account_permit=advBankInfo.getAccount_permit();
		this.invoice_require=advBankInfo.getInvoice_require();
		//广告主
		this.email=registadv.getEmail();
		this.fixedPhone=registadv.getFixed_phone();
		this.mobilePhone=registadv.getMobile_phone();
		this.qq=registadv.getQq();
		this.realName=registadv.getReal_name();
		this.creditQuota=registadv.getCredit_quota();
		this.companyName=registadv.getCompany_name();
		this.postCode=registadv.getPostcode();
        this.companyAddress=registadv.getCompany_address();
	}
}
