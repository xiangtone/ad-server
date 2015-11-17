package cn.adwalker.model.operation.domain;


/**
* <p>Title: CampaignId</p>
* <p>Description:更新广告主月底对账每天预确认数表</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-9-27
 */
public class CampaignId {
	//主键
	private Long id;
	//是否对账状态
	private Integer invoice_status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getInvoice_status() {
		return invoice_status;
	}
	public void setInvoice_status(Integer invoice_status) {
		this.invoice_status = invoice_status;
	}	
	
}
