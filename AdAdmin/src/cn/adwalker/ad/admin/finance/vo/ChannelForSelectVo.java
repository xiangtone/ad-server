/**
 * <p>Title: ChannelForSelectVo.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-10-31
 * @version 1.0
 */
package cn.adwalker.ad.admin.finance.vo;

import java.io.Serializable;

/**
 * <p>
 * Title: ChannelForSelectVo
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
public class ChannelForSelectVo implements Serializable{
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 2816960275635371309L;
	private Long media_id;
	private Long campaign_id;
	private Double cost;
	private Integer amount;
	private Integer days;

	private String campaign_name;
	private String media_name;

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}
}
