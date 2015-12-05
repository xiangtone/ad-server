package cn.adwalker.ad.admin.operation.vo;

import cn.adwalker.core.util.lang.ObjectUtils;

/**
 * 
* <p>Title: InvoiceManageVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-9-27
 */
public class InvoiceManageVo {
	/** 主键 */
	private Long id;
	/** 广告主id */
	private Long adv_id;
	/** 补齐*/
	private Long help_id ;
	/** 收支类型(1.收票，2.开票) */
	private Integer type;
	/** 发票类型(0.其他，1.专用，2.普通) */
	private Integer invoice_type;
	/** 0:媒体，1：广告主 */
	private Integer med_adv;
	
	private Integer status;
	/** 对方公司名称 */
	private String company_invoice;
	/** 对方公司发票名称 */
	private String company_outcome;
	/** 开票日期 */
	private String invoice_date;
	/** 发票编号 */
	private String number_outcome;
	/** 快递号 */
	private String express_outcome;
	/** 确认金额 */
	private Double income_money;
	/** 坏账金额 */
	private Double bad_money;
	/** 发票金额 */
	private Double invoice_money;
	/** 差额 */
	private Double balance;
	/** 备注 */
	private String remarks;
	/** 我方公司名称 */
	private String company_name;
	/** 开票人 */
	private String drawer;
	/** 媒体id */
	private Long media_id;
	/** 媒体名称*/
	private String media_name;
	/** url */
	private String invoice_url;
	/**回款备注 */
	private String circle_remarks;
	/**回款金额 */
	private Double circle_money;
	/**回款日期*/
	private String circle_time;
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
	public String getCompany_invoice() {
		return company_invoice;
	}
	public void setCompany_invoice(String company_invoice) {
		this.company_invoice = company_invoice;
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
	public Double getIncome_money() {
		return income_money;
	}
	public void setIncome_money(Double income_money) {
		this.income_money = income_money;
	}
	public Double getInvoice_money() {
		return invoice_money;
	}
	public void setInvoice_money(Double invoice_money) {
		this.invoice_money = invoice_money;
	}
	public String getremarks() {
		return remarks;
	}
	public void setremarks(String remarks) {
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
	public String getInvoice_url() {
		return invoice_url;
	}
	public void setInvoice_url(String invoice_url) {
		this.invoice_url = invoice_url;
	}
	public String getCircle_remarks() {
		return circle_remarks;
	}
	public void setCircle_remarks(String circle_remarks) {
		this.circle_remarks = circle_remarks;
	}
	public Double getCircle_money() {
		return circle_money;
	}
	public void setCircle_money(Double circle_money) {
		this.circle_money = circle_money;
	}
	public String getCircle_time() {
		return circle_time;
	}
	public void setCircle_time(String circle_time) {
		this.circle_time = circle_time;
	}
	public Long getMedia_id() {
		return media_id;
	}
	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}
	public String getMedia_name() {
		return media_name;
	}
	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}
	public Integer getMed_adv() {
		return med_adv;
	}
	public void setMed_adv(Integer med_adv) {
		this.med_adv = med_adv;
	}
	public Double getBad_money() {
		return bad_money;
	}
	public void setBad_money(Double bad_money) {
		this.bad_money = bad_money;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getBalance() {
		if(ObjectUtils.isNotEmpty(income_money)&&ObjectUtils.isNotEmpty(invoice_money)){
			balance=income_money-invoice_money;	
			
		}
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Long getHelp_id() {
		return help_id;
	}
	public void setHelp_id(Long help_id) {
		this.help_id = help_id;
	}	
	
}
