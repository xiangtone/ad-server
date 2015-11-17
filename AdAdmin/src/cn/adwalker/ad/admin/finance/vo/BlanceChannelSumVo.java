package cn.adwalker.ad.admin.finance.vo;

import java.io.Serializable;

/**
 * <p>
 * Title: CostChannelTempVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-31
 */
public class BlanceChannelSumVo implements Serializable {

	/** @Fields serialVersionUID : */
	private static final long serialVersionUID = 4475803794461940939L;

	private Double cost;
	private Integer amount;

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
