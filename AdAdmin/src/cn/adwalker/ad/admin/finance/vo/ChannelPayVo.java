package cn.adwalker.ad.admin.finance.vo;

import java.util.Date;

/**
 * 
* <p>Title: ChannelPayVo</p>
* <p>Description:TODO 渠道付款Vo</p>
* <p>Company: adwalker</p> 
* @author liwewei
* @date   2012-11-20
 */
public class ChannelPayVo {
	/** ID */
	private Long id;
	/** 开发者ID */
	private Long developer_id;
	/** 开发者Email */
	private String dev_email;
	/** 开户人 */
	private String kaihu_name;
	/** 银行名称 */
	private String bank_name;
	/** 开户行名称 */
	private String bank_subbranch;
	/** 银行账户 */
	private String bank_account;
	/** 支付类型 0银行转账（默认） 1现金 2支票 */
	private String pay_type;
	/** 运营审批人 */
	private Long manager_id;
	/** 运营确认金额 */
	private Double manager_money;
	/** 运营审批时间 */
	private Date manager_time;
	/** 申请提款的金额 */
	private Double apply_money;
	/** 审核状态（0:待审核1:通过 -1:不通过 2:付款成功-2：付款失败） */
	private int status;
	/** 运营说明 */
	private String manager_desc;
	/** 财务审批人 */
	private Long finance_id;
	/** 财务确认金额 */
	private Double finance_money;
	/** 余额 */
	private Double balance_money;
	/** 扣税 */
	private Double finance_tax;
	/** 手续费 */
	private Double finance_dues;
	/** 财务审批时间 */
	private Date finance_time;
	/** 创建时间 */
	private Date create_time;
	/** 城市 */
	private String city;
	/** 扣税状态(1:正常 2:免税) */
	private int tax_status;

	/** 证件类型(1:身份证 2:公司号) */
	private int card_type;
	/** 证件号 */
	private String card_code;
	/** 合同号 */
	private String contract_code;
	/** 付款说明 */
	private String pay_desc;
	/** 申请人 */
	private String proposer;
	/** 申请时间 */
	private String propose_date;

	private String stat_date_begin; // 结算日期-开始

	private String stat_date_end; // 结算日期-结束

	public String getPropose_date() {
		return propose_date;
	}

	public void setPropose_date(String propose_date) {
		this.propose_date = propose_date;
	}

	public String getStat_date_begin() {
		return stat_date_begin;
	}

	public void setStat_date_begin(String stat_date_begin) {
		this.stat_date_begin = stat_date_begin;
	}

	public String getStat_date_end() {
		return stat_date_end;
	}

	public void setStat_date_end(String stat_date_end) {
		this.stat_date_end = stat_date_end;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public int getCard_type() {
		return card_type;
	}

	public void setCard_type(int card_type) {
		this.card_type = card_type;
	}

	public String getCard_code() {
		return card_code;
	}

	public void setCard_code(String card_code) {
		this.card_code = card_code;
	}

	public String getContract_code() {
		return contract_code;
	}

	public void setContract_code(String contract_code) {
		this.contract_code = contract_code;
	}

	public String getPay_desc() {
		return pay_desc;
	}

	public void setPay_desc(String pay_desc) {
		this.pay_desc = pay_desc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeveloper_id() {
		return developer_id;
	}

	public void setDeveloper_id(Long developer_id) {
		this.developer_id = developer_id;
	}

	public String getDev_email() {
		return dev_email;
	}

	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}

	public String getKaihu_name() {
		return kaihu_name;
	}

	public void setKaihu_name(String kaihu_name) {
		this.kaihu_name = kaihu_name;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_subbranch() {
		return bank_subbranch;
	}

	public void setBank_subbranch(String bank_subbranch) {
		this.bank_subbranch = bank_subbranch;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public Double getManager_money() {
		return manager_money;
	}

	public void setManager_money(Double manager_money) {
		this.manager_money = manager_money;
	}

	public Date getManager_time() {
		return manager_time;
	}

	public void setManager_time(Date manager_time) {
		this.manager_time = manager_time;
	}

	public Double getApply_money() {
		return apply_money;
	}

	public void setApply_money(Double apply_money) {
		this.apply_money = apply_money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getManager_desc() {
		return manager_desc;
	}

	public void setManager_desc(String manager_desc) {
		this.manager_desc = manager_desc;
	}

	public Long getFinance_id() {
		return finance_id;
	}

	public void setFinance_id(Long finance_id) {
		this.finance_id = finance_id;
	}

	public Double getFinance_money() {
		return finance_money;
	}

	public void setFinance_money(Double finance_money) {
		this.finance_money = finance_money;
	}

	public Double getBalance_money() {
		return balance_money;
	}

	public void setBalance_money(Double balance_money) {
		this.balance_money = balance_money;
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

	public Date getFinance_time() {
		return finance_time;
	}

	public void setFinance_time(Date finance_time) {
		this.finance_time = finance_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTax_status() {
		return tax_status;
	}

	public void setTax_status(int tax_status) {
		this.tax_status = tax_status;
	}
}
