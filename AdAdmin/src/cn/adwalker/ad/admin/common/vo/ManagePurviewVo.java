/**
 * 
 */
package cn.adwalker.ad.admin.common.vo;

import java.util.Date;

/**
 * @author wjp
 * 权限表实体
 */
public class ManagePurviewVo {

	/** 权限编号 */
	private Long id;
	
	/** 权限名称 */
	private String resName;
	
	/** 权限url */
	private String resUrl;
	
	/** 父id，0：顶级 */
	private int parentId;
	
	/** 菜单排序 */
	private int sort;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 是否默认选中 0:否，1：是 */
	private int isChecked;

	
	/**
	 * @return the isChecked
	 */
	public int getIsChecked() {
		return isChecked;
	}

	/**
	 * @param isChecked the isChecked to set
	 */
	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
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
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
	
}
