package cn.adwalker.ad.admin.finance.vo;
/**
* <p>Title: FinanceIncomeSumVo</p>
* <p>Description:网站主</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-7
 */
public class FinanceIncomeSumVo {
	private Double sum_sumMoney;//总金额 统计
	
	public FinanceIncomeSumVo() {
		super();
	}

	public FinanceIncomeSumVo(Double sum_sumMoney) {
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
