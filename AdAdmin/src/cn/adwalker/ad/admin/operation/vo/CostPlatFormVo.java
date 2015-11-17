/**
* <p>Title: CostPlatForm.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author hadoop
* @date 2013-10-31
* @version 1.0
*/
package cn.adwalker.ad.admin.operation.vo;

import java.io.Serializable;

/**
 * <p>Title: CostPlatForm</p>
 * <p>Description:平台成本vo</p>
 * <p>Company: adwalker</p> 
 * @author    cuidd
 * @date       2013-10-31
 */
public class CostPlatFormVo implements Serializable{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 7345693897916625009L;
	/**
	 * 广告id
	 */
	private Long ad_id;
	
	/**
	 * 应用id
	 */
	private Long app_id;
	
	/**
	 * 广告形式
	 */
	private Long type_id;
	
	/**
	 * 成本
	 */
	private Double cost;
	
	/**
	 * 个数
	 */
	private Integer effect_num;

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public Long getType_id() {
		return type_id;
	}

	public void setType_id(Long type_id) {
		this.type_id = type_id;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getEffect_num() {
		return effect_num;
	}

	public void setEffect_num(Integer effect_num) {
		this.effect_num = effect_num;
	}
}
