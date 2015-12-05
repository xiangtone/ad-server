/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.ad.config.StatusConstant;
import cn.adwalker.model.ad.domain.Type;

/**
 * 活动主持VO
 * <p>
 * Title: CampaignVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-7
 */
public class PlacementVo implements Serializable {

	private static final long serialVersionUID = -2237320771076063905L;

	// 主键
	private Long id;

	// 广告主id
	private Integer adv_id;

	// 广告主
	private String company_name;

	// 活动名称
	private String campaign_name;

	// 活动类型
	private Integer campaign_type;

	// 活动类别(生活类，工具类。。。。)
	private Integer placement_category_id;

	// 活动名称
	private String placement_name;
	

	private Double star_level;
	
	/**
	 * 接入单价
	 */
	private Double price;
	
	

	// 状态
	private Integer status;

	/**
	 * 广告形式
	 */
	private List<Type> typeList;

	/**
	 * 投放id
	 */
	private Long placement_id;

	// 平台类型
	private String os;
	// 排期开始时间
	private Date plan_start;
	// 排期结束时间
	private Date plan_end;
	// 数据入库时间
	private Timestamp create_time;
	// 操作人id
	private Long create_user;

	private String create_user_name;
	
	
	public String getStatus_name(){
		return StatusConstant.getCampaignStatusName(status);
	}

	public String getTypeStr() {
		String str = null;
		if (typeList != null && typeList.size() > 0) {
			for (Type type : typeList) {
				if (!StringUtils.isEmpty(str)) {
					str = str + "," + type.getName();
				} else {
					str = type.getName();
				}
			}

		}
		return str;
	}

	/********************** getter\setter方法 ****************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCampaign_type() {
		return campaign_type;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public void setCampaign_type(Integer campaign_type) {
		this.campaign_type = campaign_type;
	}

	

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Integer adv_id) {
		this.adv_id = adv_id;
	}

	public Integer getPlacement_category_id() {
		return placement_category_id;
	}

	public void setPlacement_category_id(Integer placement_category_id) {
		this.placement_category_id = placement_category_id;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public Date getPlan_start() {
		return plan_start;
	}

	public void setPlan_start(Date plan_start) {
		this.plan_start = plan_start;
	}

	public Date getPlan_end() {
		return plan_end;
	}

	public void setPlan_end(Date plan_end) {
		this.plan_end = plan_end;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public String getPlacement_name() {
		return placement_name;
	}

	public void setPlacement_name(String placement_name) {
		this.placement_name = placement_name;
	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public Double getStar_level() {
		return star_level;
	}

	public void setStar_level(Double star_level) {
		this.star_level = star_level;
	}
}
