package cn.adwalker.ad.admin.report.vo;
/**
 * 
* <p>Title: AdvConsumeDetailVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-16
 */
public class SumSalesAchievementVo {
	
	private Integer sum_platform_amount;//广告主确认数统计
	private Double sum_income_money;//总金额 统计
	
	
	public SumSalesAchievementVo(Integer sum_platform_amount, Double sum_income_money) {
		super();
		this.sum_platform_amount = sum_platform_amount;
		this.sum_income_money = sum_income_money;
	}

	public Integer getSum_platform_amount() {
		return sum_platform_amount;
	}





	public void setSum_platform_amount(Integer sum_platform_amount) {
		this.sum_platform_amount = sum_platform_amount;
	}

	public Double getSum_income_money() {
		return sum_income_money;
	}


	public void setSum_income_money(Double sum_income_money) {
		this.sum_income_money = sum_income_money;
	}


	
	
	
	
}
