/**
 * <p>Title: ReportAdByHour.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-8-15
 * @version 1.0
 */
package cn.adwalker.ad.admin.report.vo;

/**
* <p>Title: ReportResByDay</p>
* <p>Description:Res数据统计Vo</p>
* <p>Company: adwalker</p> 
* @author    lichuang
* @date       2014年10月17日
 */
public class ReportResByDay {
	private String static_date;
	private String static_hour;
	private String id;
	private String placement_name;
	private String fname;
	private int res_type;
	private Double adpv;
	private Double click;
	private Double download;
	private Double activate;
	private Double cost;
	private Double ctrc;
	private Double ctrd;
	private Double ctra;

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public String getStatic_hour() {
		return static_hour;
	}

	public void setStatic_hour(String static_hour) {
		this.static_hour = static_hour;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlacement_name() {
		return placement_name;
	}

	public void setPlacement_name(String placement_name) {
		this.placement_name = placement_name;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	
	public Double getAdpv() {
		return adpv;
	}

	public void setAdpv(Double adpv) {
		this.adpv = adpv;
	}

	public Double getClick() {
		return click;
	}

	public void setClick(Double click) {
		this.click = click;
	}

	public Double getDownload() {
		return download;
	}

	public void setDownload(Double download) {
		this.download = download;
	}

	public Double getActivate() {
		return activate;
	}

	public void setActivate(Double activate) {
		this.activate = activate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getCtrc() {
		return ctrc;
	}

	public void setCtrc(Double ctrc) {
		this.ctrc = ctrc;
	}

	public Double getCtrd() {
		return ctrd;
	}

	public void setCtrd(Double ctrd) {
		this.ctrd = ctrd;
	}

	public Double getCtra() {
		return ctra;
	}

	public void setCtra(Double ctra) {
		this.ctra = ctra;
	}

	public int getRes_type() {
		return res_type;
	}

	public void setRes_type(int res_type) {
		this.res_type = res_type;
	}
	
	
}
