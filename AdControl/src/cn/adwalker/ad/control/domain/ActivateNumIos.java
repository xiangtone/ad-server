package cn.adwalker.ad.control.domain;

import java.util.Date;

public class ActivateNumIos {
	private Long id;
	private Long campaign_id;
	private String static_date;
	private String balance_model;
	private Integer activate;
	private Integer click;
	private Integer confirm_amount;
	private Integer status;
	private Double price;
	private Long operater_id;;
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
	
	public Integer getActivate() {
		return activate;
	}
	public void setActivate(Integer activate) {
		this.activate = activate;
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
	public String getBalance_model() {
		return balance_model;
	}
	public void setBalance_model(String balance_model) {
		this.balance_model = balance_model;
	}
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
	}
}
