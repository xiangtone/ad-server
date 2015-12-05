package cn.adwalker.ad.admin.finance.bean;
/**
* <p>Title: DevApplyMoneylbean</p>
* <p>Description:网站主提款财务bean</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-7
 */
public class ChaApplyMoneylbean {

	private String keyword;
	private String value ;
	private String operatorMan;//操作人
	private String operatorBegin;//操作开始时间
	private String operatorEnd;//操作结束时间
	private String begin;//申请开始时间
	private String end;//申请 结束时间
	private String ope_begin;//运营审核日期
	private String ope_end;//运营审核日期
	private Integer status;//支付状态
	private Integer payType;//支付类型
	private Integer invoice;//发票状态
	
	
	
	
	
	public String getOperatorMan() {
		return operatorMan;
	}





	public void setOperatorMan(String operatorMan) {
		this.operatorMan = operatorMan;
	}

	public String getOperatorBegin() {
		return operatorBegin;
	}


	public void setOperatorBegin(String operatorBegin) {
		this.operatorBegin = operatorBegin;
	}

	public String getOperatorEnd() {
		return operatorEnd;
	}

	public void setOperatorEnd(String operatorEnd) {
		this.operatorEnd = operatorEnd;
	}

	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getStatus() {
		return status;
	}





	public String getKeyword() {
		return keyword;
	}





	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}





	public String getValue() {
		return value;
	}





	public void setValue(String value) {
		this.value = value;
	}





	public void setStatus(Integer status) {
		this.status = status;
	}





	public Integer getPayType() {
		return payType;
	}





	public void setPayType(Integer payType) {
		this.payType = payType;
	}





	public Integer getInvoice() {
		return invoice;
	}





	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}





	public ChaApplyMoneylbean() {
		super();
		// TODO Auto-generated constructor stub
	}





	public String getOpe_begin() {
		return ope_begin;
	}





	public void setOpe_begin(String ope_begin) {
		this.ope_begin = ope_begin;
	}





	public String getOpe_end() {
		return ope_end;
	}





	public void setOpe_end(String ope_end) {
		this.ope_end = ope_end;
	}
	
	

}
