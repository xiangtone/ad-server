/**
* <p>Title: CampaignCategory.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-6-3
* @version 1.0
*/
package cn.adwalker.ad.bean;

/**
 * <p>Title: CampaignCategory</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-6-3
 */
public class SysSort extends Data {

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 2232421479958109109L;
	
	private Integer id;
	private String content_value;
	private Integer type;
	private Integer parent_id;
	private String name;
	private Integer sort;
	
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parentId) {
		parent_id = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent_value() {
		return content_value;
	}
	public void setContent_value(String content_value) {
		this.content_value = content_value;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	

}
