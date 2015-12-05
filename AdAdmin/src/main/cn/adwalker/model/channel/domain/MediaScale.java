/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.model.channel.domain;

import cn.adwalker.core.repository.Entity;

/**
 * 
 * <p>
 * Title: MediaScale
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-8-5
 */
public class MediaScale implements Entity {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -4243142213665169061L;

	/** 主键ID */
	private Long id;

	/**
	 * 活动id
	 */
	private Long campaign_id;

	/**
	 * 媒体id
	 */
	private Long media_id;

	/** 渠道评级 */
	private Double scale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	/**
	 * @param campaign_id
	 *            the campaign_id to set
	 */

	public void setCampaign_id(Long campaign_id) {
		this.campaign_id = campaign_id;
	}

	/**
	 * @return campaign_id
	 */
	public Long getCampaign_id() {
		return campaign_id;
	}

	/**
	 * @param media_id
	 *            the media_id to set
	 */

	public void setMedia_id(Long media_id) {
		this.media_id = media_id;
	}

	/**
	 * @return media_id
	 */
	public Long getMedia_id() {
		return media_id;
	}

}
