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
public class BlanceCostChannelVo implements Serializable {

	/** @Fields serialVersionUID :  */
	private static final long serialVersionUID = 4475803794461940939L;
	private Long id;
	private String static_date;
	private Long type_id;
	
	private Long campaign_id;
	private Double cost;
	private Integer amount;
	public String getStatic_date() {
		return static_date;
	}
	public void setStatic_date(String static_date) {
		this.static_date = static_date;
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
}
