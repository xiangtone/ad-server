/**
 * 
 */
package cn.adwalker.ad.model.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wjp
 * 权限表实体
 */
public class ManagePurview implements Comparable ,Serializable{

	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = -5271142196466894824L;

	/** 权限编号 */
	private Long id;
	
	/** 权限名称 */
	private String resName;
	
	/** 权限url */
	private String resUrl;
	
	/** 父id，0：顶级 */
	private Integer parentId;
	
	/** 菜单排序 */
	private Integer sort;
	
	/** 创建时间 */
	private Date createTime;

	
	
	@Override
	public int compareTo(Object arg0) {
		ManagePurview m = (ManagePurview)arg0;
		if(this.sort > m.sort){
			return 1;
		}else{
			return -1;
		}
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the resName
	 */
	public String getResName() {
		return resName;
	}

	/**
	 * @param resName the resName to set
	 */
	public void setResName(String resName) {
		this.resName = resName;
	}

	/**
	 * @return the resUrl
	 */
	public String getResUrl() {
		return resUrl;
	}

	/**
	 * @param resUrl the resUrl to set
	 */
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	
}
