/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.channel.vo;

import cn.adwalker.model.channel.domain.Channel;
import cn.adwalker.ad.admin.channel.form.ChannelBankInfo;

/**
* <p>Title: RegistChannelVo</p>
* <p>Description:渠道vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-11
 */
public class ChannelAndBankinfoVo {
	public ChannelAndBankinfoVo() {
		super();
	}
	/** 主键ID */
	private Long id;
	/** 用户名（电子邮箱） */
	private String email;
	/** 渠道名 */
	private String channel_name;
	/** QQ */
	private String qq;
	/** 渠道联系人 */
	private String real_name;
	/** 移动电话 */
	private String phone;
	/** sdk渠道标示 */
	private String marking;
	/** 渠道负责人 */
	private String channe_manager;
	/** 合作方式 */
	private Integer channe_mode;
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
	/** 渠道评级*/
	private Double scale;
	
	public String getMarking() {
		return marking;
	}
	public void setMarking(String marking) {
		this.marking = marking;
	}
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
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getChanne_manager() {
		return channe_manager;
	}
	public void setChanne_manager(String channe_manager) {
		this.channe_manager = channe_manager;
	}
	public Integer getChanne_mode() {
		return channe_mode;
	}
	public void setChanne_mode(Integer channe_mode) {
		this.channe_mode = channe_mode;
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
	
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public String getlevel() {
		String str = null;
		Integer i = getScale_int();
		if (i != null) {
			if (i > 130 && i <= 150) {
				str = "A";
			} else if (i > 100 && i <= 130) {
				str = "B";
			} else if (i > 70 && i <= 100) {
				str = "C";
			} else if (i > 40 && i <= 70) {
				str = "D";
			} else if (i >= 0 && i <= 40) {
				str = "E";
			}

		}
		return str;
	}

	public Integer getScale_int() {
		Double d = null;
		if (scale != null) {
			d = (Double) scale * 100;
		}
		return d != null ? d.intValue() : null;

	}
	/**
	* <p>Title: </p>
	* <p>Description:TODO</p>
	* @param chanInfo
	 */
	public ChannelAndBankinfoVo(Channel chanInfo,ChannelBankInfo chaBankInfo) {
		//渠道基础信息
		this.channel_name=chanInfo.getName();
		this.email =chanInfo.getEmail();
		this.channe_mode =chanInfo.getChanne_mode();
		this.phone =chanInfo.getPhone(); 
		this.qq=chanInfo.getQq();
		this.real_name =chanInfo.getReal_name();
		this.channe_manager=chanInfo.getChanne_manager();
		this.marking=chanInfo.getMarking();
		this.scale=chanInfo.getScale();
		//财务信息
		this.bank_account=chaBankInfo.getBank_account();
		this.bank_subbranch=chaBankInfo.getBank_subbranch();
		this.account_hoder=chaBankInfo.getAccount_hoder();
		this.invoice_require=chaBankInfo.getInvoice_require();
		this.invoice_require_others=chaBankInfo.getInvoice_require_others();
	}
}
