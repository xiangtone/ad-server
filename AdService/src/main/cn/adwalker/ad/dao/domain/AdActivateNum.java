package cn.adwalker.ad.dao.domain;

import java.io.Serializable;

/**
 * 
* <p>Title: AdActivateNum</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author    cuidd
* @date       2014年11月13日
 */
public class AdActivateNum implements Serializable {
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -549029193439658179L;
	// 主键
	private String ad_id;
	// 活动id
	private Integer activate_num;
	
	public AdActivateNum() {
		super();
	}
	
	public AdActivateNum(String ad_id, Integer activate_num) {
		super();
		this.ad_id = ad_id;
		this.activate_num = activate_num;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public Integer getActivate_num() {
		return activate_num;
	}
	public void setActivate_num(Integer activate_num) {
		this.activate_num = activate_num;
	}
}
