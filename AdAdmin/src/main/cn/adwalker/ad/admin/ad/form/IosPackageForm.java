package cn.adwalker.ad.admin.ad.form;

import java.util.Date;

/**
 * <p>
 * Title: PlacementPackageVo
 * </p>
 * <p>
 * Description:广告包
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-4-12
 */
public class IosPackageForm {

	// 主键
	private Long id;
	// 资源下载地址
	private String res_url;
	// 创建人
	private Long create_user;
	// 包编号
	private String code;
	// 文件名
	private String file_name;
	// 文件大小
	private Double file_size;
	// 活动id
	private Long placement_id;

	// 包名：com.jingdong.XX
	private String package_name;
	// 版本名称
	private String version_name;
	// 版本号
	private String version_code;

	private Date create_time;

	private Integer status;
	//是否开启免跳转
	private Integer appstore_status;
	//ios广告appstore的id
	private Integer appstore_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRes_url() {
		return res_url;
	}

	public void setRes_url(String res_url) {
		this.res_url = res_url;
	}

	public Long getCreate_user() {
		return create_user;
	}

	public void setCreate_user(Long create_user) {
		this.create_user = create_user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Double getFile_size() {
		return file_size;
	}

	public void setFile_size(Double file_size) {
		this.file_size = file_size;
	}

	public Long getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(Long placement_id) {
		this.placement_id = placement_id;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public String getVersion_name() {
		return version_name;
	}

	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}

	public String getVersion_code() {
		return version_code;
	}

	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAppstore_status() {
		return appstore_status;
	}

	public void setAppstore_status(Integer appstore_status) {
		this.appstore_status = appstore_status;
	}

	public Integer getAppstore_id() {
		return appstore_id;
	}

	public void setAppstore_id(Integer appstore_id) {
		this.appstore_id = appstore_id;
	}

}
