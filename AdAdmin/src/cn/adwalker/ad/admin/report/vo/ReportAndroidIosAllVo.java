package cn.adwalker.ad.admin.report.vo;

import java.math.BigDecimal;

/**
* <p>Title: ReportAndroidIosAllVo</p>
* <p>Description:流量趋势图Vo</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-19
 */
public class ReportAndroidIosAllVo {
	/** 主键ID */
	private Long id;
	/** 时间 */
	private String static_date;
	/** PV */
	private  BigDecimal pv;
	/** 点击 */
	private  BigDecimal click;
	
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
	public BigDecimal getPv() {
		return pv;
	}
	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}
	public BigDecimal getClick() {
		return click;
	}
	public void setClick(BigDecimal click) {
		this.click = click;
	}
}
