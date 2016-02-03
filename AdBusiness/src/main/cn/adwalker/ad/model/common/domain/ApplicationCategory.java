package cn.adwalker.ad.model.common.domain;

public class ApplicationCategory implements java.io.Serializable {

	private static final long serialVersionUID = -8561076110915647339L;
	/**
	 * ID
	 */
	private Long id;

	/**
	 * 应用ID
	 */
	private Long appId;

	/**
	 * 分类ID，多个用,分开
	 */
	private String categoryIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

}
