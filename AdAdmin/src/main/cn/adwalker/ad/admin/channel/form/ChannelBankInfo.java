package cn.adwalker.ad.admin.channel.form;

import java.util.Date;

/**
* <p>Title: ChannelBankInfo</p>
* <p>Description:渠道财务</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-16
 */
public class ChannelBankInfo {
	private Long  user_id;
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
	/** 修改时间*/
	private Date update_time;

	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
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
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}	
	
}
