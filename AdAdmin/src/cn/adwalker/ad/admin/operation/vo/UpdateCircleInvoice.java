package cn.adwalker.ad.admin.operation.vo;


/**
* <p>Title: InvoiceAdd</p>
* <p>Description:发票管理form</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-9-13
 */
public class UpdateCircleInvoice {
	
	/** 主键 */
	private long id;
	/** 状态 */
	private Integer status;
	/**回款备注 */
	private String circle_remarks;
	/**回款金额 */
	private Double circle_money;
	/*坏账金额 */
	private Double bad_money;
	/**回款日期*/
	private String circle_time;
	/**提交人*/
	private long circle_id; 
	
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCircle_id() {
		return circle_id;
	}
	public void setCircle_id(long circle_id) {
		this.circle_id = circle_id;
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
	
	
}
