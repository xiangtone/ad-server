/**
 * 
 */
package cn.adwalker.model.app.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * 功能描述：<br>
 * 开发者提现记录实体
 * 
 * @author guoyongxiang
 */
/**
 * @author Administrator
 *
 */
public class ChaApplyMoney implements Entity  {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -2956082671445200968L;

	/** ID */
	private Long id;

	/** 渠道ID */
	private Long channelId;
	/** 渠道ID */
	private Long cha_Id;

	/** 渠道Email */
	private String chaEmail;

	/** 银行信息 */
	private Long bankId;
	
	/**银行名称*/
	private String bank_name;
	
	/**银行账户 */
	private String bank_subbranch;

	/**银行账户 */
	private String bank_account;

	/** 申请提款的金额 */
	private Double applyMoney;

	/** 审核状态（0:未审批 1:审批通过 -1:审批失败 2:财务审批成功 -2：财务审批失败） */
	private String status;

	/** 运营审批人 */
	private Long managerId;

	/** 运营确认金额 */
	private Double managerMoney;

	/** 运营审批时间或者开发者撤销申请时间 */
	private Date managerTime;

	/** 运营说明 */
	private String managerDesc;

	/** 财务审批人 */
	private Long financeId;

	/** 财务确认金额 */
	private Double financeMoney;

	/** 财务审批时间 */
	private Date financeTime;

	/** 财务说明 */
	private String financeDesc;

	/** 账户原来的钱 */
	private Double formerMoney;

	/** 创建时间 */
	private Date createTime;

	/** 联系人 */
	private String realName;
	
	/** 开户人 */
	private String kaihu_name;
	
	/** 财务审批操作人 */
	private String operator;
	/** 财务审批操作人 */
	private String finance_name;
	/**扣税*/
	private Double finance_tax;
	
	/**手续费*/
	private Double finance_dues;
	
	/**支付类型 0银行转账（默认） 1现金 2支票*/
	private String pay_type;
	
	/**余额*/
	private Double balance;
	
	private Double balance_money;
	
	/**财务实际支付金额*/
	private Double finance_money;
	
	/**开发者帐号余额*/
	private Double cha_balance;
	
	/**城市*/
	private String city;
	
	/**开户城市*/
	private String bank_city;
	
	
	
	private Integer flow_status; // 流量状态( 1:疑似 2:作弊 0:其他)
	
	private Integer tax_status; // 
	private Integer invoice; // 
	private String invoice_name;
	
	
	
	
	public Double getBalance_money() {
		return balance_money;
	}

	public void setBalance_money(Double balance_money) {
		this.balance_money = balance_money;
	}

	public String getFinance_name() {
		return finance_name;
	}

	public void setFinance_name(String finance_name) {
		this.finance_name = finance_name;
	}

	public String getBank_city() {
		return bank_city;
	}

	public void setBank_city(String bank_city) {
		this.bank_city = bank_city;
	}

	public Integer getTax_status() {
		return tax_status;
	}

	public void setTax_status(Integer tax_status) {
		this.tax_status = tax_status;
	}
	public Integer getFlow_status() {
		return flow_status;
	}

	public void setFlow_status(Integer flow_status) {
		this.flow_status = flow_status;
	}

	public Double getFinance_tax() {
		return finance_tax;
	}

	public void setFinance_tax(Double finance_tax) {
		this.finance_tax = finance_tax;
	}

	public Double getFinance_dues() {
		return finance_dues;
	}

	public void setFinance_dues(Double finance_dues) {
		this.finance_dues = finance_dues;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getFinance_money() {
		return finance_money;
	}

	public void setFinance_money(Double finance_money) {
		this.finance_money = finance_money;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	public String getKaihu_name() {
		return kaihu_name;
	}

	public void setKaihu_name(String kaihu_name) {
		this.kaihu_name = kaihu_name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	

	/**
	 * @return Returns the bankId.
	 */
	public Long getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 *            The bankId to set.
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return Returns the applyMoney.
	 */
	public Double getApplyMoney() {
		return applyMoney;
	}

	/**
	 * @param applyMoney
	 *            The applyMoney to set.
	 */
	public void setApplyMoney(Double applyMoney) {
		this.applyMoney = applyMoney;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return Returns the managerMoney.
	 */
	public Double getManagerMoney() {
		return managerMoney;
	}

	/**
	 * @param managerMoney
	 *            The managerMoney to set.
	 */
	public void setManagerMoney(Double managerMoney) {
		this.managerMoney = managerMoney;
	}

	/**
	 * @return Returns the managerTime.
	 */
	public Date getManagerTime() {
		return managerTime;
	}

	/**
	 * @param managerTime
	 *            The managerTime to set.
	 */
	public void setManagerTime(Date managerTime) {
		this.managerTime = managerTime;
	}

	/**
	 * @return Returns the managerDesc.
	 */
	public String getManagerDesc() {
		return managerDesc;
	}

	/**
	 * @param managerDesc
	 *            The managerDesc to set.
	 */
	public void setManagerDesc(String managerDesc) {
		this.managerDesc = managerDesc;
	}

	/**
	 * @return Returns the financeMoney.
	 */
	public Double getFinanceMoney() {
		return financeMoney;
	}

	/**
	 * @param financeMoney
	 *            The financeMoney to set.
	 */
	public void setFinanceMoney(Double financeMoney) {
		this.financeMoney = financeMoney;
	}

	/**
	 * @return Returns the financeTime.
	 */
	public Date getFinanceTime() {
		return financeTime;
	}

	/**
	 * @param financeTime
	 *            The financeTime to set.
	 */
	public void setFinanceTime(Date financeTime) {
		this.financeTime = financeTime;
	}

	/**
	 * @return Returns the financeDesc.
	 */
	public String getFinanceDesc() {
		return financeDesc;
	}

	/**
	 * @param financeDesc
	 *            The financeDesc to set.
	 */
	public void setFinanceDesc(String financeDesc) {
		this.financeDesc = financeDesc;
	}

	/**
	 * @return Returns the formerMoney.
	 */
	public Double getFormerMoney() {
		return formerMoney;
	}

	/**
	 * @param formerMoney
	 *            The formerMoney to set.
	 */
	public void setFormerMoney(Double formerMoney) {
		this.formerMoney = formerMoney;
	}

	/**
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	

	/**
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the financeId
	 */
	public Long getFinanceId() {
		return financeId;
	}

	/**
	 * @param financeId the financeId to set
	 */
	public void setFinanceId(Long financeId) {
		this.financeId = financeId;
	}
	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getCha_Id() {
		return cha_Id;
	}

	public void setCha_Id(Long cha_Id) {
		this.cha_Id = cha_Id;
	}

	public String getChaEmail() {
		return chaEmail;
	}

	public void setChaEmail(String chaEmail) {
		this.chaEmail = chaEmail;
	}

	public Double getCha_balance() {
		return cha_balance;
	}

	public void setCha_balance(Double cha_balance) {
		this.cha_balance = cha_balance;
	}

	public Integer getInvoice() {
		return invoice;
	}

	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}

	public String getInvoice_name() {
		return invoice_name;
	}

	public void setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
	}


}
