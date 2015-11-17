package cn.adwalker.model.operation.domain;
/**
* <p>Title: CampaignIncome</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-7-31
 */
public class CampaignIncomeCost {
	//主键
	private Long id;
	//预确认收入
	private Double focast_money;
	//确认支出
	private Double sys_cost;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getFocast_money() {
		return focast_money;
	}
	public void setFocast_money(Double focast_money) {
		this.focast_money = focast_money;
	}
	public Double getSys_cost() {
		return sys_cost;
	}
	public void setSys_cost(Double sys_cost) {
		this.sys_cost = sys_cost;
	}
	
	
}
