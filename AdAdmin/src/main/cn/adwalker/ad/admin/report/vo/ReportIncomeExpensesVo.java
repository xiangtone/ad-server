package cn.adwalker.ad.admin.report.vo;
/**
* <p>Title: ReportIncomeExpensesVo</p>
* <p>Description:收入/支出Vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-20
 */
public class ReportIncomeExpensesVo {
	/** 主键ID */
	private Long id;
	/** 时间 */
	private String static_date;
	/**确认收入*/
	private  double income_money;
	/**成本*/
	private  double cost;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatic_date() {
		return static_date;
	}
	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}
	public double getIncome_money() {
		return income_money;
	}
	public void setIncome_money(double income_money) {
		this.income_money = income_money;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	
}
