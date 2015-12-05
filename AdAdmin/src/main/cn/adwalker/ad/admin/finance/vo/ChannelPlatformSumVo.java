package cn.adwalker.ad.admin.finance.vo;
/**
* <p>Title: CampaignConfirmSumVo</p>
* <p>Description:求和Vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-10-11
 */
public class ChannelPlatformSumVo {
	private Double sum_pha_money;//平台总金额 
	private Double sum_cha_money;//渠道总金额 
	
	public ChannelPlatformSumVo() {
		super();
	}

	public ChannelPlatformSumVo(Double sum_pha_money,Double sum_cha_money) {
		super();
		this.sum_pha_money = sum_pha_money;
		this.sum_cha_money = sum_cha_money;
	}

	public Double getSum_pha_money() {
		return sum_pha_money;
	}

	public void setSum_pha_money(Double sum_pha_money) {
		this.sum_pha_money = sum_pha_money;
	}

	public Double getSum_cha_money() {
		return sum_cha_money;
	}

	public void setSum_cha_money(Double sum_cha_money) {
		this.sum_cha_money = sum_cha_money;
	}
}
