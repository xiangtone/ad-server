package cn.adwalker.ad.web.developer.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.adwalker.ad.util.StatusConfigUtil;

/**
 * 功能描述：<br>
 * 上传的SDK应用实体
 * 
 * @author guoyongxiang
 */
public class ApplicationVo {

	/** 主键 */
	private Long id;

	/** 操作系统 */
	private String os;

	private String cname;

	/**
	 * 
	 */
	private String fname;

	/** 开发者ID */
	private Long developerId;
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

	/** 状态（0：草稿，1：待审核，2：通过，3：未通过，4：上线，5：下线，6：终止，9：暂停，10：删除） */
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

	/** 开发者email */
	private String devEmail;

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

	private String marketUrl;

	/*** 下线时间 **/
	private Date down_time;
	/*** 黑名单 **/
	private Integer shitList;

	private Integer flow_status;// 应用状态

	public Long getDev_id() {
		return dev_id;
	}

	public void setDev_id(Long dev_id) {
		this.dev_id = dev_id;
	}

	private List<Integer> typeList;

	public String getMarketUrl() {
		return marketUrl;
	}

	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}

	public String getCategoryName() {
		String string = null;
		if (!StringUtils.isEmpty(fname) && !StringUtils.isEmpty(cname)) {
			string = fname + "--" + cname;
		}
		return string;
	}

	public Integer getFlow_status() {
		return flow_status;
	}

	public void setFlow_status(Integer flow_status) {
		this.flow_status = flow_status;
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setDown_time(Date down_time) {
		this.down_time = down_time;
	}

	public Integer getShitList() {
		return shitList;
	}

	public void setShitList(Integer shitList) {
		this.shitList = shitList;
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
	 * @return the developerId
	 */
	public Long getDeveloperId() {
		return developerId;
	}

	/**
	 * @param developerId the developerId to set
	 */
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
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
	 * @param name the name to set
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
	 * @param keyword the keyword to set
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
	 * @param shortDesc the shortDesc to set
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * @return the iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl the iconUrl to set
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * @return the longDesc
	 */
	public String getLongDesc() {
		return longDesc;
	}

	/**
	 * @param longDesc the longDesc to set
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
	 * @param projectUrl the projectUrl to set
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
	 * @param status the status to set
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
	 * @param managerId the managerId to set
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

	/**
	 * @param checkTime the checkTime to set
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
	 * @param del the del to set
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
	 * @param createTime the createTime to set
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
	 * @param appkey the appkey to set
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
	 * @param updateTime the updateTime to set
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
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the devEmail
	 */
	public String getDevEmail() {
		return devEmail;
	}

	/**
	 * @param devEmail the devEmail to set
	 */
	public void setDevEmail(String devEmail) {
		this.devEmail = devEmail;
	}

	/**
	 * @return the releaseTime
	 */
	public Date getReleaseTime() {
		return releaseTime;
	}

	/**
	 * @param releaseTime the releaseTime to set
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
	 * @param resourceSize the resourceSize to set
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
	 * @param packageName the packageName to set
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
	 * @param versionName the versionName to set
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
	 * @param versionCode the versionCode to set
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
	 * @param checkMsg the checkMsg to set
	 */
	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}

	public List<Integer> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Integer> typeList) {
		this.typeList = typeList;
	}

	public String getTypeNames() {
		StringBuilder sb = new StringBuilder();
		if (typeList != null && typeList.size() > 0) {
			int m = 0;
			for (Integer i : typeList) {
				switch (i) {
				case 0:
					sb.append("积分墙");
					break;
				case 1:
					sb.append("推荐墙");
					break;
				case 2:
					sb.append("九宫格");
					break;
				case 4:
					sb.append("banner");
					break;
				case 5:
					sb.append("插屏");
					break;
				}
				if (m != (typeList.size() - 1)) {
					sb.append("\t");
				}
				m++;
			}
		}
		return sb.toString();
	}

	/**
	 * @return the appStatusName
	 */
	public String getStatusName() {
		String str = null;
		if (this.status != null) {
			str = StatusConfigUtil.getString(this.status.toString());
		}
		return str;
	}
}