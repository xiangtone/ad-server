/**
 * <p>Title: App.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-7-23
 * @version 1.0
 */
package cn.adwalker.ad.api.app.vo;

import java.io.Serializable;

/**
 * <p>
 * Title: App
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-23
 */
public class StaticByDay implements Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 7638338243317145525L;

	private Integer click;
	private String static_date;
	private String app_id;
	private String type_id;
	private Integer pospv;
	private Integer pv;
	private Integer download;
	private Integer activate;
	private Double cost;
	private Integer syspv;
	private Integer sysclick;
	private Integer sysdownload;
	private Integer sysactivate;
	private Integer syspospv;
	private Integer clickd;
	private Integer sysclickd;

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public Integer getPospv() {
		return pospv;
	}

	public void setPospv(Integer pospv) {
		this.pospv = pospv;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getActivate() {
		return activate;
	}

	public void setActivate(Integer activate) {
		this.activate = activate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getSyspv() {
		return syspv;
	}

	public void setSyspv(Integer syspv) {
		this.syspv = syspv;
	}

	public Integer getSysclick() {
		return sysclick;
	}

	public void setSysclick(Integer sysclick) {
		this.sysclick = sysclick;
	}

	public Integer getSysdownload() {
		return sysdownload;
	}

	public void setSysdownload(Integer sysdownload) {
		this.sysdownload = sysdownload;
	}

	public Integer getSysactivate() {
		return sysactivate;
	}

	public void setSysactivate(Integer sysactivate) {
		this.sysactivate = sysactivate;
	}

	public Integer getSyspospv() {
		return syspospv;
	}

	public void setSyspospv(Integer syspospv) {
		this.syspospv = syspospv;
	}

	public Integer getClickd() {
		return clickd;
	}

	public void setClickd(Integer clickd) {
		this.clickd = clickd;
	}

	public Integer getSysclickd() {
		return sysclickd;
	}

	public void setSysclickd(Integer sysclickd) {
		this.sysclickd = sysclickd;
	}

}
