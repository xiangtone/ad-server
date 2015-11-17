/*
 * RegistVo.java
 *
 * Copyright 2011 1000CHI Software Technology Co.,Ltd
 * All rights reserved.
 * 
 * Created on 01-Dec-2011
 */
package cn.adwalker.ad.admin.ad.vo;


/**
 * 查看活动VO
* <p>Title: CampaignVo</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-4-7
 */
public class AdCategoryVo {
	//定向类别id
	private Integer type;
	//属性值
	private Long id ;
	//活动分类id
	private String content_value ;
	
	
	private Long category_id;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent_value() {
		return content_value;
	}
	public void setContent_value(String content_value) {
		this.content_value = content_value;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
}
