package cn.adwalker.ad.model.application.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 功能描述：<br>
 * 上传的SDK应用实体
 */
@Entity
@Table(name = "DEVELOPED_APP")
public class Application {

	/** 主键 */
	private Long id;

	/** 开发者ID */
	private Long dev_id;

	/** 分类ID */
	private Long categoryId;

	/** 应用名称 */
	private String name;

	/** 关键字 */
	private String keyword;

	/** 广告语 */
	private String shortDesc;

	/** 图标url */
	private String iconUrl;

	/** 应用描述 */
	private String longDesc;

	/** 应用上传路径 */
	private String projectUrl;

	/** 状态（0：待审核，1：审核中，2：通过，3：未通过，4：上线，5：下线，6：终止） */
	private Integer status;

	/** 审核人 */
	private Long managerId;

	/** 审核时间 */
	private Date checkTime;

	/** 逻辑删除标识(0:未删除 1:删除) */
	private Integer del;
	/**  */
	private Integer app_res;

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
	/** 媒体负责人 */
	private String app_manager;

	/** 最后更新人ID(0:为自己) */
	private Long lastUpdateMan;

	/*** 下线时间 **/
	private Date down_time;
	
	private String marketUrl;
	/*** 媒体评价 **/
	private Double scale;
	
	public String getMarketUrl() {
		return marketUrl;
	}

	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}

	/*** 大的分类 **/
	private Integer type_id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getProjectUrl() {
		return projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Long getResourceSize() {
		return resourceSize;
	}

	public void setResourceSize(Long resourceSize) {
		this.resourceSize = resourceSize;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getCheckMsg() {
		return checkMsg;
	}

	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}

	public Long getLastUpdateMan() {
		return lastUpdateMan;
	}

	public void setLastUpdateMan(Long lastUpdateMan) {
		this.lastUpdateMan = lastUpdateMan;
	}

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	public Integer getApp_res() {
		return app_res;
	}

	public void setApp_res(Integer app_res) {
		this.app_res = app_res;
	}

	public String getApp_manager() {
		return app_manager;
	}

	public void setApp_manager(String app_manager) {
		this.app_manager = app_manager;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}
	
}
