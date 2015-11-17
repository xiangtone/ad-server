/**
 * 
 */
package cn.adwalker.model.sys.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wjp 权限表实体
 */
@SuppressWarnings("rawtypes")
public class SysPurview implements Comparable, Serializable {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = 224798161157585336L;

	/** 权限编号 */
	private Long id;

	/** 权限名称 */
	private String name;

	/** 权限url */
	private String url;

	/** 父id，0：顶级 */
	private Long parentId;

	/** 菜单排序 */
	private Integer sort;

	/** 创建时间 */
	private Date createTime;

	@Override
	public int compareTo(Object arg0) {
		int i = -1;
		SysPurview m = (SysPurview) arg0;
		if (m != null && this.sort != null) {
			if ((this.sort > m.sort)) {
				i = 1;
			}

		}
		return i;

	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
