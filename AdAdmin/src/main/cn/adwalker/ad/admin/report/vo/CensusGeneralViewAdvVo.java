package cn.adwalker.ad.admin.report.vo;


/**
* <p>Title: CensusGeneralViewAdvVo</p>
* <p>Description:统计概览adv</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-18
 */
public class CensusGeneralViewAdvVo {
	/** 主键ID */
	private Long id;
	/** 广告主 */
	private String company_name;
	/** 广告主确认数 */
	private  Integer income_amount;
	/** 单价 */
	private double price;
	/** 金额 */
	private double income_money;
	/** 收入sum */
	private double sum_cost;
	/** 占比 */
	private double proportion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public Integer getIncome_amount() {
		return income_amount;
	}
	public void setIncome_amount(Integer income_amount) {
		this.income_amount = income_amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getIncome_money() {
		return income_money;
	}
	public void setIncome_money(double income_money) {
		this.income_money = income_money;
	}
	public double getSum_cost() {
		return sum_cost;
	}
	public void setSum_cost(double sum_cost) {
		this.sum_cost = sum_cost;
	}
	public double getProportion() {
		if(sum_cost!=0){
			proportion = (income_money / sum_cost)*100;
		}
		return proportion;
	}
	public void setProportion(double proportion) {
		this.proportion = proportion;
	}
	
}
