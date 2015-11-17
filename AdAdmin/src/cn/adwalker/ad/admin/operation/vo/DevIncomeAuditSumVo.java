package cn.adwalker.ad.admin.operation.vo;
/**
 * 
* <p>Title: DevDevmonDetailSum</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-17
 */
public class DevIncomeAuditSumVo {
	private Double sum_sumMoney;//总金额 统计
	
	public DevIncomeAuditSumVo() {
		super();
	}

	
	/**
	 * 
	* <p>Title: </p>
	* <p>Description:TODO</p>
	* @param sum_valid_Amount
	* @param sum_sumMoney
	 */
	public DevIncomeAuditSumVo(Double sum_valid_Amount, Double sum_sumMoney) {
		super();
		this.sum_sumMoney = sum_sumMoney;
	}	
	public Double getSum_sumMoney() {
		return sum_sumMoney;
	}
	public void setSum_sumMoney(Double sum_sumMoney) {
		this.sum_sumMoney = sum_sumMoney;
	}
		
	
}
