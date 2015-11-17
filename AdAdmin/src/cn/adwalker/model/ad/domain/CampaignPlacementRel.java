/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.ad.domain;

import cn.adwalker.core.repository.Entity;

/**
 * 活动主持VO
 * <p>
 * Title: PlacementVo
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
public class CampaignPlacementRel implements Entity {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 8188873042949439119L;

	// 主键
	private Long id;

	// 活动id
	private Long campaign_id;

	// 投放id
	private Long placement_id;

	// 状态（-50活动草稿、-40活动待审核、-30待投放，-20投放草稿，-10投放待审核 ,1上线，0下线）
	private Integer status;

	/********************** getter\setter方法 ****************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
