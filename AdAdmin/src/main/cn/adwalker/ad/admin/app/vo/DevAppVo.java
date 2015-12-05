/**
 * 
 */
package cn.adwalker.ad.admin.app.vo;

import java.util.Date;

import cn.adwalker.ad.config.StatusConfigUtil;

/**
 * @author guoyongxiang
 * 
 */
public class DevAppVo {

	/** 主键 */
	private Long id;

	/** 开发者ID */
	private Long developerId;

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

	/**   */
	private Date releaseTime;

	/** 密钥 */
	private String appkey;

	/** 修改时间 */
	private Date updateTime;

	/** 操作系统 */
	private String os;

	/** 状态名称 */
	private String statusName;

	/** 文件名称 */
	private String fileName;

	/** 审核信息 */
	private String checkMsg;
	
	/**
	 * @return Returns the statusName.
	 */
	public String getStatusName() {
		if (status == 0) {
			return "待提交";
		} else if (status == 2) {
			return "通过";
		} else if (status == 3) {
			return "未通过";
		} else if (status == 4) {
			return "上线";
		} else if (status == 5) {
			return "下线";
		} else if (status == 6) {
			return "终止";
		}
		return statusName;
	}

	/**
	 * @param statusName
	 *            The statusName to set.
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the developerId.
	 */
	public Long getDeveloperId() {
		return developerId;
	}

	/**
	 * @param developerId
	 *            The developerId to set.
	 */
	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}

	/**
	 * @return Returns the categoryId.
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            The categoryId to set.
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the keyword.
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            The keyword to set.
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return Returns the shortDesc.
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * @param shortDesc
	 *            The shortDesc to set.
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * @return Returns the iconUrl.
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl
	 *            The iconUrl to set.
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * @return Returns the longDesc.
	 */
	public String getLongDesc() {
		return longDesc;
	}

	/**
	 * @param longDesc
	 *            The longDesc to set.
	 */
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	/**
	 * @return Returns the projectUrl.
	 */
	public String getProjectUrl() {
		return projectUrl;
	}

	/**
	 * @param projectUrl
	 *            The projectUrl to set.
	 */
	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	/**
	 * @return Returns the status.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return Returns the managerId.
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId
	 *            The managerId to set.
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return Returns the checkTime.
	 */
	public Date getCheckTime() {
		return checkTime;
	}

	/**
	 * @param checkTime
	 *            The checkTime to set.
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * @return Returns the del.
	 */
	public Integer getDel() {
		return del;
	}

	/**
	 * @param del
	 *            The del to set.
	 */
	public void setDel(Integer del) {
		this.del = del;
	}

	/**
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return Returns the appkey.
	 */
	public String getAppkey() {
		return appkey;
	}

	/**
	 * @param appkey
	 *            The appkey to set.
	 */
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	/**
	 * @return Returns the updateTime.
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            The updateTime to set.
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return Returns the os.
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            The os to set.
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return Returns the fileName.
	 */
	public String getFileName() {
		if (projectUrl != null) {
			if (projectUrl.contains("/")) {
				fileName = projectUrl
						.substring(projectUrl.lastIndexOf("/") + 1);
			} else {
				fileName = projectUrl;
			}
		}
		return fileName;
	}

	/**
	 * @param fileName
	 *            The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return Returns the checkMsg.
	 */
	public String getCheckMsg() {
		return checkMsg;
	}

	/**
	 * @param checkMsg
	 *            The checkMsg to set.
	 */
	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}

	/**
	 * @return Returns the releaseTime.
	 */
	public Date getReleaseTime() {
		return releaseTime;
	}

	/**
	 * @param releaseTime
	 *            The releaseTime to set.
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	/**
	 * @return the appStatusName
	 */
	public String getAppStatusName() {
		return StatusConfigUtil.getString(this.status.toString()); 
	}
}
