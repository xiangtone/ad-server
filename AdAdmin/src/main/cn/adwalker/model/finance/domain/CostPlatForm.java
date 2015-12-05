package cn.adwalker.model.finance.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>Title: CostPlatForm</p>
 * <p>Description:平台成本</p>
 * <p>Company: adwalker</p> 
 * @author    cuidd
 * @date       2013-10-31
 */
public class CostPlatForm implements Entity{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 7345693897916625009L;
	
	private Long id;
	
	/**
	 * 
	 */
	private Long campaign_id;
	
	/**
	 * 应用id
	 */
	private Long app_id;
	
	/**
	 * 广告形式
	 */
	private Long type_id;
	
	/**
	 * 成本
	 */
	private Double cost;
	
	/**
	 * 个数
	 */
	private Integer amount;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 结算id
	 */
	private Long balance_id;
	
	/**
	 * 状态
	 */
	private Integer status;;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Long balance_id) {
		this.balance_id = balance_id;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
