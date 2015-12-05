package cn.adwalker.model.operation.domain;

import cn.adwalker.core.repository.Entity;

/**
* <p>Title: DeveloerChannel</p>
* <p>Description:TODO</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-10-28
 */
public class DeveloerChannel implements Entity {
	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 3812329217845171997L;

	/** 主键ID */
	private Long id;	
	/** 公司名 */
	private String media_name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMedia_name() {
		return media_name;
	}
	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}


}