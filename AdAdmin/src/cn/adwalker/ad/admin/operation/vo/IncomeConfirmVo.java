package cn.adwalker.ad.admin.operation.vo;

import java.util.Date;
/**
* <p>Title: IncomeConfirmVo</p>
* <p>Description:IOS确认数</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-6-1
 */
public class IncomeConfirmVo {
	/** 主键id */
	private Long id;
	/** 广告id*/
	private String placement_id;
	/** add*/
	private String ad_id;
	/**广告名称 */
	private String ad_name;
	/**状态 */
	private Integer status;
	/** 效果发生时间 */
	private String  static_date; 	
	/**mac */
	private String income_mac;
	/**mac */
	private String openudid;
	/**mac */
	private String idfa;
	/**创建时间 */
	private Date create_time;
	/** 录入人id */
	private Long manager_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlacement_id() {
		return placement_id;
	}
	public void setPlacement_id(String placement_id) {
		this.placement_id = placement_id;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatic_date() {
		return static_date;
	}
	public void setStatic_date(String static_date) {
		this.static_date = static_date;
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
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
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
	
	
}
