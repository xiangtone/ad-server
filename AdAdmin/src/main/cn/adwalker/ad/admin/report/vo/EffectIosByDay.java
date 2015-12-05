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
 * <p>
 * Title: ReportAdByHour
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-15
 */
public class EffectIosByDay {

	private String static_date;
	private String ad_id;
	private String ad_name;
	private String channel_name;
	private String fname;
	private String os;
	//接入单价
	private Double in_price;
	private Double sys_num;
	private Double confirm_num;
	private Double ctrc;

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getStatic_date() {
		return static_date;
	}

	public void setStatic_date(String static_date) {
		this.static_date = static_date;
	}

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Double getCtrc() {
		return ctrc;
	}

	public void setCtrc(Double ctrc) {
		this.ctrc = ctrc;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public Double getSys_num() {
		return sys_num;
	}

	public void setSys_num(Double sys_num) {
		this.sys_num = sys_num;
	}

	public Double getConfirm_num() {
		return confirm_num;
	}

	public void setConfirm_num(Double confirm_num) {
		this.confirm_num = confirm_num;
	}

	public Double getIn_price() {
		return in_price;
	}

	public void setIn_price(Double in_price) {
		this.in_price = in_price;
	}

}
