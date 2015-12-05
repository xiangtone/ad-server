package cn.adwalker.ad.admin.finance.vo;
/**
 * 
* <p>Title: DevDevmonDetailSum</p>
* <p>Description:TODO </p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-17
 */
public class ChannelCostSumVo {
	private Double sum_cost;//总金额 统计
	private Integer sum_amount;//总金额 统计
	
	public ChannelCostSumVo() {
		super();
	}
	
	
	

	/**
	* <p>Title: </p>
	* <p>Description:TODO</p>
	* @param sum_cost
	* @param sum_amount
	*/
	public ChannelCostSumVo(Double sum_cost, Integer sum_amount) {
		super();
		this.sum_cost = sum_cost;
		this.sum_amount = sum_amount;
	}



	/**
	 * @return sum_cost
	 */
	public Double getSum_cost() {
		return sum_cost;
	}

	/**
	 * @param sum_cost the sum_cost to set
	 */
	
	public void setSum_cost(Double sum_cost) {
		this.sum_cost = sum_cost;
	}

	/**
	 * @return sum_amount
	 */
	public Integer getSum_amount() {
		return sum_amount;
	}

	/**
	 * @param sum_amount the sum_amount to set
	 */
	
	public void setSum_amount(Integer sum_amount) {
		this.sum_amount = sum_amount;
	}

	
 
	
}
