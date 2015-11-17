package cn.adwalker.model.news.domain;


/**
* <p>Title: InteriorMailDev</p>
* <p>Description:站内信中间表domain</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-1-26
 */
public class InteriorMailDev {

	/** 主键 */
	private Long id;
	/** 1草稿，2发布', */
	private Integer status;
	/** 开发者id */
	private Long dev_id;
	/** 站内信id */
	private Long interior_id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getDev_id() {
		return dev_id;
	}
	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}
	public Long getInterior_id() {
		return interior_id;
	}
	public void setInterior_id(Long interior_id) {
		this.interior_id = interior_id;
	}
	
	
	
}
