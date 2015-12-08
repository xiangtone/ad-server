/**
* <p>Title: EscoreCategorye.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-1-8
* @version 1.0
*/
package cn.adwalker.ad.bean;

import java.util.Date;

/**
 * <p>Title: EscoreCategorye</p>
 * <p>Description:广告分类表</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-1-8
 */
public class SysCategorye extends Data{
	private static final long serialVersionUID = -647255345275036380L;
	private Long id;			//分类id
	private String name;		//分类名称
	private Long parent_id;		//分类名称
	private String platform;	//平台
	private Integer sort;	    //平台
	private String short_desc;	//短描述
	private String icon;	    //图标
	private Date create_time;	//sysdate
	private String[] sonIds;    //子孙id集合
	
	
	
	public String[] getSonIds() {
		return sonIds;
	}
	public void setSonIds(String[] sonIds) {
		this.sonIds = sonIds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getShort_desc() {
		return short_desc;
	}
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	

}
