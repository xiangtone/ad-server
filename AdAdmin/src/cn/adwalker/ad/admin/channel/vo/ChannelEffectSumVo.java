package cn.adwalker.ad.admin.channel.vo;
/**
 * 
* <p>Title: AdvConsumeDetailVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-1-16
 */
public class ChannelEffectSumVo {
	
	private int sum_platform_amount;//确认数
	
	
	public ChannelEffectSumVo(int sum_amount,int sum_platform_amount) {
		super();
		this.sum_platform_amount = sum_platform_amount;
	}


	public int getSum_platform_amount() {
		return sum_platform_amount;
	}


	public void setSum_platform_amount(int sum_platform_amount) {
		this.sum_platform_amount = sum_platform_amount;
	}
}
