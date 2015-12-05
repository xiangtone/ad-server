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
 * 定向投放VO
* <p>Title: CampaignCategoryRel</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-7
 */
public class AdCategoryRel implements Entity {
    /** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -3635290268221557003L;
	//活动id
    private Long id;
    //活动id
    private Long ad_id;
    //分类id（外键）
    private Long category_id;
    
    
    public AdCategoryRel(){
    	super();
    	
    }
    
    
	
	/**
	* <p>Title: </p>
	* <p>Description:TODO</p>
	* @param id
	* @param campaign_id
	* @param category_id
	* @param type
	*/
	public AdCategoryRel(Long ad_id, Long category_id) {
		super();
		this.ad_id = ad_id;
		this.category_id = category_id;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Long getAd_id() {
		return ad_id;
	}

	public void setAd_id(Long ad_id) {
		this.ad_id = ad_id;
	}
}
