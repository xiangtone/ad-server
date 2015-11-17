package cn.adwalker.ad.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * <p>线下广告主数据bean</p>
 * @author jief
 *
 */
public class OffLineData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3961459639594106713L;
	private Long id;
	private String ad_id;
	private String ad_key;
	private Date active_date;
	private Integer status;
	private String income_mac;
	private String openudid;
	private String idfa;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getActive_date() {
		return active_date;
	}
	public void setActive_date(Date active_date) {
		this.active_date = active_date;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getIncome_mac() {
		return income_mac;
	}
	public void setIncome_mac(String income_mac) {
		this.income_mac = income_mac;
	}
	public String getOpenudid() {
		return openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getAd_key() {
		return ad_key;
	}
	public void setAd_key(String ad_key) {
		this.ad_key = ad_key;
	}
	
}
