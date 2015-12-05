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
public class CostChannelTmpVo implements Serializable {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -1071492599275880078L;
	private Long channel_id;
	private Long type_id;
	private Integer adv_confrim_num;
	private Integer amount;
	private Double cost;
	
	
	public Long getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}
	public Long getType_id() {
		return type_id;
	}
	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}
	public Integer getAdv_confrim_num() {
		return adv_confrim_num;
	}
	public void setAdv_confrim_num(Integer adv_confrim_num) {
		this.adv_confrim_num = adv_confrim_num;
	}
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
