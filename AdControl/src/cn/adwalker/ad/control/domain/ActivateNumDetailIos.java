package cn.adwalker.ad.control.domain;

import java.util.Date;

public class ActivateNumDetailIos  {
	private Long id;
	private String static_date;
	private Integer sys_activate;
	private Long media_id;
	private Long type_id;
	private Date create_time;
	private Long parent_id;
	private Integer confirm_num;
	private Double sys_cost;
	private Integer confirm_amount;
	private Integer status;
	private Double out_price;
	private Integer media_type;
	private String blance_mode;
	private Date submit_time;
	private Integer activate;
	private Integer click;
	private Long ad_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public Integer getSys_activate() {
		return sys_activate;
	}

	public void setSys_activate(Integer sys_activate) {
		this.sys_activate = sys_activate;
	}

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Integer confirm_num) {
		this.confirm_num = confirm_num;
	}

	public Double getSys_cost() {
		return sys_cost;
	}

	public void setSys_cost(Double sys_cost) {
		this.sys_cost = sys_cost;
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

	public Double getOut_price() {
		return out_price;
	}

	public void setOut_price(Double out_price) {
		this.out_price = out_price;
	}

	public Integer getMedia_type() {
		return media_type;
	}

	public void setMedia_type(Integer media_type) {
		this.media_type = media_type;
	}

	public String getBlance_mode() {
		return blance_mode;
	}

	public void setBlance_mode(String blance_mode) {
		this.blance_mode = blance_mode;
	}

	public Date getSubmit_time() {
		return submit_time;
	}

	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
}
