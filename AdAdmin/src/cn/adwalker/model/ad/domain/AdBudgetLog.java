/**
 * <p>Title: AdBudgetLog.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-9-2
 * @version 1.0
 */
package cn.adwalker.model.ad.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: AdBudgetLog
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-9-2
 */
public class AdBudgetLog implements Serializable {
	private static final long serialVersionUID = 8458353803636397050L;
	private Long id;
	private Long ad_id;
	private Integer budget_day;
	private Integer cost_day;
	private String budget_type;
	/**
	 * 天
	 */
	private String static_date;
	private Date online_time;
	private Date offline_time;
	private Date create_time;

	public Date getOff_line_time() {
		return new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Integer budget_day) {
		this.budget_day = budget_day;
	}

	public Integer getCost_day() {
		return cost_day;
	}

	public void setCost_day(Integer cost_day) {
		this.cost_day = cost_day;
	}

	public String getBudget_type() {
		return budget_type;
	}

	public void setBudget_type(String budget_type) {
		this.budget_type = budget_type;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Date online_time) {
		this.online_time = online_time;
	}

	public Date getOffline_time() {
		return offline_time;
	}

	public void setOffline_time(Date offline_time) {
		this.offline_time = offline_time;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}
}
