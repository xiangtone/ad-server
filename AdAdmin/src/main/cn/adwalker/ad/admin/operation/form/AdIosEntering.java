package cn.adwalker.ad.admin.operation.form;

import java.util.Date;



/**
* <p>Title: AdEntering</p>
* <p>Description:广告效果基础数据</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-5-10
 */
public class AdIosEntering {
	/** 主键id */
	private Long id;
	/**  */
	private String ad_id;

	/** 效果发生时间 */
	private String stat_date;	
	/**mac */
	private String income_mac;
	/**mac */
	private String openudid;
	/**mac */
	private String idfa;
	/**mac */
	private Date create_time ;
	/**：0||激活：2*/
	private Integer status ;
	/**录入人 */
	private Long manager_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStat_date() {
		return stat_date;
	}
	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}
	public String getIncome_mac() {
		return income_mac;
	}
	public void setIncome_mac(String income_mac) {
		this.income_mac = income_mac;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	
}
