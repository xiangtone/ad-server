package cn.adwalker.ad.admin.operation.vo;
/**
 * 
* <p>Title: AdvConsumeDetailVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-16
 */
public class AdvNumberIosSum {
	
	private int sum_amount;//广告主确认数统计
	private int sum_platform_amount;//确认数
	
	
	
	
	
	/**
	* <p>Title: </p>
	* <p>Description:TODO</p>
	*/
	public AdvNumberIosSum() {
		super();
	}


	/**
	* <p>Title: </p>
	* <p>Description:TODO</p>
	* @param sum_amount
	* @param sum_platform_amount
	*/
	public AdvNumberIosSum(int sum_amount, int sum_platform_amount) {
		super();
		this.sum_amount = sum_amount;
		this.sum_platform_amount = sum_platform_amount;
	}


	public int getSum_platform_amount() {
		return sum_platform_amount;
	}


	public void setSum_platform_amount(int sum_platform_amount) {
		this.sum_platform_amount = sum_platform_amount;
	}
	public int getSum_amount() {
		return sum_amount;
	}


	public void setSum_amount(int sum_amount) {
		this.sum_amount = sum_amount;
	}

	
	
	
}
