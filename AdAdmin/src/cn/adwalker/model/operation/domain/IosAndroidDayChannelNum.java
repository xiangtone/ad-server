/**
 * <p>Title: ActivateNumIos.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author hadoop
 * @date 2013-11-13
 * @version 1.0
 */
package cn.adwalker.model.operation.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: ActivateNumIos
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-11-13
 */
public class IosAndroidDayChannelNum implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -3012278233107570623L;

	private Long id;
	private Long campaign_id;
	private Long channel_id;
	private String static_date;
	private String charge_type;
	private Integer confirm_amount;
	private Integer status;
	private Double price;
	private Long operater_id;
	private Long parent_id;
	private Date update_time;
	private Date create_time;
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
	public String getStatic_date() {
		return static_date;
	}
	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}
	public String getCharge_type() {
		return charge_type;
	}
	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}
	public Integer getConfirm_amount() {
		return confirm_amount;
	}
	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getOperater_id() {
		return operater_id;
	}
	public void setOperater_id(Long operater_id) {
		this.operater_id = operater_id;
	}
	
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Long getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}
	
	
}
