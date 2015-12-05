/**
 * <p>Title: CostChannelTmpVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-1
 * @version 1.0
 */
package cn.adwalker.ad.admin.operation.vo;

import java.io.Serializable;

/**
 * <p>
 * Title: CostChannelTmpVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-1
 */
public class CostChannelTmpSumVo implements Serializable {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -1071492599275880078L;
	private Integer amount;
	private Double cost;
	
	 
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
}
