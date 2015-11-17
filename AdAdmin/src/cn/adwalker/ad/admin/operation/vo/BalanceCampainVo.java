package cn.adwalker.ad.admin.operation.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: BalanceCampainVo
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
public class BalanceCampainVo implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 3940658737053467729L;
	private Long id;
	private Long media_id;

	private Integer media_type;

	private String media_name;
	private Long balance_id;
	private Double expect_cost;
	private Double balance_cost;
	private Integer days;
	private Integer status;
	private Date update_time;
	private Date create_time;
	private Integer expect_amount;
	private Integer balance_amount;

	public Integer getMedia_type() {
		return media_type;
	}

	public void setMedia_type(Integer media_type) {
		this.media_type = media_type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	public Long getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(Long balance_id) {
		this.balance_id = balance_id;
	}

	public Double getExpect_cost() {
		return expect_cost;
	}

	public void setExpect_cost(Double expect_cost) {
		this.expect_cost = expect_cost;
	}

	public Double getBalance_cost() {
		return balance_cost;
	}

	public void setBalance_cost(Double balance_cost) {
		this.balance_cost = balance_cost;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getExpect_amount() {
		return expect_amount;
	}

	public void setExpect_amount(Integer expect_amount) {
		this.expect_amount = expect_amount;
	}

	public Integer getBalance_amount() {
		return balance_amount;
	}

	public void setBalance_amount(Integer balance_amount) {
		this.balance_amount = balance_amount;
	}

	public String getMedia_name() {
		return media_name;
	}

	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}
}
