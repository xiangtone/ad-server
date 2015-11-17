package cn.adwalker.ad.web.channel.vo;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.ad.util.ObjectUtils;

public class ChannelReportSumVo {
	
	private Integer sum_amount;//确认数统计
	private Double sum_money;//总金额 统计
	private Double sum_money_sdk;//总金额 统计
	
	public ChannelReportSumVo(Integer sum_amount, Double sum_money,Double sum_money_sdk) {
		super();
		this.sum_amount = sum_amount;
		this.sum_money = sum_money;
		this.sum_money_sdk = sum_money_sdk;
	}

	public Integer getSum_amount() {
		return sum_amount;
	}


	public void setSum_amount(Integer sum_amount) {
		this.sum_amount = sum_amount;
	}

	public Double getSum_money() {
		
		return sum_money;
	}

	public void setSum_money(Double sum_money) {
		this.sum_money = sum_money;
	}

	public Double getSum_money_sdk() {
		if(ObjectUtils.isNotEmpty(sum_money_sdk)){
			sum_money_sdk=sum_money_sdk*AppConstant.CHANNEL_SDK_SCALE;
		}
		return sum_money_sdk;
	}

	public void setSum_money_sdk(Double sum_money_sdk) {
		this.sum_money_sdk = sum_money_sdk;
	}

}
