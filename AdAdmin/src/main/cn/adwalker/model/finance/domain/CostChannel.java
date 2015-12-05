/**
 * <p>Title: CostChannel.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-1
 * @version 1.0
 */
package cn.adwalker.model.finance.domain;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: CostChannel
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
public class CostChannel implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 6052102983600863890L;
	private Long id;
	private Long channel_id;
	private Long type_id;
	private Integer adv_confrim_num;
	private Integer amount;
	private Double cost;
	private Long campaign_id;
	
	

	private Long balance_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

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

	public Long getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Long balance_id) {
		this.balance_id = balance_id;
	}
}
