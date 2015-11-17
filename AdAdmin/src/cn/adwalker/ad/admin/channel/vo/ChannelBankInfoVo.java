/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.channel.vo;

import java.util.Date;

/**
* <p>Title: ChannelBankInfoVo</p>
* <p>Description:渠道财务vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-11
 */
public class ChannelBankInfoVo {
	/** 主键ID */
	private Long id;
	/** 渠道id */
	private Long channel_id;
	/** 用户类型 1、广告主 2、开发者 */
	private Integer userType;
	/** 银行账号*/
	private String bank_account;
	/** 开户行*/
	private String bank_subbranch;
	/** （公司名称）财务*/
	private String account_hoder;
	/** 发票要求*/
	private Integer invoice_require;
	/** 发票要求(其他)*/
	private String invoice_require_others;
	/** 创建时间 */
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
