package cn.adwalker.model.finance.domain;

import java.util.Date;

import cn.adwalker.core.repository.Entity;

/**
 * <p>
 * Title: BalanceChannelEditVo
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: adwalker 
 * </p>
 * 
 * @author cuidd
 * @date 2013-10-30
 */
public class BalanceChannel implements Entity {


	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -3268614673896979499L;
	private String start_date;
	private String end_date;
	private Long channel_id;
	private Long campaign_id;
	private Double cost;
	private Long id;
	private Date create_time;	
	private Integer status;
	private Long manager_id;		
	private Double price;	
	private Date update_time;
	private Integer confirm_amount;	
	private String os;
	private Long create_user;
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	public Long getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}
	public Long getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getConfirm_amount() {
		return confirm_amount;
	}
	public void setConfirm_amount(Integer confirm_amount) {
		this.confirm_amount = confirm_amount;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Long getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}
}
