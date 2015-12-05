/**
 * <p>Title: PackageForm.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author kingdom
 * @date 2013-5-9
 * @version 1.0
 */
package cn.adwalker.ad.admin.ad.form;

/**
 * <p>
 * Title: PackageForm
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-9
 */
public class PackageForm {

	/** 主键 */
	private Long placement_id;

	/** 资源url */
	private String resourceUrl;

	/** 资源大小 */
	private Double resourceSize;

	/** 资源包名称 */
	private String packageName;

	/** 版本号 */
	private String versionCode;

	/** 版本名称 */
	private String versionName;

	/** 文件名称 */
	private String fileName;

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public Double getResourceSize() {
		return resourceSize;
	}

	public void setResourceSize(Double resourceSize) {
		this.resourceSize = resourceSize;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}
}
