/**
 * <p>Title: AdEditForm.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-4-17
 * @version 1.0
 */
package cn.adwalker.ad.admin.ad.form;

import java.util.Date;

/**
 * <p>
 * Title: AdEditForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-4-17
 */
public class AdAddForm {

	private Long page_type;

	private Long appid;
	
	private Long placement_id;

	/**
	 * 开始时间
	 */
	private Date start_time;

	/**
	 * 结束时间
	 */
	private Date end_time;

	/**
	 * 包
	 */
	private Long package_id;

	/**
	 * 日投放量
	 */
	private Double budget_day;

	/**
	 * 日投放量单位
	 */
	private String budget_type;

	/**
	 * 结算单价
	 */
	private Double blance_price;

	/**
	 * 结算方式
	 */
	private String blance_mode;

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Long getPackage_id() {
		return package_id;
	}

	public void setPackage_id(Long package_id) {
		this.package_id = package_id;
	}

	public Double getBudget_day() {
		return budget_day;
	}

	public void setBudget_day(Double budget_day) {
		this.budget_day = budget_day;
	}

	public String getBudget_type() {
		return budget_type;
	}

	public void setBudget_type(String budget_type) {
		this.budget_type = budget_type;
	}

	public Double getBlance_price() {
		return blance_price;
	}

	public void setBlance_price(Double blance_price) {
		this.blance_price = blance_price;
	}

	public String getBlance_mode() {
		return blance_mode;
	}

	public void setBlance_mode(String blance_mode) {
		this.blance_mode = blance_mode;
	}

	public Long getPage_type() {
		return page_type;
	}

	public void setPage_type(Long page_type) {
		this.page_type = page_type;
	}

	public Long getAppid() {
		return appid;
	}

	public void setAppid(Long appid) {
		this.appid = appid;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
}
