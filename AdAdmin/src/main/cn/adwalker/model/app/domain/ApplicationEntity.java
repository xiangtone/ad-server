package cn.adwalker.model.app.domain;

import java.util.Date;

import cn.adwalker.ad.config.StatusConfigUtil;
import cn.adwalker.core.repository.Entity;

/**
 * 功能描述：<br>
 * 上传的SDK应用实体
 * 
 * @author guoyongxiang
 */
public class ApplicationEntity implements Entity {

	/** @Fields serialVersionUID : TODO */
	private static final long serialVersionUID = -5597528213085088413L;

	/** 主键 */
	private Long id;

	/** 开发者ID */
	private Long dev_id;

	/** 分类ID */
	private Long category_id;

	/** 应用名称 */
	private String name;

	/** 关键字 */
	private String keyword;

	/** 应用描述 */
	private String long_desc;

	/** 应用上传路径 */
	private String project_url;

	/** 状态（0：待审核，2：通过，3：未通过，4：上线，5：下线，6：终止） */
	private Integer status;

	/** 审核人 */
	private Long manager_id;

	/** 审核时间 */
	private Date check_time;

	/** 逻辑删除标识(0:未删除 1:删除) */
	private Integer del;

	/** 创建时间 */
	private Date create_time;

	/** 密钥 */
	private String appkey;

	/** 修改时间 */
	private Date update_time;

	/** 操作系统 */
	private String os;

	/** 上线时间 */
	private Date release_time;

	/** 资源大小 */
	private Long resource_size;

	/** 资源包名称 */
	private String package_name;

	/** 版本名称 */
	private String version_name;

	/** 版本号 */
	private String version_code;

	/** 审核信息 */
	private String check_msg;

	/*** 下线时间 **/
	private Date down_time;
	
	/**
	 * 应用来源
	 */
	private Integer app_res;

	/**
	 * 媒体评价
	 */
	private Double scale;

	/** 是否单独投放 **/
	private String placement;

	public Date getDown_time() {
		return down_time;
	}

	public void setDown_time(Date down_time) {
		this.down_time = down_time;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLong_desc() {
		return long_desc;
	}

	public void setLong_desc(String long_desc) {
		this.long_desc = long_desc;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the del
	 */
	public Integer getDel() {
		return del;
	}

	/**
	 * @param del
	 *            the del to set
	 */
	public void setDel(Integer del) {
		this.del = del;
	}

	/**
	 * @return the appkey
	 */
	public String getAppkey() {
		return appkey;
	}

	/**
	 * @param appkey
	 *            the appkey to set
	 */
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the appStatusName
	 */
	public String getAppStatusName() {
		return StatusConfigUtil.getString(this.status.toString());
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public Double getScale() {
		return scale;
	}

	public Long getResource_size() {
		return resource_size;
	}

	public void setResource_size(Long resource_size) {
		this.resource_size = resource_size;
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

	public String getProject_url() {
		return project_url;
	}

	public void setProject_url(String project_url) {
		this.project_url = project_url;
	}

	public Date getCheck_time() {
		return check_time;
	}

	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getRelease_time() {
		return release_time;
	}

	public void setRelease_time(Date release_time) {
		this.release_time = release_time;
	}

	public String getCheck_msg() {
		return check_msg;
	}

	public void setCheck_msg(String check_msg) {
		this.check_msg = check_msg;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public Integer getApp_res() {
		return app_res;
	}

	public void setApp_res(Integer app_res) {
		this.app_res = app_res;
	}
}
