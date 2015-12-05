package cn.adwalker.ad.admin.app.vo;

import java.util.Date;

/**
 * 
 * <p>
 * Title: ApplicationVo
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2014-8-26
 */
public class AppEditVo {

	/** 主键 */
	private Long id;

	/** 开发者ID */
	private Long dev_id;

	private String dev_email;
	
	private String 	page_type;
	
	private String 	ad_res;
	
	
	

	/**
	 * 开发者姓名
	 */
	private String dev_name;

	/** 分类ID */
	private Long categoryId;

	/** 应用名称 */
	private String name;

	/** 关键字 */
	private String keyword;

	/** 广告语 */
	private String shortDesc;

	/** 应用描述 */
	private String longDesc;

	/** 应用上传路径 */
	private String projectUrl;

	/** 状态（0：待审核，2：通过，3：未通过，4：上线，5：下线，6：终止） */
	private Integer status;

	/** 审核人 */
	private Long managerId;

	/** 审核时间 */
	private Date checkTime;

	/** 逻辑删除标识(0:未删除 1:删除) */
	private Integer del;

	/** 创建时间 */
	private Date createTime;

	/** 密钥 */
	private String appkey;

	/** 修改时间 */
	private Date updateTime;

	/** 操作系统 */
	private String os;

	/** 上线时间 */
	private Date releaseTime;

	/** 资源大小 */
	private Long resourceSize;

	/** 资源包名称 */
	private String packageName;

	/** 版本名称 */
	private String versionName;

	/** 版本号 */
	private String versionCode;

	/** 审核信息 */
	private String checkMsg;

	/** 最后更新人ID(0:为自己) */
	private Long lastUpdateMan;

	/*** 下线时间 **/
	private Date down_time;

	/**
	 * 媒体评价
	 */
	private Integer scale;
	/**
	 * 是否获取经纬度信息
	 */
	private Integer is_coordinate;

	/** 是否单独投放 **/
	private String placement;

	/*** 大的分类 **/
	private Integer type_id;
	/** 渠道负责人 **/
	private String app_manager;
	
	private Long app_manager_id;

	public String getFile_name() {
		String s = null;
		if (projectUrl != null) {
			if (projectUrl.contains("/")) {
				s = projectUrl.substring(projectUrl.lastIndexOf("/") + 1);
			} else {
				s = projectUrl;
			}
		}
		return s;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

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

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	/**
	 * @return the shortDesc
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * @param shortDesc
	 *            the shortDesc to set
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * @return the longDesc
	 */
	public String getLongDesc() {
		return longDesc;
	}

	/**
	 * @param longDesc
	 *            the longDesc to set
	 */
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	/**
	 * @return the projectUrl
	 */
	public String getProjectUrl() {
		return projectUrl;
	}

	/**
	 * @param projectUrl
	 *            the projectUrl to set
	 */
	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
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
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId
	 *            the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the checkTime
	 */
	public Date getCheckTime() {
		return checkTime;
	}
	
	

	public Long getApp_manager_id() {
		return app_manager_id;
	}

	public void setApp_manager_id(Long app_manager_id) {
		this.app_manager_id = app_manager_id;
	}

	/**
	 * @param checkTime
	 *            the checkTime to set
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
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
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	 * @return the releaseTime
	 */
	public Date getReleaseTime() {
		return releaseTime;
	}

	/**
	 * @param releaseTime
	 *            the releaseTime to set
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * @return the resourceSize
	 */
	public Long getResourceSize() {
		return resourceSize;
	}

	/**
	 * @param resourceSize
	 *            the resourceSize to set
	 */
	public void setResourceSize(Long resourceSize) {
		this.resourceSize = resourceSize;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName
	 *            the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * @param versionName
	 *            the versionName to set
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * @return the versionCode
	 */
	public String getVersionCode() {
		return versionCode;
	}

	/**
	 * @param versionCode
	 *            the versionCode to set
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * @return the checkMsg
	 */
	public String getCheckMsg() {
		return checkMsg;
	}

	/**
	 * @param checkMsg
	 *            the checkMsg to set
	 */
	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}

	/**
	 * @return the lastUpdateMan
	 */
	public Long getLastUpdateMan() {
		return lastUpdateMan;
	}

	/**
	 * @param lastUpdateMan
	 *            the lastUpdateMan to set
	 */
	public void setLastUpdateMan(Long lastUpdateMan) {
		this.lastUpdateMan = lastUpdateMan;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public String getApp_manager() {
		return app_manager;
	}

	public void setApp_manager(String app_manager) {
		this.app_manager = app_manager;
	}

	public Integer getIs_coordinate() {
		return is_coordinate;
	}

	public void setIs_coordinate(Integer is_coordinate) {
		this.is_coordinate = is_coordinate;
	}

	public String getDev_email() {
		return dev_email;
	}

	public void setDev_email(String dev_email) {
		this.dev_email = dev_email;
	}

	public String getDev_name() {
		return dev_name;
	}

	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}

	public String getPage_type() {
		return page_type;
	}

	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}

	public String getAd_res() {
		return ad_res;
	}

	public void setAd_res(String ad_res) {
		this.ad_res = ad_res;
	}
}
