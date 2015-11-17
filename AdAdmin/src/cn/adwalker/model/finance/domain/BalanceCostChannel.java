/**
 * <p>Title: CostChannelTempVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-10-31
 * @version 1.0
 */
package cn.adwalker.model.finance.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

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
public class BalanceCostChannel implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 4475803794461940939L;
	private Long id;
	private Long campaign_id;
	private Date create_time;
	private Integer status;
	private String static_date;
	private Double price;
	private Double cost;
	private Long channel_id;
	private Date update_time;
	private Integer amount;
	
	private Integer adv_confrim_num;
	private Long balance_id;
	private Long type_id;

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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Long balance_id) {
		this.balance_id = balance_id;
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
}
